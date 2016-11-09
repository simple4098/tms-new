package com.yql.framework.schedule.conf;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author wangxiaohong
 */
@Configuration
public class MybatisConfiguration {

    private Properties pageProperties() {
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        return properties;
    }

    @Bean
    public Interceptor pageHelper() {
        PageHelper pageHelper = new PageHelper();
        pageHelper.setProperties(pageProperties());
        return pageHelper;
    }

    @Bean
    public Interceptor[] interceptors(Interceptor pageHelper) {
        return new Interceptor[]{pageHelper};
    }
}
