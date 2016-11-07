package com.yql.framework.schedule.support;

/**
 * @author wangxiaohong
 */
public interface Counter {
    long increment(long amount);

    long increment();
}
