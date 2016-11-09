package com.yql.framework.schedule.service;

import com.yql.framework.schedule.domain.TaskInfo;
import com.yql.framework.schedule.dto.TaskInfoDto;

import java.util.List;

/**
 * @author wangxiaohong
 */
public interface ITaskEntityService {
    List<TaskInfoDto> findList();

    void create(TaskInfo taskInfo);

    TaskInfo findById(int taskId);

    void startup(int id);

    void pause(int id);

    void delete(int id);

    void update(TaskInfo taskInfo);
}
