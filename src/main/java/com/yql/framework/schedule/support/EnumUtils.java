package com.yql.framework.schedule.support;

/**
 * @author wangxiaohong
 */
public class EnumUtils {
    public static String getFullName(Enum e) {
        return e.getClass().getName() + "." + e.name();
    }
}
