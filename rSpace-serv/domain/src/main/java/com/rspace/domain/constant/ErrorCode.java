package com.rspace.domain.constant;

import lombok.Getter;

/**
 * 自定义异常错误信息
 */
@Getter
public enum ErrorCode {
    SUCCESS(0,"success"),
    DEFAULT_ERROR(9999,"服务异常"),
    /**
     * 自定义异常
     * 错误代码范围: 1000-9999
     * 1000-1999 特殊异常（非业务异常）
     * 2000-2999 特殊业务异常
     * 3000-8999 常规业务异常
     * 9000-9999 保留特殊情况使用
     */
    ARGUMENT_VALID_ERROR(1000, "参数校验异常"),
    ARGUMENT_NOT_EXIST(1001, "无法获取请求参数"),

    USER_NOT_EXIST(3000,"用户信息不存在");



    private final int code;
    private String message;

    ErrorCode(int code,String message){
        this.code = code;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
