package com.dhu.yumail.server.controller;

import com.dhu.yumail.server.core.YumailContext;
import com.dhu.yumail.server.model.base.Result;
import com.dhu.yumail.server.model.entity.Log;
import com.dhu.yumail.server.model.entity.MailTemplate;
import com.dhu.yumail.server.utils.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 邮件模板控制器
 *
 * @author Yupi Li
 * @date 19/03/16
 */
@RequestMapping("/mail-templates")
@RestController
public class MailTemplateController {

    @GetMapping
    public Result<List<MailTemplate>> listAllMailTemplates() {
        List<MailTemplate> mailTemplates = new ArrayList<>();
        for (List<MailTemplate> mailTemplateList : YumailContext.getNameMailTemplateListMap().values()) {
            mailTemplates.addAll(mailTemplateList);
        }
        return Result.success(mailTemplates);
    }

    @GetMapping("/{appId}")
    public Result<List<MailTemplate>> listMailTemplatesByAppId(@PathVariable String appId) {
        return Result.success(YumailContext.getNameMailTemplateListMap().getOrDefault(appId, new ArrayList<>()));
    }

    @PostMapping
    public Result addMailTemplate(@RequestBody MailTemplate mailTemplate) {
        String appId = mailTemplate.getAppId();
        Assert.notNull(appId, mailTemplate.getName(), mailTemplate.getMdValue(), mailTemplate.getContent());
        Map<String, List<MailTemplate>> map = YumailContext.getNameMailTemplateListMap();
        mailTemplate.setCreateTime(new Date());
        List<MailTemplate> mailTemplateList = map.getOrDefault(appId, new ArrayList<>());
        mailTemplateList.add(mailTemplate);
        map.put(appId, mailTemplateList);
        return Result.success();
    }
}
