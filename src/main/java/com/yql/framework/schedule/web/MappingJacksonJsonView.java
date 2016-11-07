package com.yql.framework.schedule.web;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * JSON 返回 加工， 如果返回的JSON key 为extMessage ,则直接把对应的值作为主要对象返回
 *
 * @author wangxiaohong
 */
public class MappingJacksonJsonView extends MappingJackson2JsonView {
    @Override
    @SuppressWarnings("unchecked")
    protected Object filterModel(Map<String, Object> model) {
        String extMessageKey = StringUtils.uncapitalize(ExtMessage.class.getSimpleName());
        for (Map.Entry<String, Object> map : model.entrySet()) {
            if (map.getKey().equals(extMessageKey)) {
                return map.getValue();
            }
        }
        return super.filterModel(model);
    }
}
