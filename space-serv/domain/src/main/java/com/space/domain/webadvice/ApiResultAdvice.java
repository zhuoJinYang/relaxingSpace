package com.space.domain.webadvice;

import com.space.domain.annotation.ApiResultAdviceIgnore;
import com.space.domain.model.ApiResult;
import com.space.domain.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 常规API调用统一处理
 *
 * <p>实现ResponseBodyAdvice接口，其实是对加了@RestController(也就是@Controller+@ResponseBody)注解的处理器将要返回的值进行增强处理。
 * 其实也就是采用了AOP的思想，对返回值进行一次修改。
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.space.admin.web")
public class ApiResultAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 不被@ApiResultAdviceIgnore修饰的controller方法返回值将被允许修改
        return !returnType.hasMethodAnnotation(ApiResultAdviceIgnore.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body == null){
            return ApiResult.success();
        }
        if (body instanceof ApiResult<?>) {
            return body;
        }
        // 由于字符串的返回处理优先级比自己实现的这个类优先级要高，因此这里要特别处理，否则会直接返回字符串内容
        if (body instanceof String) {
            return JacksonUtil.toString(ApiResult.success(body));
        }
        return ApiResult.success(body);
    }
}
