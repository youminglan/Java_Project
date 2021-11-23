package com.dhu.yumail.server.model.base;

import com.dhu.yumail.server.constant.ExceptionEnum;

/**
 * 自定义异常
 * @author Yupi Li
 * @date 19/01/14
 */
public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public BusinessException(ExceptionEnum exceptionEnum, String msg) {
        super(msg);
        this.code = exceptionEnum.getCode();
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

