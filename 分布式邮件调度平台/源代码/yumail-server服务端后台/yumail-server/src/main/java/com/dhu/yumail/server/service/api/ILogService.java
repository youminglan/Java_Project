package com.dhu.yumail.server.service.api;

import javax.servlet.http.HttpServletResponse;

/**
 * 日志服务
 * @author Yupi Li
 * @date 19/03/16
 */
public interface ILogService {

    void error(String appId, String content);

    void warning(String appId, String content);

    void info(String appId, String content);

    void log(String appId, String content, Integer level);

    /**
     * 持久化
     * @param response
     * @param appId
     */
    String doPersist(HttpServletResponse response, String appId);

}
