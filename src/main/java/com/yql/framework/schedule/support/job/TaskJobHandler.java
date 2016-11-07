package com.yql.framework.schedule.support.job;

import com.yql.framework.schedule.support.task.TaskJobContext;
import org.quartz.JobExecutionContext;

/**
 * @author wangxiaohong
 */
public interface TaskJobHandler {
    boolean supports(TaskJobContext meta);

    void handle(JobExecutionContext context, TaskJobContext meta) throws Exception;
}
