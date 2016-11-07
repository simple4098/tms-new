package com.yql.framework.schedule.support;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 默认计数器
 * @author wangxiaohong
 */
public class DefaultCounter implements Counter {
    private AtomicLong value;

    public DefaultCounter() {
        this.value = new AtomicLong(0);
    }

    public DefaultCounter(long value) {
        this.value = new AtomicLong(0);
    }

    @Override
    public long increment(long amount) {
        return value.addAndGet(amount);
    }

    @Override
    public long increment() {
        return value.get();
    }
}
