package com.yql.framework.schedule.dao;


import com.yql.framework.schedule.domain.TaskInfo;
import com.yql.framework.schedule.dto.TaskInfoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wangxiaohong
 */
@Mapper
public interface TaskMapper {
    @Select("SELECT t.id,t.task_name,t.cron_expression,t.author,t.task_type ,qt.prev_fire_time,qt.next_fire_time," +
            "  qt.TRIGGER_STATE AS status,t.uri,t.task_description,t.app" +
            "  FROM task_info t  LEFT JOIN QRTZ_TRIGGERS qt ON t.id = qt.JOB_NAME")
    @Results(value = {
            @Result(column = "task_type", property = "type"),
            @Result(column = "task_name", property = "name"),
            @Result(column = "cron_expression", property = "cron"),
            @Result(column = "task_description", property = "description"),
            @Result(column = "next_fire_time",property = "nextExecTime"),
            @Result(column = "prev_fire_time",property = "preExecTime"),
    })
    List<TaskInfoDto> findList();

    @Insert("INSERT INTO task_info (id,task_name,cron_expression,task_description,author,task_type,uri,app,deleted)" +
            "        VALUES (#{id},#{name},#{cron},#{description},#{author},#{type},#{uri},#{app},#{deleted})")
    void create(TaskInfo taskInfo);

    @Select("SELECT t.*,qt.TRIGGER_STATE AS status FROM  task_info t " +
            " LEFT JOIN QRTZ_TRIGGERS qt ON t.id = qt.JOB_NAME WHERE t.id=#{taskId}")
    @Results(value = {
            @Result(column = "task_type", property = "type"),
            @Result(column = "task_name", property = "name"),
            @Result(column = "cron_expression", property = "cron"),
            @Result(column = "task_description", property = "description"),
            @Result(column = "next_fire_time",property = "nextExecTime"),
            @Result(column = "prev_fire_time",property = "preExecTime")
    })
    TaskInfoDto findById(@Param("taskId") int taskId);

    @Delete("DELETE FROM task_info WHERE id=#{id}")
    void deleteBy(@Param("id") int id);

    @Update("UPDATE task_info SET" +
            "        task_name = #{name}," +
            "        task_description=#{description}," +
            "        author=#{author}," +
            "        task_type=#{type}," +
            "        uri=#{uri}," +
            "        app=#{app}," +
            "        cron_expression=#{cron}" +
            "        WHERE id=#{id}")
    void update(TaskInfo taskInfo);
}
