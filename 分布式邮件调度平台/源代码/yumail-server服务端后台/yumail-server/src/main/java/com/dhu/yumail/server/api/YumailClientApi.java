package com.dhu.yumail.server.api;

import com.alibaba.fastjson.JSONObject;
import com.dhu.yumail.server.constant.ExceptionEnum;
import com.dhu.yumail.server.core.YumailContext;
import com.dhu.yumail.server.model.base.BusinessException;
import com.dhu.yumail.server.model.entity.Mail;
import com.dhu.yumail.server.model.entity.Source;
import com.dhu.yumail.server.service.api.ISourceService;
import com.dhu.yumail.server.utils.HttpClientUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * 功能描述：远程调用Yumail Client的接口
 *
 * @author Yupi Li
 * @date 2019/4/20 0:31
 */
@Component
public class YumailClientApi {

    private Map<String, Source> nameSourceMap = YumailContext.getNameSourceMap();

    @Resource
    private ISourceService sourceService;

    public void heartCheck(String appId) {
        try {
            HttpClientUtils.get(nameSourceMap.get(appId).getUrl() + "/heart");
        } catch (IOException e) {
            sourceService.delSource(appId);
            throw new BusinessException(ExceptionEnum.CLIENT_HEART_CHECK_FAILED);
        }
    }

    public void sendEmail(Mail mail) throws IOException {
        heartCheck(mail.getSource());
        HttpClientUtils.post(nameSourceMap.get(mail.getSource()).getUrl() + "/mails", JSONObject.toJSONString(mail));
    }
}
