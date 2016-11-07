package com.yql.framework.schedule.dao;


import com.yql.framework.schedule.domain.TaskInfo;
import com.yql.framework.schedule.dto.TaskInfoDto;
import com.yql.framework.schedule.support.Pagination;

import java.util.List;

/**
 * @author wangxiaohong
 */
public interface ITaskEntityDao {
    public List<TaskInfoDto> findList(Pagination<TaskInfoDto> param);

    void create(TaskInfo taskInfo);

    TaskInfoDto findById(String taskId);

    void deleteBy(String id);

    void update(TaskInfo taskInfo);
}
