package com.dhu.yumail.server.service.impl;

import com.dhu.yumail.server.api.YumailClientApi;
import com.dhu.yumail.server.constant.ExceptionEnum;
import com.dhu.yumail.server.job.LocalSendMailJob;
import com.dhu.yumail.server.job.RemoteSendMailJob;
import com.dhu.yumail.server.model.base.BusinessException;
import com.dhu.yumail.server.model.entity.Mail;
import com.dhu.yumail.server.properties.YumailServerProperties;
import com.dhu.yumail.server.service.api.ILocalMailService;
import com.dhu.yumail.server.service.api.ILogService;
import com.dhu.yumail.server.service.api.IRemoteMailService;
import com.dhu.yumail.server.service.api.IQuartzService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 邮件服务实现类
 *
 * @author Yupi Li
 * @date 19/03/15
 */
@Service
public class RemoteMailServiceImpl implements IRemoteMailService {

    @Resource
    private YumailClientApi yumailClientApi;

    @Resource
    private IQuartzService quartzService;

    @Override
    public void sendMail(Mail mail) {
        // 未定时，直接发送
        if (!mail.getTimer()) {
            try {
                yumailClientApi.sendEmail(mail);
            } catch (IOException e) {
                throw new BusinessException(ExceptionEnum.RPC_FAILED);
            }
        } else {
            // 生成远程job
            startSendMailJob(mail);
        }
    }

    private void startSendMailJob(Mail mail) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("mail", mail);
        String uniKey = UUID.randomUUID().toString();
        // 组设置为调度源appId
        quartzService.addJob("sendMail-" + uniKey, mail.getSource(),
                "sendMail-" + uniKey, mail.getSource(),
                RemoteSendMailJob.class, mail.getCrontab(), mail.getStartTime(), mail.getEndTime(), params);
    }

}
