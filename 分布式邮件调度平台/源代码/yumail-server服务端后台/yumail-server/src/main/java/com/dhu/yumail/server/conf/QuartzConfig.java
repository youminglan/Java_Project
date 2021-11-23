package com.dhu.yumail.server.conf;

import com.dhu.yumail.server.factory.AutowireJobFactory;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;

/**
 * Quartz配置类
 * 生成factory和scheduler依赖
 * @author Yupi Li
 * @date 19/01/21
 */
@Configuration
public class QuartzConfig {
    @Resource
    private AutowireJobFactory autowireJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(autowireJobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

}