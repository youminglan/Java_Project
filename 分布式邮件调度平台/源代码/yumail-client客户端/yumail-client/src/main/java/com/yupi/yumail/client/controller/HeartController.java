package com.yupi.yumail.client.controller;

import com.yupi.yumail.client.model.base.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 心跳检测控制器
 * @author Yupi Li
 * @date 19/04/19
 */
@RestController
@RequestMapping("/heart")
public class HeartController {

    /**
     * 心跳检测
     * @return
     */
    @GetMapping
    public Result<Integer> heartCheck() {
        return Result.success(1);
    }

}
