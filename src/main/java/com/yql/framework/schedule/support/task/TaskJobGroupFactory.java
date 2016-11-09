package com.yql.framework.schedule.support.task;

import com.yql.framework.schedule.domain.TaskInfo;
import com.yql.framework.schedule.support.DelegatingJobProxy;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.ParseException;


/**
 * @author wangxiaohong
 */
@Component
public class TaskJobGroupFactory implements ITaskJobGroupFactory {

    static final String metaTaskIdKey = "taskId";
    static final String metaTaskUriKey = "uri";
    static final String metaCityIdArray = "taskCityIdArray";
    static final String metaTaskTypeKey = "type";
    static final String JOB_DETAIL_PREFIX = "jobDetail_";
    static final String JOB_TRIGGER_PREFIX = "trigger_";


    public JobGroup getJobGroup(TaskInfo taskInfo) {
        JobGroup jobGroup = new JobGroup();
        Assert.notNull(taskInfo.getType(), "对不起，未配置调度类型");
        Assert.notNull(taskInfo.getUri(), "对不起，未配置请求路径");
        Assert.notNull(taskInfo.getApp(), "对不起，未配置应用");
        Assert.notNull(taskInfo.getAuthor(), "对不起，未配置作者");
        jobGroup.setJobKey(createJobKey(taskInfo));
        JobDetailImpl jobDetail = createJobDetail(jobGroup.getJobKey());
        jobDetail.setDescription(taskInfo.getDescription());
        JobDataMap map = jobDetail.getJobDataMap();
        /**
         *主要用户填充 调度所需数据，请不要修改 key 值，需要和 TaskJobMeta的属性保持 一致
         */
        map.put(metaTaskIdKey, taskInfo.getId());
        map.put(metaTaskUriKey, taskInfo.getUri());
        map.put(metaTaskTypeKey, taskInfo.getType().name());
        jobGroup.setJobDetail(jobDetail);
        jobGroup.setTrigger(createCronTrigger(jobGroup.getJobKey(), taskInfo.getCron()));

        return jobGroup;
    }

    private JobKey createJobKey(TaskInfo taskInfo) {
        return new JobKey(String.valueOf(taskInfo.getId()), taskInfo.getType().name());
    }

    private JobDetailImpl createJobDetail(JobKey jobKey) {
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setJobClass(DelegatingJobProxy.class);
        jobDetail.setName(JOB_DETAIL_PREFIX + jobKey.getName());
        jobDetail.setKey(jobKey);
        return jobDetail;
    }


    private Trigger createCronTrigger(JobKey jobKey, String cron) {
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setJobKey(jobKey);
        cronTrigger.setName(JOB_TRIGGER_PREFIX.concat(jobKey.getName()));
        try {
            cronTrigger.setCronExpression(cron);
        } catch (ParseException parseException) {
            throw new RuntimeException("启动失败[克隆表达式错误]", parseException);
        }
        return cronTrigger;
    }
}
