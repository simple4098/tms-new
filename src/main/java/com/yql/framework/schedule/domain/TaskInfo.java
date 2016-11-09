package com.yql.framework.schedule.domain;

import com.yql.framework.schedule.support.EnumUtils;
import com.yql.framework.schedule.support.MessageSource;

import java.util.Date;

/**
 * @author wangxiaohong
 */
public class TaskInfo {
    //id
    private int id;

    //创建时间
    private Date dateCreated = new Date();
    //最后更新
    private Date lastUpdated = new Date();

    //是否删除
    private boolean deleted = false;
    //任务名称
    private String name;
    //克隆表达式
    private String cron;
    //作者
    private String author;
    //描述
    private String description;
    //任务状态
    private TaskStatus status;
    //请求资源
    private String uri;
    //任务类型
    private JobType type;

    //应用平台
    private String app;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return this.cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getStatusDescription() {
        if (status == null) return "";
        return MessageSource.lan(EnumUtils.getFullName(status));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
