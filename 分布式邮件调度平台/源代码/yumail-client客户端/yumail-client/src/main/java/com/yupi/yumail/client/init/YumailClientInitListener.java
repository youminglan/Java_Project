package com.yupi.yumail.client.init;

import com.yupi.yumail.client.api.YumailServerApi;
import com.yupi.yumail.client.constant.ExceptionEnum;
import com.yupi.yumail.client.model.base.BusinessException;
import com.yupi.yumail.client.model.base.ServerInfo;
import com.yupi.yumail.client.model.entity.Source;
import com.yupi.yumail.client.properties.YumailClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Yumail客户端初始化（接入server）
 * @author Yupi Li
 * @date 19/03/16
 */
@Component
public class YumailClientInitListener implements ApplicationListener<ContextRefreshedEvent> {

    private final static Logger logger = LoggerFactory.getLogger(YumailClientInitListener.class);

    @Resource
    private YumailClientProperties yumailClientProperties;

    @Resource
    private ServerInfo serverInfo;

    @Resource
    private YumailServerApi yumailServerApi;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 接入server
        Source source = new Source();
        source.setAppId(yumailClientProperties.getAppId());
        source.setHost(yumailClientProperties.getMail().getHost());
        source.setUsername(yumailClientProperties.getMail().getUsername());
        String host = null;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        source.setUrl(String.format("http://%s:%s%s", host, port, contextPath));
        try {
            // 调用接入接口
            yumailServerApi.addSource(source);
            serverInfo.setStatus(1);
            logger.info("应用接入Server成功！");
        } catch (IOException e) {
            if (yumailClientProperties.getForce()) {
                throw new BusinessException(ExceptionEnum.SERVER_CONNECTION_FAILED);
            } else {
                logger.error(ExceptionEnum.SERVER_CONNECTION_FAILED.getMsg());
            }
        }
    }
}
