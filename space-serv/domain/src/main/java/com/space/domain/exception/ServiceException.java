package com.space.domain.exception;

import com.space.domain.constant.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义服务器异常
 */
@Getter
@Setter
public class ServiceException extends RuntimeException{
    private ErrorCode error;
    /**
     * 根据错误代码和对应消息内容抛出异常
     */
    public ServiceException(ErrorCode error) {
        this.error = error;
    }
    /**
     * 根据错误代码,并修改提示消息,抛出异常
     * 可用于某一类错误情况一致,但提示消息有所区别,或提示消息中需要附带动态参数时使用
     */
    public ServiceException(ErrorCode error, String message) {
        this.error = error;
        this.error.setMessage(message);
    }
    /**
     * 自定义错误提示消息,结合默认的错误代码,抛出异常
     * 不建议使用,尽量先定义错误代码枚举项,再通过错误枚举抛出异常
     */
    public ServiceException(String message){
        this.error = ErrorCode.DEFAULT_ERROR;
        this.error.setMessage(message);
    }
}
