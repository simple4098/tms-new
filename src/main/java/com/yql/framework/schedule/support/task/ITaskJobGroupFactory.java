package com.yql.framework.schedule.support.task;


import com.yql.framework.schedule.domain.TaskInfo;

/**
 * User: Administrator
 * Date: 14-2-14
 * Time: 上午10:14
 */
public interface ITaskJobGroupFactory {
    JobGroup getJobGroup(TaskInfo taskInfo);
}
