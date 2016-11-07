package com.yql.framework.schedule.web;


import com.yql.framework.schedule.support.EnumUtils;
import com.yql.framework.schedule.support.MessageSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字段工具类 ，用于封装字段 类型对象
 *
 * @author wangxiaohong
 */
public abstract class FieldUtils {
    /**
     * 将枚举对象 转化为字段对象
     *
     * @param enums 待转化的枚举对象
     */
    public static List<Field> convertToList(Enum[] enums) {
        return Arrays.asList(enums).stream().map(e -> new Field(MessageSource.lan(EnumUtils.getFullName(e)), e.name())).collect(Collectors.toList());
    }
}
