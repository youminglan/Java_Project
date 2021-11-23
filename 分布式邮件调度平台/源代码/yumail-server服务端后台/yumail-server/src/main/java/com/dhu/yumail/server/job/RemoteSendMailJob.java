package com.dhu.yumail.server.job;

import com.dhu.yumail.server.api.YumailClientApi;
import com.dhu.yumail.server.model.entity.Mail;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 测试Job
 * @author Yupi Li
 * @date 19/03/15
 */
@Component

public class RemoteSendMailJob implements Job {

    @Resource
    private YumailClientApi yumailClientApi;

    private void before() {
        System.out.println("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        before();
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Mail mail = (Mail) dataMap.get("mail");
        try {
            // 远程调用
            yumailClientApi.sendEmail(mail);
        } catch (IOException e) {
            e.printStackTrace();
        }
        after();
    }

    private void after() {
        System.out.println("任务结束执行");
    }
}
