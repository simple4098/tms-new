/*
* Copyright (c) 2013S Chengdu Funi Cloud Code Technology Co.,Ltd.
* www.funi.com
* All rights reserved.
*
* This software is the confidential and proprietary information of
* Easy Solution Technologies ("Confidential Information").
* You shall not disclose such Confidential Information and shall use
* it only in accordance with the terms of the license agreement you
* entered into with Chengdu Funi Cloud Code Technology.
*/
package com.yql.framework.schedule.utils;

import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Simon Wang
 */
public abstract class BeanUtils {

    public static Map<String, Object> describe(Object bean) {
        Assert.notNull(bean, "Target bean can not be null!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Field> fields = new ArrayList<Field>();

        Class<?> aClass = bean.getClass();

        do {
            Field[] declaredFields = aClass.getDeclaredFields();
            fields.addAll(Arrays.asList(declaredFields));
            aClass = aClass.getSuperclass();
        } while (!aClass.equals(Object.class));

        for (Field field : fields) {
            String fieldName = field.getName();
            map.put(fieldName, ReflectUtils.getFieldValue(bean, fieldName));
        }
        return map;
    }
}
