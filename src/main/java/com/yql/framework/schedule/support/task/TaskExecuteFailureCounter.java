package com.yql.framework.schedule.support.task;

import com.yql.framework.schedule.support.Counter;
import com.yql.framework.schedule.support.DefaultCounter;

import java.util.HashMap;
import java.util.Map;

/**
 * 调度失败 计数器
 *
 * @author wangxiaohong
 */
public class TaskExecuteFailureCounter {
    private Map<Integer, DefaultCounter> taskCounterMap = new HashMap<>();

    public void increment(int taskId) {
        if (taskCounterMap.get(taskId) == null) {
            reset(taskId);
        } else {
            Counter counter = taskCounterMap.get(taskId);
            counter.increment(1);
        }

    }

    public boolean exist(int taskId) {
        return null != taskCounterMap.get(taskId);
    }

    public long getIncrement(int taskId) {
        Counter counter = taskCounterMap.get(taskId);
        return counter.increment();
    }

    public void reset(int taskId) {
        taskCounterMap.put(taskId, new DefaultCounter());
    }
}
