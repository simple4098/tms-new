package com.yql.framework.schedule.service.impl;

import com.yql.framework.schedule.dao.ITaskEntityDao;
import com.yql.framework.schedule.domain.TaskInfo;
import com.yql.framework.schedule.dto.TaskInfoDto;
import com.yql.framework.schedule.service.ITaskEntityService;
import com.yql.framework.schedule.support.Pagination;
import com.yql.framework.schedule.support.task.ITaskEntityManager;
import com.yql.framework.schedule.support.task.TaskInfoChecker;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangxiaohong
 */
@Service
public class TaskEntityService implements ITaskEntityService {
    @Resource
    private ITaskEntityDao taskEntityDao;
    @Resource
    private ITaskEntityManager taskEntityManager;

    @Resource
    private TaskInfoChecker taskInfoChecker;


    @Override
    public List<TaskInfoDto> findList(Pagination<TaskInfoDto> param) {
        return taskEntityDao.findList(param);
    }

    @Override
    public void create(TaskInfo taskInfo) {
        Assert.isTrue(CronExpression.isValidExpression(taskInfo.getCron()), "对不起，克隆表达式有误");
        taskEntityDao.create(taskInfo);
    }

    @Override
    public TaskInfo findById(String taskId) {
        return taskEntityDao.findById(taskId);
    }

    @Override
    public void startup(String id) {
        TaskInfo taskInfo = findById(id);
        taskInfoChecker.checkBeforeStart(taskInfo);
        taskEntityManager.startup(taskInfo);

    }

    @Override
    public void pause(String id) {
        TaskInfo taskInfo = findById(id);
        taskEntityManager.pause(taskInfo);

    }

    @Override
    public void delete(String id) {
        TaskInfo taskInfo = findById(id);
        taskInfoChecker.checkBeforeDelete(taskInfo);
        taskEntityManager.delete(taskInfo);
        taskEntityDao.deleteBy(id);
    }

    @Override
    public void update(TaskInfo taskInfo) {
        TaskInfoDto originalTaskInfo = taskEntityDao.findById(taskInfo.getId());
        taskInfoChecker.checkBeforeUpdate(originalTaskInfo);
        taskEntityDao.update(taskInfo);
    }
}
