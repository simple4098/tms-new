package com.yql.framework.schedule.web;

import java.util.List;

/**
 * 对象Ext Js 消息体
 *
 * @author wangxiaohong
 */
public class ExtMessage {
    private String msg;
    private boolean success = true;
    private List results;
    private long totalCount;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List getResults() {
        return results;
    }

    public <T> void setResults(List<T> results) {
        this.results = results;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
