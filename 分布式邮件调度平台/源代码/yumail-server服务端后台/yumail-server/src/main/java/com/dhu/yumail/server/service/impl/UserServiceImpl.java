package com.dhu.yumail.server.service.impl;

import com.dhu.yumail.server.model.base.Session;
import com.dhu.yumail.server.model.request.LoginRequest;
import com.dhu.yumail.server.model.vo.LoginInfoVO;
import com.dhu.yumail.server.service.api.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 用户服务实现类
 * @author Yupi Li
 * @date 19/03/16
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private Session session;

    @Override
    public LoginInfoVO login(LoginRequest loginRequest) {
        this.session.validate(loginRequest);
        String token = UUID.randomUUID().toString();
        String uuid = loginRequest.getUsername();
        return new LoginInfoVO(token, uuid, loginRequest.getUsername());
    }
}
