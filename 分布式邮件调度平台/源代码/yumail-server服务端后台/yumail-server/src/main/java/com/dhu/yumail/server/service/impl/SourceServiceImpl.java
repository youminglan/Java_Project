package com.dhu.yumail.server.service.impl;

import com.dhu.yumail.server.core.YumailContext;
import com.dhu.yumail.server.model.entity.Log;
import com.dhu.yumail.server.model.entity.MailTemplate;
import com.dhu.yumail.server.model.entity.Source;
import com.dhu.yumail.server.properties.YumailServerProperties;
import com.dhu.yumail.server.service.api.ISourceService;
import com.dhu.yumail.server.utils.Assert;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 调度源服务实现类
 *
 * @author Yupi Li
 * @date 2019-04-16 14:56
 */
@Service
public class SourceServiceImpl implements ISourceService {

    @Resource
    private RedisTemplate<String, Map<String, Source>> redisTemplate;

    @Resource
    private YumailServerProperties yumailServerProperties;

    private Map<String, Source> nameSourceMap = YumailContext.getNameSourceMap();

    /**
     * 接入两步：存入全局map，更新redis
     */
    @Override
    public void addSource(Source source) {
        Assert.notNull(source.getAppId(), source.getHost(), source.getUsername(), source.getUrl());
        String key = source.getAppId();
        // 同key自动覆盖
        nameSourceMap.put(key, source);
        // 更新Redis
        redisTemplate.opsForValue().set(key, nameSourceMap);
        System.out.println(key + "接入成功");
    }

    /**
     * 移除两步：移除全局map对应键，更新redis
     */
    @Override
    public void delSource(String appId) {
        Assert.notNull(appId);
        nameSourceMap.remove(appId);
        // 更新Redis
        redisTemplate.opsForValue().set(yumailServerProperties.getAppId(), nameSourceMap);
    }
}
