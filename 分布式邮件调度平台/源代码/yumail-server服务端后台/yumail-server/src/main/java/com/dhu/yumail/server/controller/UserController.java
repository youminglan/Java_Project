package com.dhu.yumail.server.controller;

import com.dhu.yumail.server.model.base.Result;
import com.dhu.yumail.server.model.base.Session;
import com.dhu.yumail.server.model.request.LoginRequest;
import com.dhu.yumail.server.model.vo.LoginInfoVO;
import com.dhu.yumail.server.service.api.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户控制器
 * @author Yupi Li
 * @date 19/03/16
 */
@RequestMapping("/users")
@RestController
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result<LoginInfoVO> login(@RequestBody LoginRequest loginRequest) {
        return Result.success(userService.login(loginRequest));
    }

}
