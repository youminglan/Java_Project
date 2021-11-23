package com.dhu.yumail.server.service.api;

import com.dhu.yumail.server.model.base.Session;
import com.dhu.yumail.server.model.request.LoginRequest;
import com.dhu.yumail.server.model.vo.LoginInfoVO;

/**
 * 用户服务
 * @author Yupi Li
 * @date 19/03/16
 */
public interface IUserService {

    /**
     * 用户登录
     * @param loginRequest
     * @return
     */
    LoginInfoVO login(LoginRequest loginRequest);
}
