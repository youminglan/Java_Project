package com.dhu.yumail.server.controller;

import com.dhu.yumail.server.core.YumailContext;
import com.dhu.yumail.server.model.base.Result;
import com.dhu.yumail.server.model.entity.Source;
import com.dhu.yumail.server.service.api.ISourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 调度源控制器
 * @author Yupi Li
 * @date 19/03/15
 */
@RequestMapping("/sources")
@RestController
public class SourceController {

    @Resource
    private ISourceService sourceService;

    @GetMapping
    public Result<List<Source>> listSources() {
        return Result.success(new ArrayList<>(YumailContext.getNameSourceMap().values()));
    }

    @GetMapping("/app-ids")
    public Result<Set<String>> listAppIds() {
        return Result.success(YumailContext.getNameSourceMap().keySet());
    }

    /**
     * 客户端接入，添加调度源（远程调用）
     */
    @PutMapping
    public Result addSource(@RequestBody Source source) {
        sourceService.addSource(source);
        return Result.success();
    }

}
