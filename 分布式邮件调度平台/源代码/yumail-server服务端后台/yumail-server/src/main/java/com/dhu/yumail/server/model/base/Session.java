package com.dhu.yumail.server.model.base;

import com.dhu.yumail.server.constant.ExceptionEnum;
import com.dhu.yumail.server.model.request.LoginRequest;
import com.dhu.yumail.server.properties.YumailServerProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 会话状态
 * @author Yupi Li
 * @date 19/03/16
 */
@Component
@Data
public class Session {

    @Resource
    private YumailServerProperties yumailServerProperties;

    public void validate(LoginRequest loginRequest) {
        YumailServerProperties.AdminProperties adminProperties = yumailServerProperties.getAdmin();
        if (!loginRequest.getUsername().equals(adminProperties.getUsername()) || !loginRequest.getPassword().equals(adminProperties.getPassword())) {
            throw new BusinessException(ExceptionEnum.LOGIN_FAILED);
        }
    }

}
