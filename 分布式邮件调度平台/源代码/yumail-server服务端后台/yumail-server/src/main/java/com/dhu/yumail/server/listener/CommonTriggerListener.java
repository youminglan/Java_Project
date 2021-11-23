package com.dhu.yumail.server.listener;

import com.dhu.yumail.server.properties.YumailServerProperties;
import com.dhu.yumail.server.service.api.ILogService;
import com.dhu.yumail.server.utils.LogTemplate;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Job监听器
 * @author Yupi Li
 * @date 19/03/19
 */
@Component
public class CommonTriggerListener implements TriggerListener {

    @Resource
    private ILogService logService;


    @Resource
    private YumailServerProperties yumailServerProperties;

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        // 调度完成时记录日志
        if (trigger.getEndTime() != null && new Date().compareTo(trigger.getEndTime()) >= 0) {
            logService.info(yumailServerProperties.getAppId(), LogTemplate.endJobTemplate(jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), jobDetail.getJobClass()));
        }
    }
}
