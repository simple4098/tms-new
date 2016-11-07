package com.yql.framework.schedule.support;

import com.yql.framework.schedule.service.ITaskEntityService;
import com.yql.framework.schedule.support.job.TaskJobHandler;
import com.yql.framework.schedule.support.task.TaskExecuteFailureCounter;
import com.yql.framework.schedule.support.task.TaskJobContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * task 调度 委托 类，根据 任务类型 委托给spring 管理的job执行
 *
 * @author
 */
public class DelegatingJobProxy extends QuartzJobBean {

    private TaskJobHandler delegate;

    private ApplicationContext springApplicationContext;
    protected static final Log logger = LogFactory.getLog(DelegatingJobProxy.class);

    private static final int MAX_ERROR_COUNT = 10;
    private  TaskExecuteFailureCounter taskExecuteFailureCounter = new TaskExecuteFailureCounter();


    public void setSpringApplicationContext(ApplicationContext springApplicationContext) {
        this.springApplicationContext = springApplicationContext;
    }

    /**
     * 调度器入口方法
     *
     * @param context 调度器上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            TaskJobContext taskJobContext = createNewJobTaskContext(context);
            delegate = findDelegate(taskJobContext);
            Assert.notNull(delegate, "未找到合适的任务解析器");
            invokeDelegate(context, taskJobContext);
        } catch (SchedulerException ex) {
            throw new JobExecutionException(ex);
        }
    }

    /**
     * 新建 Job 的上下文
     */
    private TaskJobContext createNewJobTaskContext(JobExecutionContext context) {
        TaskJobContext taskJobContext = new TaskJobContext();
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(taskJobContext);
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.addPropertyValues(context.getMergedJobDataMap());
        bw.setPropertyValues(pvs, true);
        return taskJobContext;
    }


    /**
     * 调用 被委托 对象 相应的方法
     *
     * @throws JobExecutionException
     */
    private void invokeDelegate(final JobExecutionContext context, TaskJobContext meta) throws JobExecutionException {
        initCounter(meta);
        continueByDefault(context, meta);
    }

    private void continueByDefault(JobExecutionContext context, TaskJobContext meta) {
        execute(context, copyTaskJobMeta(meta));
    }

    /**
     * 单个 线程中 委托对象执行逻辑
     *
     * @param context      调度器上下文
     * @param cloneContext task 上下文
     */
    private void execute(final JobExecutionContext context, TaskJobContext cloneContext) {
        if (cloneContext == null) {
            return;
        }
        try {
            delegate.handle(context, cloneContext);
            taskExecuteFailureCounter.reset(cloneContext.getTaskId());
        } catch (Exception e) {
            logger.error(e);
            if (maxErrorCountReached(cloneContext)) {
                logger.info("连续错误");
                //如果连续错误5次 则 强制暂停
                ITaskEntityService taskEntityService = springApplicationContext.getBean(ITaskEntityService.class);
                taskEntityService.pause(cloneContext.getTaskId());
                return;
            }
            taskExecuteFailureCounter.increment(cloneContext.getTaskId());
        }
    }

    private TaskJobContext copyTaskJobMeta(TaskJobContext meta) {
        TaskJobContext copyMeta = new TaskJobContext();
        try {
            BeanUtils.copyProperties(meta, copyMeta);
            return copyMeta;
        } catch (Exception e) {
            return null;
        }
    }

    private void initCounter(TaskJobContext meta) {
        if (!taskExecuteFailureCounter.exist(meta.getTaskId())) {
            taskExecuteFailureCounter.reset(meta.getTaskId());
        }
    }

    private boolean maxErrorCountReached(TaskJobContext meta) {
        return taskExecuteFailureCounter.getIncrement(meta.getTaskId()) > MAX_ERROR_COUNT;
    }

    @SuppressWarnings("unchecked")
    private TaskJobHandler findDelegate(TaskJobContext taskJobContext) {
        Map<String, TaskJobHandler> map = springApplicationContext.getBeansOfType(TaskJobHandler.class);
        for (Map.Entry<String, TaskJobHandler> entry : map.entrySet()) {
            if (entry.getValue().supports(taskJobContext)) {
                return entry.getValue();
            }
        }
        return null;
    }

}
