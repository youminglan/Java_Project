package com.dhu.yumail.server.model.base;

import com.dhu.yumail.server.constant.ExceptionEnum;
import lombok.Data;

/**
 * 结果类
 * @author Yupi Li
 * @date 19/01/14
 */
@Data
public class Result<T> {

    private String msg;
    private T data;
    private Integer code;

    private Result(String msg, T data, Integer code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>("成功", data, 0);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(msg, data, 0);
    }

    public static Result success() {
        return new Result<>("成功", null, 0);
    }

    public static Result error(Integer code, String msg) {
        return new Result<>(msg, null, code);
    }

    public static Result error(ExceptionEnum ex) {
        return error(ex.getCode(), ex.getMsg());
    }
}
