package com.dhu.yumail.server.service.api;

import com.dhu.yumail.server.model.entity.Source;

/**
 * 来源服务
 * @author Yupi Li
 * @date 2019-04-16 14:55
 */
public interface ISourceService {

    /**
     * 添加调度源
     * @param source
     * @return
     */
    void addSource(Source source);

    /**
     * 移除调度源
     * @param appId
     */
    void delSource(String appId);
}
