package com.yql.framework.schedule.web;

import java.io.Serializable;

/**
 * 字段，通常用于JSON返回 封装简单的Kv 对象
 * @author wangxiaohong
 */
public class Field implements Serializable {
    private String name;
    private String value;

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
