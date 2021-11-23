package com.dhu.yumail.server.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录信息包装类
 * @author Yupi Li
 * @date 19/03/16
 */
@Data
public class LoginInfoVO implements Serializable {

    private String token;

    private String uuid;

    private String name;

    private static final long serialVersionUID = 7574814229092935272L;

    public LoginInfoVO(String token, String uuid, String name) {
        this.token = token;
        this.uuid = uuid;
        this.name = name;
    }
}
