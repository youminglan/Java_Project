package com.dhu.yumail.server.service.impl;

import com.dhu.yumail.server.job.RemoteSendMailJob;
import com.dhu.yumail.server.model.entity.Mail;
import com.dhu.yumail.server.model.vo.JobVO;
import com.dhu.yumail.server.service.api.IJobService;
import com.dhu.yumail.server.service.api.IQuartzService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 任务服务实现类
 *
 * @author Yupi Li
 * @date 19/03/23
 */
@Service
public class JobServiceImpl implements IJobService {

    @Resource
    private Scheduler scheduler;

    @Resource
    private IQuartzService quartzService;

    @Override
    public List<JobVO> listAllJobs() {
        GroupMatcher<TriggerKey> groupMatcher = GroupMatcher.anyTriggerGroup();
        return listJobsByGroupMatcher(groupMatcher);
    }

    @Override
    public List<JobVO> listJobsByAppId(String appId) {
        GroupMatcher<TriggerKey> groupMatcher = GroupMatcher.triggerGroupEquals(appId);
        return listJobsByGroupMatcher(groupMatcher);
    }

    @Override
    public void addJob(Mail mail) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("mail", mail);
        String uniKey = UUID.randomUUID().toString();
        // 组设置为调度源appId
        quartzService.addJob("sendMail-" + uniKey,
                mail.getSource(),
                "sendMail-" + uniKey,
                mail.getSource(),
                RemoteSendMailJob.class,
                mail.getCrontab(),
                mail.getStartTime(),
                mail.getEndTime(),
                params);
    }

    private List<JobVO> listJobsByGroupMatcher(GroupMatcher<TriggerKey> groupMatcher) {
        List<JobVO> jobVOS = new ArrayList<>();
        try {
            Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(groupMatcher);
            for (TriggerKey triggerKey : triggerKeys) {
                Trigger trigger = scheduler.getTrigger(triggerKey);
                JobDetail jobDetail = scheduler.getJobDetail(trigger.getJobKey());
                JobVO jobVO = new JobVO();
                Mail mail = (Mail) jobDetail.getJobDataMap().get("mail");
                jobVO.setJobName(jobDetail.getKey().getName());
                jobVO.setJobGroupName(jobDetail.getKey().getGroup());
                jobVO.setState(scheduler.getTriggerState(triggerKey).ordinal());
                jobVO.setNextFireTime(trigger.getNextFireTime());
                jobVO.setPreviousFireTime(trigger.getPreviousFireTime());
                jobVO.setStartTime(trigger.getStartTime());
                jobVO.setEndTime(trigger.getEndTime());
                jobVO.setMail(mail);
                jobVOS.add(jobVO);
            }
        } catch (SchedulerException e) {
            return jobVOS;
        }
        return jobVOS;
    }

}
