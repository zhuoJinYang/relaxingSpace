package com.space.domain.constant;

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
    NO_MATCH_ENUM_ITEM(1002,"没有匹配的枚举数据"),

    DATABASE_INSERT_FAIL(1100, "新增数据失败"),
    DATABASE_UPDATE_FAIL(1101, "更新数据失败"),
    DATABASE_DELETE_FAIL(1102, "删除数据失败"),
    DATABASE_NULL_DATA(1111,"查询数据无结果"),

    FILE_SAVE_FAIL(1200,"文件上传保存失败"),
    FILE_NOT_EXIT(1202,"文件不存在"),
    FILE_DOWNLOAD_FAIL(1203,"文件下载失败"),

    CAPTCHA_VERIFY_ERROR(1300,"验证码错误"),

    INVALID_TOKEN(2000,"无效的登录凭证"),
    USERNAME_PASSWORD_ERROR(2001,"用户名或密码错误"),
    PASSWORD_NULL(2002,"密码不能为空"),
    ACCOUNT_UNAVAILABLE(2003,"账号不可用"),
    ACCOUNT_USERNAME_EXISTED(2004,"账号已存在"),
    ACCOUNT_PASSWORD_UPDATE_ERROR(2005,"密码重置失败"),
    PASSWORD_DECRYPT_ERROR(2099,"密码解密错误"),

    NO_PERMISSION(2100,"没有访问或操作权限"),



    USER_NOT_EXIST(3000,"用户信息不存在"),
    CURRENT_PASSWORD_ERROR(3001,"当前密码错误");



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
