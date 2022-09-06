package com.space.domain.model;

import com.space.domain.constant.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义API请求返回结果实体类
 */
@Getter
@Setter
@ToString
public class ApiResult<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 提示消息
     */
    private String message;
    /**
     * 返回结果
     */
    private T data;

    public ApiResult(int code,String message){
        this.code = code;
        this.message = message;
    }

    public ApiResult(ErrorCode error){
        this(error.getCode(),error.getMessage());
    }

    public ApiResult(T data){
        this(ErrorCode.SUCCESS);
        this.data = data;
    }

    public static <T> ApiResult<T> success(T data){
        return new ApiResult<T>(data);
    }

    public static <T> ApiResult<T> success(){
        return success(null);
    }

    public static <T> ApiResult<T> error(ErrorCode error){
        return new ApiResult<T>(error);
    }

    public static <T> ApiResult<T> error(ErrorCode error, String message){
        error.setMessage(message);
        return error(error);
    }
}
