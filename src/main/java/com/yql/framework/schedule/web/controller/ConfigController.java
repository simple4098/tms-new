package com.yql.framework.schedule.web.controller;

import com.yql.framework.schedule.domain.JobType;
import com.yql.framework.schedule.web.ExtMessage;
import com.yql.framework.schedule.web.FieldUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

import static com.yql.framework.schedule.web.ExtMessageBuilder.createResults;

/**
 * 通用配置  资源
 *
 * @author wangxiaohong
 */
@Controller
public class ConfigController {

    /**
     * 系统运行时 软件配置
     */
    @RequestMapping("/environment/init")
    public Model model(Model model, HttpServletRequest request) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("js", new ArrayList<String>());
        model.addAttribute("css", new ArrayList<String>());
        model.addAttribute("id", UUID.randomUUID().toString());
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
