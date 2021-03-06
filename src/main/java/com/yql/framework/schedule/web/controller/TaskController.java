package com.yql.framework.schedule.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yql.framework.schedule.domain.TaskInfo;
import com.yql.framework.schedule.dto.TaskInfoDto;
import com.yql.framework.schedule.service.ITaskEntityService;
import com.yql.framework.schedule.web.ExtMessage;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.yql.framework.schedule.web.ExtMessageBuilder.createMessage;
import static com.yql.framework.schedule.web.ExtMessageBuilder.createResults;

/**
 * 任务操作响应
 *
 * @author wangxiaohong
 */
@RestController
@RequestMapping("/task")
public class TaskController {


    @Resource
    private ITaskEntityService taskService;

    /**
     * 加载任务列表数据
     */
    @RequestMapping("/list")
    public ExtMessage showTaskList(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        PageInfo<TaskInfoDto> pageInfo = new PageInfo<>(taskService.findList());
        ExtMessage extMessage = createResults(pageInfo.getList());
        extMessage.setTotalCount(pageInfo.getTotal());
        return extMessage;
    }

    /**
     * 任务数据更新
     *
     * @param taskInfo 表单对象
     */
    @RequestMapping("/saveEdit")
    public ExtMessage saveEdit(TaskInfo taskInfo) {
        Assert.notNull(taskInfo, "更新失败，未找到ID");
        taskService.update(taskInfo);
        return createMessage("更新成功");

    }

    /**
     * 任务新建保存
     *
     * @param taskInfo 表单对象
     */
    @RequestMapping("/saveCreate")
    public ExtMessage saveCreate(TaskInfo taskInfo) {
        taskService.create(taskInfo);
        return createMessage("创建成功");
    }

    /**
     * 任务启动
     *
     * @param id 任务ID
     */
    @RequestMapping("/startup")
    public ExtMessage startup(int id) {
        taskService.startup(id);
        return createMessage("启动成功");
    }

    /**
     * 任务暂停
     *
     * @param id 任务ID
     */
    @RequestMapping("/pause")
    public ExtMessage pause(int id) {
        taskService.pause(id);
        return createMessage("暂停成功");
    }

    /**
     * 任务删除
     *
     * @param id 任务ID
     */
    @RequestMapping("/delete")
    public ExtMessage delete(int id) {
        taskService.delete(id);
        return createMessage("删除成功");
    }
}
