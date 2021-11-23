package com.dhu.yumail.server.service.api;

import java.util.Date;
import java.util.Map;

/**
 * Quartz服务
 * @author Yupi Li
 * @date 19/03/15
 */
public interface IQuartzService {

    /**
     * 添加一个定时任务
     * @param jobName          Job名
     * @param jobGroupName     Job组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         Job类类型
     * @param cron             时间设置 表达式，参考quartz说明文档
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param params           参数
     */
    void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
                Class jobClass, String cron, Date startTime, Date endTime, Map<String, Object> params);

    /**
     * 修改一个任务的触发时间
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param cron             时间设置，参考quartz说明文档
     */
    void modifyJobTime(String triggerName, String triggerGroupName,
                       String cron);

    /**
     * 移除一个任务
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName);

    /**
     * 启动所有定时任务
     */
    void startScheduler();

    /**
     * 关闭所有定时任务
     */
    void pauseScheduler();

    /**
     * 获取调度器状态
     * @return
     */
    int getSchedulerStatus();

    /**
     * 暂停job
     * @param jobName
     * @param jobGroupName
     */
    void pauseJob(String jobName, String jobGroupName);

    /**
     * 恢复job
     * @param jobName
     * @param jobGroupName
     */
    void resumeJob(String jobName, String jobGroupName);
}
