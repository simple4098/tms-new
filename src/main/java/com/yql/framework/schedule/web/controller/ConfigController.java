package com.yql.framework.schedule.web.controller;

import com.yql.framework.schedule.domain.JobType;
import com.yql.framework.schedule.web.ExtMessage;
import com.yql.framework.schedule.web.FieldUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.yql.framework.schedule.web.ExtMessageBuilder.createResults;

/**
 * 通用配置  资源
 *
 * @author wangxiaohong
 */
@RestController
public class ConfigController {

    /**
     * 系统运行时 软件配置
     */
    @RequestMapping("/environment/init")
    public Map model(HttpServletRequest request) {
        Map model = new HashMap();
        model.put("contextPath", request.getContextPath());
        model.put("js", new ArrayList<String>());
        model.put("css", new ArrayList<String>());
        model.put("id", UUID.randomUUID().toString());
        return model;
    }

    /**
     * 支持 远程JOb 类型
     */
    @RequestMapping("/environment/jobTypeList")
    public ExtMessage getJobTypeList() {
        return createResults(FieldUtils.convertToList(JobType.values()));
    }
}
