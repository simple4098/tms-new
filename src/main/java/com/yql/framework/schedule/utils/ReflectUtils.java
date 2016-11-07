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

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Simon Wang
 */
public abstract class ReflectUtils {
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) params[index];
    }

    public static Object getFieldValue(Object target, String fieldName) {
        Object value = null;
        Field field = getField(target, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                value = field.get(target);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static void setFieldValue(Object target, String fieldName, Object value) {
        Field field = getField(target, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(target, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }

        }
    }

    public static Field getField(Object target, String fieldName) {
        Class<?> clazz = target.getClass();
        while (clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

}
