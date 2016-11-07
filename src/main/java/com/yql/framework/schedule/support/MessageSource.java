package com.yql.framework.schedule.support;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 *  @author wangxiaohong
 */
public class MessageSource extends ReloadableResourceBundleMessageSource {
    private static final MessageSource ms = new MessageSource();

    protected MessageSource() {
        this.setBasenames("message");
    }

    public static String lan(String str) {
        return ms.getMessage(str, null, Locale.getDefault());

    }
}
