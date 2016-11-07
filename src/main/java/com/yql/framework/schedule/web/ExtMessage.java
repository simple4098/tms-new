package com.yql.framework.schedule.web;

import java.util.List;

/**
 * 对象Ext Js 消息体
 *
 * @author wangxiaohong
 */
public class ExtMessage {
    private String msg;
    private Boolean success = true;
    private List results;
    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List getResults() {
        return results;
    }

    public <T> void setResults(List<T> results) {
        this.results = results;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
