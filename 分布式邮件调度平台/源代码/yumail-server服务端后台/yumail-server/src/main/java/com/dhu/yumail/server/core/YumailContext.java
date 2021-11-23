package com.dhu.yumail.server.core;

import com.dhu.yumail.server.model.entity.Log;
import com.dhu.yumail.server.model.entity.MailTemplate;
import com.dhu.yumail.server.model.entity.Source;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Yumail全局容器
 * @author Yupi Li
 * @date 19/03/15
 */
public class YumailContext {

    /**
     * 应用和源信息容器
     * key 应用名
     * source 应用源信息
     */
    private static Map<String, Source> nameSourceMap;

    /**
     * 应用日志列表
     * key 应用名
     * List<Log> 日志列表
     */
    private static Map<String, List<Log>> nameLogListMap;

    /**
     * 邮件模板列表
     * key 应用名
     * List<MailTemplate> 模板列表
     */
    private static Map<String, List<MailTemplate>> nameMailTemplateListMap;

    private YumailContext() {
    }

    public static Map<String, Source> getNameSourceMap() {
        if (nameSourceMap == null) {
            nameSourceMap = new HashMap<>(16);
        }
        return nameSourceMap;
    }

    public static Map<String, List<Log>> getNameLogListMap() {
        if (nameLogListMap == null) {
            nameLogListMap = new HashMap<>(16);
        }
        return nameLogListMap;
    }

    public static Map<String, List<MailTemplate>> getNameMailTemplateListMap() {
        if (nameMailTemplateListMap == null) {
            nameMailTemplateListMap = new HashMap<>(16);
        }
        return nameMailTemplateListMap;
    }

}
