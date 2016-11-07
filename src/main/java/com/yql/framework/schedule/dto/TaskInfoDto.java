package com.yql.framework.schedule.dto;

import com.yql.framework.schedule.domain.TaskInfo;

public class TaskInfoDto extends TaskInfo {
    private Long preExecTime;
    private Long nextExecTime;

    public Long getPreExecTime() {
        return preExecTime;
    }

    public void setPreExecTime(Long preExecTime) {
        this.preExecTime = preExecTime;
    }

    public Long getNextExecTime() {
        return nextExecTime;
    }

    public void setNextExecTime(Long nextExecTime) {
        this.nextExecTime = nextExecTime;
    }
}
