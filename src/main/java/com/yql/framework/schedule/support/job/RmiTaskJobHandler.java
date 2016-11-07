package com.yql.framework.schedule.support.job;

import com.yql.framework.api.job.ITask;
import com.yql.framework.schedule.domain.JobType;
import com.yql.framework.schedule.support.task.TaskJobContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;

import java.rmi.Naming;

/**
 * RMI 方式远程访问
 *
 * @author wangxiaohong
 */
public class RmiTaskJobHandler implements TaskJobHandler {
    private static Logger logger = LogManager.getLogger(RmiTaskJobHandler.class);

    @Override
    public boolean supports(TaskJobContext meta) {
        return meta.getType() != null && meta.getType().equals(JobType.RMI);
    }

    @Override
    public void handle(JobExecutionContext context, TaskJobContext meta) throws Exception {
        logger.info("开始请求资源" + meta.getUri());
        ITask task = (ITask) Naming.lookup(meta.getUri());
        task.execute();
    }


}
