package com.dhu.yumail.server.service.impl;

import com.dhu.yumail.server.constant.ExceptionEnum;
import com.dhu.yumail.server.listener.CommonTriggerListener;
import com.dhu.yumail.server.model.base.BusinessException;
import com.dhu.yumail.server.properties.YumailServerProperties;
import com.dhu.yumail.server.service.api.ILogService;
import com.dhu.yumail.server.service.api.IQuartzService;
import com.dhu.yumail.server.utils.LogTemplate;
import org.quartz.*;
import org.quartz.impl.matchers.EverythingMatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Quartz服务实现类
 *
 * @author Yupi Li
 * @date 19/03/15
 */
@Service
public class QuartzServiceImpl implements IQuartzService {

    @Resource
    private Scheduler scheduler;

    @Resource
    private ILogService logService;

    @Resource
    private CommonTriggerListener commonTriggerListener;

    @Resource
    private YumailServerProperties yumailServerProperties;

    @Override
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
                       Class jobClass, String cron, Date startTime, Date endTime, Map<String, Object> params) {
        try {
            // 任务名，任务组，任务执行类
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            System.out.println("jobDetail.getKey:" + jobDetail.getKey());
            // 触发器
            if (params != null) {
                jobDetail.getJobDataMap().putAll(params);
            }
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            if (startTime != null) {
                triggerBuilder.startAt(startTime);
            } else {
                triggerBuilder.startNow();
            }
            if (endTime != null) {
                triggerBuilder.endAt(endTime);
            }
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            // 启动
            scheduler.start();
            // 绑定通用监听器
            scheduler.getListenerManager().addTriggerListener(commonTriggerListener, EverythingMatcher.allTriggers());
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);
            logService.info(yumailServerProperties.getAppId(), LogTemplate.startJobTemplate(jobName, jobGroupName, jobClass, cron, startTime, endTime));
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.QUARTZ_ERROR);
        }
    }

    /**
     * 功能：修改一个任务的触发时间
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param cron             时间设置，参考quartz说明文档
     */
    @Override
    public void modifyJobTime(String triggerName, String triggerGroupName, String cron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.QUARTZ_ERROR);
        }
    }

    /**
     * 功能: 移除一个任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    @Override
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
            // 记录日志
            logService.error(jobGroupName, LogTemplate.delJobTemplate(jobName, jobGroupName));
            System.out.println("removeJob:" + JobKey.jobKey(jobName));
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.QUARTZ_ERROR);
        }
    }

    /**
     * 功能：启动所有定时任务
     */
    @Override
    public void startScheduler() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.QUARTZ_ERROR);
        }
    }

    /**
     * 功能：暂停所有定时任务
     */
    @Override
    public void pauseScheduler() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.standby();
            }
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.QUARTZ_ERROR);
        }
    }

    /**
     * 功能：获取调度器状态
     */
    @Override
    public int getSchedulerStatus() {
        try {
            if (scheduler.isInStandbyMode()) {
                return 1;
            } else if (scheduler.isStarted()) {
                return 0;
            } else if (scheduler.isShutdown()) {
                return 2;
            } else {
                return 3;
            }
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.QUARTZ_ERROR);
        }
    }

    @Override
    public void pauseJob(String jobName, String jobGroupName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
        // 停止触发器
        try {
            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new BusinessException(ExceptionEnum.QUARTZ_ERROR);
        }
    }

    @Override
    public void resumeJob(String jobName, String jobGroupName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
        // 恢复触发器
        try {
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new BusinessException(ExceptionEnum.QUARTZ_ERROR);
        }
    }
}