package com.dhu.yumail.server.job;

import com.dhu.yumail.server.model.entity.Mail;
import com.dhu.yumail.server.service.api.ILocalMailService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 测试Job
 *
 * @author Yupi Li
 * @date 19/03/15
 */
@Component

public class LocalSendMailJob implements Job {

    @Resource
    private ILocalMailService localMailService;

    private void before() {
        System.out.println("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        before();
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Mail mail = (Mail) dataMap.get("mail");
        localMailService.sendHtmlMail(mail.getToUser(), mail.getSubject(), mail.getContent());
        after();
    }

    private void after() {
        System.out.println("任务结束执行");
    }
}
