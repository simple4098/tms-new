package com.yql.framework.schedule.domain;

/**
 *@author wangxiaohong
 */
public enum TaskStatus {
    WAITING,ACQUIRED,EXECUTING,COMPLETE,BLOCKED,ERROR,PAUSED,PAUSED_BLOCKED,DELETED,MISFIRED
}
