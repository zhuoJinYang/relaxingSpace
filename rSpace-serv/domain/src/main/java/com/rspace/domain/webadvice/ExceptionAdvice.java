package com.rspace.domain.webadvice;

import com.rspace.domain.constant.ErrorCode;
import com.rspace.domain.exception.ServiceException;
import com.rspace.domain.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常统一处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    // @Order控制配置类的加载顺序,num越小,优先度越高
    // @ExceptionHandler注解一般是用来自定义异常的,可以认为它是一个异常拦截器（处理器）
    @Order(1)
    @ExceptionHandler(ServiceException.class)
    public ApiResult<?> exceptionHandler(ServiceException e) {
        ErrorCode error = e.getError();
        log.error("全局拦截自定义异常 ServiceException: {} {}", error.getCode(), error.getMessage());
        return ApiResult.error(error);
    }

    @ExceptionHandler(BindException.class)
    public ApiResult<?> MethodArgumentNotValidExceptionHandler(BindException e) {
        log.error("全局拦截异常 BindException: {}", e.getMessage());
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return ApiResult.error(ErrorCode.ARGUMENT_VALID_ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResult<?> exceptionHandler(HttpMessageNotReadableException e) {
        log.error("全局拦截异常 HttpMessageNotReadableException: {}", e.getMessage());
        return ApiResult.error(ErrorCode.ARGUMENT_NOT_EXIST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResult<?> exceptionHandler(RuntimeException e) {
        log.error("全局拦截异常 RuntimeException: {}", e.getMessage(), e);
        return ApiResult.error(ErrorCode.DEFAULT_ERROR, e.getMessage());
    }
}
