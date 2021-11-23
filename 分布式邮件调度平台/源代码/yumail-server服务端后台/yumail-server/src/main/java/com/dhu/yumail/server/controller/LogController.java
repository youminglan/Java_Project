package com.dhu.yumail.server.controller;

import com.dhu.yumail.server.core.YumailContext;
import com.dhu.yumail.server.model.base.Result;
import com.dhu.yumail.server.model.entity.Log;
import com.dhu.yumail.server.service.api.ILogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志控制器
 *
 * @author Yupi Li
 * @date 19/03/16
 */
@RequestMapping("/logs")
@RestController
public class LogController {

    @Resource
    private ILogService logService;

    @GetMapping
    public Result<List<Log>> listAllLogs() {
        List<Log> logs = new ArrayList<>();
        for (List<Log> logList : YumailContext.getNameLogListMap().values()) {
            logs.addAll(logList);
        }
        return Result.success(logs);
    }

    @GetMapping("/{appId}")
    public Result<List<Log>> listLogsByAppId(@PathVariable String appId) {
        return Result.success(YumailContext.getNameLogListMap().getOrDefault(appId, new ArrayList<>()));
    }

    @PostMapping
    public Result addLog(@RequestBody Log log) {
        logService.log(log.getAppId(), log.getContent(), log.getLevel());
        return Result.success();
    }

    /**
     * 持久化
     */
    @PostMapping("/persist/{appId}")
    public Result<String> doPersist(HttpServletResponse response, @PathVariable String appId) {
        return Result.success(logService.doPersist(response, appId));
    }
}
