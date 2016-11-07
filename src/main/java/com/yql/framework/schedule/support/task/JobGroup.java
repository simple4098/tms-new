package com.yql.framework.schedule.support.task;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

/**
 * @author wangxiaohong
 */
public class JobGroup {
    private JobDetail jobDetail;
    private Trigger trigger;
    private JobKey jobKey;

    public JobKey getJobKey() {
        return jobKey;
    }

    public void setJobKey(JobKey jobKey) {
        this.jobKey = jobKey;
    }

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
}
