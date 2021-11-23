package com.dhu.yumail.server.core;

import com.dhu.yumail.server.model.entity.Source;
import com.dhu.yumail.server.properties.YumailServerProperties;
import com.dhu.yumail.server.service.api.ISourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Yumail容器初始化
 *
 * @author Yupi Li
 * @date 19/03/16
 */
@Component
public class YumailContextInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private YumailServerProperties yumailServerProperties;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Resource
    private ISourceService sourceService;

    @Resource
    private RedisTemplate<String, Map<String, Source>> redisTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 项目启动，从redis中拉取之前缓存的调度源map
        String key = yumailServerProperties.getAppId();
        Map<String, Source> map = redisTemplate.opsForValue().get(key);
        // 初始化YumailContext
        // nameSourceMap
        Map<String, Source> nameSourceMap = YumailContext.getNameSourceMap();
        if (map != null) {
            nameSourceMap.putAll(map);
        }
        // 添加本地调度源
        Source source = new Source();
        source.setAppId(yumailServerProperties.getAppId());
        source.setUsername(yumailServerProperties.getMail().getUsername());
        source.setHost(yumailServerProperties.getMail().getHost());
        source.setUrl("http://localhost:" + port + contextPath);
        sourceService.addSource(source);
    }
}
