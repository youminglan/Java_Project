package com.yupi.yumail.client.api;

import com.alibaba.fastjson.JSONObject;
import com.yupi.yumail.client.constant.ExceptionEnum;
import com.yupi.yumail.client.constant.YumailConstant;
import com.yupi.yumail.client.model.base.BusinessException;
import com.yupi.yumail.client.model.base.ServerInfo;
import com.yupi.yumail.client.model.entity.Log;
import com.yupi.yumail.client.model.entity.Mail;
import com.yupi.yumail.client.model.entity.Source;
import com.yupi.yumail.client.utils.HttpClientUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 功能描述：远程调用Yumail Server的接口
 *
 * @author Yupi Li
 * @date 2019/4/20 0:31
 */
@Component
public class YumailServerApi {

    @Resource
    private ServerInfo serverInfo;

    public void heartCheck() {
        try {
            HttpClientUtils.get(serverInfo.getUrl() + "/heart");
        } catch (Exception e) {
            serverInfo.setStatus(YumailConstant.SERVER_STATUS_BREAK);
            throw new BusinessException(ExceptionEnum.SERVER_HEART_CHECK_FAILED);
        }
    }

    public void addSource(Source source) throws IOException {
        heartCheck();
        HttpClientUtils.put(serverInfo.getUrl() + "/sources", JSONObject.toJSONString(source));
    }

    public void addJob(Mail mail) throws IOException {
        heartCheck();
        HttpClientUtils.post(serverInfo.getUrl() + "/jobs", JSONObject.toJSONString(mail));
    }

    public void addLog(Log log) throws IOException {
        heartCheck();
        HttpClientUtils.post(serverInfo.getUrl() + "/logs", JSONObject.toJSONString(log));
    }
}
