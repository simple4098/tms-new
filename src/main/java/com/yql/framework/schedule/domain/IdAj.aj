package com.yql.framework.schedule.domain;

import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author wangxiaohong
 */
privileged aspect IdAj {

    pointcut createId(TaskInfo taskInfo): execution(com.yql.framework.schedule.domain.TaskInfo.new(..))&&target(taskInfo);

    pointcut ignoreIdSetting(String taskId):execution(public void com.yql.framework.schedule.domain.TaskInfo.setId(java.lang.String)) && args(taskId);

    after(TaskInfo taskInfo):createId(taskInfo){
        taskInfo.setId(UUID.randomUUID().toString());
    }

    void around(String taskId):ignoreIdSetting(taskId){
        if (!StringUtils.hasText(taskId)) {
            return;
        }
        proceed(taskId);
    }

}
