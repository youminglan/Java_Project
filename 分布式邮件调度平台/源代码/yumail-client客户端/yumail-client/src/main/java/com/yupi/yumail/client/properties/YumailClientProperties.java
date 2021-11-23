package com.yupi.yumail.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Yumail客户端属性文件
 * @author Yupi Li
 * @date 2019-04-16 17:33
 */
@Data
@Component
@ConfigurationProperties(prefix = "yumail")
public class YumailClientProperties {

    private String appId;

    private MailProperties mail = new MailProperties();

    private ServerProperties server = new ServerProperties();

    private Boolean force = false;

    @Data
    public class MailProperties {

        private String host;

        private String username;

        private String password;

    }

    @Data
    public class ServerProperties {
        private String url;
    }


}
