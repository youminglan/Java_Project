package com.dhu.yumail.server.constant;

/**
 * 异常枚举类
 * @author Yupi Li
 * @date 19/01/14
 */
public enum ExceptionEnum {

    LOGIN_FAILED(2, "用户名或密码错误"),

    USER_ALREADY_EXISTS(1001, "用户已经存在"),

    CAPTCHA_ERROR(1005, "验证码错误"),

    EMAIL_VALIDATE_FAILED(1006, "邮箱格式错误"),

    MOBILE_VALIDATE_FAILED(1007, "手机号格式错误"),

    PASSWORD_VALIDATE_FAILED(1008, "密码格式错误"),

    QUARTZ_ERROR(10001, "定时任务调度失败"),

    CLIENT_HEART_CHECK_FAILED(10004, "客户端心跳检测失败"),

    NO_RESULT(4, "查找不到结果"),

    NOT_LOGIN(5, "未登录"),

    NO_TOKEN(6, "Token为空"),

    NETWORK_CONGESTION(8, "网络拥堵"),

    RPC_FAILED(9, "远程调用错误"),

    HTTP_REQUEST_ERROR_CODE(10, "HTTP请求状态码错误"),

    ARRAY_EMPTY(80, "数组为空"),

    NOT_NULL(85, "值不为空"),

    PARAM_ERROR(86, "参数错误"),

    NO_DATA_DELETE(87, "无数据删除"),

    NO_DATA_UPDATE(88, "无数据更新"),

    DATA_FORMAT_ERROR(89, "数据格式错误"),

    PARAM_NULL(90, "参数为空"),

    GET_LOGIN_USER_ID_FAILED(91, "获取登录用户id失败"),

    REPETITIVE_OPERATION(98, "重复操作"),

    SYSTEM_EXCEPTION(99, "系统异常"),

    NO_AUTH(401, "无权限");


    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}