package com.dhu.yumail.server.aop;

import com.dhu.yumail.server.model.base.BusinessException;
import com.dhu.yumail.server.model.base.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 功能描述：全局异常处理器
 * @author Yupi Li
 * @date 2018/10/29 11:40
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(BusinessException ex) {
        logger.error("业务异常 -- 错误码：" + ex.getCode() + " 错误信息：" + ex.getMessage());
        return Result.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(Exception ex) {
        logger.error("运行时异常 -- 错误信息：" + ex.getMessage());
        return Result.error(99, ex.getMessage());
    }

}