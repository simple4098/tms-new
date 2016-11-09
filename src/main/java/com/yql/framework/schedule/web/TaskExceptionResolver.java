package com.yql.framework.schedule.web;


import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常处理 统一ajax 处理
 *
 * @author wangxiaohong
 */
public class TaskExceptionResolver implements HandlerExceptionResolver {
    public static final String DEFAULT_CONTENT_TYPE = "application/json";
    private Logger logger = LoggerFactory.getLogger(TaskExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(DEFAULT_CONTENT_TYPE);
        response.setCharacterEncoding("UTF-8");
        if (!response.isCommitted()) {
            try {
                ExtMessage extMessage = ExtMessageBuilder.createMessage(ex.getMessage());
                extMessage.setSuccess(false);
                response.getWriter().print(JSONArray.toJSON(extMessage));
                response.flushBuffer();
            } catch (IOException e) {
                logger.error(e.getMessage(), ex);
            } catch (Exception e) {
                logger.error(e.getMessage(), ex);
            }
        }
        return null;
    }
}
