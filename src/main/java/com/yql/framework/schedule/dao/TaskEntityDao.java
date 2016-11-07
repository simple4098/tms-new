package com.yql.framework.schedule.dao;

import com.yql.framework.schedule.domain.TaskInfo;
import com.yql.framework.schedule.dto.TaskInfoDto;
import com.yql.framework.schedule.support.Pagination;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangxiaohong
 */
@Repository
public class TaskEntityDao implements ITaskEntityDao {
    private static final String STATEMENT = "ITaskEntityDao";

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public List<TaskInfoDto> findList(Pagination<TaskInfoDto> param) {
        return sqlSessionTemplate.selectList(STATEMENT + ".findList", param);
    }

    @Override
    public void create(TaskInfo taskInfo) {
        sqlSessionTemplate.insert(STATEMENT + ".insert", taskInfo);
    }

    @Override
    public TaskInfoDto findById(String taskId) {
        return sqlSessionTemplate.selectOne(STATEMENT + ".load", taskId);
    }

    @Override
    public void deleteBy(String id) {
        sqlSessionTemplate.delete(STATEMENT + ".delete", id);
    }

    @Override
    public void update(TaskInfo taskInfo) {
        sqlSessionTemplate.update(STATEMENT + ".update", taskInfo);
    }

}
