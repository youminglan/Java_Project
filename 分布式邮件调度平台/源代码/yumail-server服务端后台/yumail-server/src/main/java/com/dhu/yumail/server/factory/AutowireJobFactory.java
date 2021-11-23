package com.dhu.yumail.server.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自实现JobFactory
 * 生成可注入依赖的Job
 * @author Yupi Li
 * @date 19/01/21
 */
@Component
public class AutowireJobFactory extends AdaptableJobFactory {
    @Resource
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法创建job实例
        Object jobInstance = super.createJobInstance(bundle);
        // 为job实例注入依赖
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}  
