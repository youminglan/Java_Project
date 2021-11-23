package com.yupi.yumail.client.facade;

import com.yupi.yumail.client.api.YumailServerApi;
import com.yupi.yumail.client.constant.ExceptionEnum;
import com.yupi.yumail.client.constant.LogConstant;
import com.yupi.yumail.client.constant.YumailConstant;
import com.yupi.yumail.client.model.base.BusinessException;
import com.yupi.yumail.client.model.base.ServerInfo;
import com.yupi.yumail.client.model.entity.Log;
import com.yupi.yumail.client.model.entity.Mail;
import com.yupi.yumail.client.properties.YumailClientProperties;
import com.yupi.yumail.client.utils.LogTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

/**
 * 邮件服务实现类
 *
 * @author Yupi Li
 * @date 19/03/15
 */
@Component
public class YumailClient {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private YumailClientProperties clientProperties;

    @Resource
    private ServerInfo serverInfo;

    @Resource
    private YumailServerApi yumailServerApi;

    /**
     * 发送邮件
     * @param toUser
     * @param subject
     * @param content
     */
    public void sendMail(String toUser, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(clientProperties.getMail().getUsername());
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        if (serverInfo.getStatus().equals(YumailConstant.SERVER_STATUS_ACTIVE)) {
            // 远程调用-记录日志
            Log log = new Log(clientProperties.getAppId(),
                    LogTemplate.sendMailTemplate(clientProperties.getAppId(),
                            clientProperties.getMail().getUsername(), toUser, content), LogConstant.INFO_LEVEL);
            try {
                yumailServerApi.addLog(log);
            } catch (IOException e) {
                throw new BusinessException(ExceptionEnum.RPC_FAILED);
            }
        }
    }

    /**
     * 开启发送邮件Job
     * @param toUser
     * @param subject
     * @param content
     * @param startTime
     * @param endTime
     * @param crontab
     */
    public void startSendMailJob(String toUser, String subject, String content, Date startTime, Date endTime, String crontab) {
        Mail mail = new Mail();
        mail.setToUser(toUser);
        mail.setContent(content);
        mail.setSubject(subject);
        mail.setStartTime(startTime);
        mail.setEndTime(endTime);
        mail.setCrontab(crontab);
        mail.setTimer(true);
        mail.setSource(clientProperties.getAppId());
        // 远程调用-开启任务
        try {
            yumailServerApi.addJob(mail);
        } catch (IOException e) {
            throw new BusinessException(ExceptionEnum.RPC_FAILED);
        }
    }


}
