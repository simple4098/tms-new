package com.yql.framework.schedule.web;

import java.util.List;

/**
 * 消息封装类，用于常用消息体的封装
 *
 * @author wangxiaohong
 */
public final class ExtMessageBuilder {
    public static ExtMessage createMessage(String message) {
        ExtMessage extMessage = new ExtMessage();
        extMessage.setMsg(message);
        return extMessage;
    }

    public static <T> ExtMessage createResults(List<T> list) {
        ExtMessage extMessage = new ExtMessage();
        extMessage.setResults(list);
        return extMessage;
    }
}
