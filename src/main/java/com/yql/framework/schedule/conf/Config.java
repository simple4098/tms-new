package com.yql.framework.schedule.conf;

import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

/**
 * @author wangxiaohong
 */
@Configuration
public class Config {

    @Bean
    public JobFactory jobFactory() {
        return new SpringBeanJobFactory();
    }

    @Bean(name = "quartzScheduler")
    public SchedulerFactoryBean scheduler(JobFactory jobFactory, DataSource dataSource) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(jobFactory);
        factoryBean.setDataSource(dataSource);
        factoryBean.setApplicationContextSchedulerContextKey("springApplicationContext");
        factoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
        factoryBean.setAutoStartup(false);
        return factoryBean;
    }
}
