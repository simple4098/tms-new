package com.yql.framework.schedule.support.job;

import com.yql.framework.api.job.ITask;
import com.yql.framework.schedule.domain.JobType;
import com.yql.framework.schedule.support.task.TaskJobContext;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.rmi.Naming;

/**
 * RMI 方式远程访问
 *
 * @author wangxiaohong
 */
@Component
public class RmiTaskJobHandler implements TaskJobHandler {
    private static Logger logger = LoggerFactory.getLogger(RmiTaskJobHandler.class);

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
