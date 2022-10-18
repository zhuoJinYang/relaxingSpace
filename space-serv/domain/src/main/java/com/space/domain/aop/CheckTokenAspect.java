package com.space.domain.aop;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.space.db.entity.Session;
import com.space.domain.annotation.CheckToken;
import com.space.domain.constant.ErrorCode;
import com.space.domain.constant.RedisOption;
import com.space.domain.constant.RoleType;
import com.space.domain.exception.ServiceException;
import com.space.domain.util.CurrentRequestHolder;
import com.space.domain.util.HttpRequestUtil;
import com.space.domain.util.JacksonUtil;
import com.space.domain.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Order(1000)
@Aspect
@Component
public class CheckTokenAspect {

    @Resource
    private RedisUtil redisUtil;

    @Pointcut("@within(com.space.domain.annotation.CheckToken)")
    public void pointcutType(){}

    @Pointcut("@within(com.space.domain.annotation.CheckToken)")
    public void pointcutMethod(){}

    @Before("pointcutType() || pointcutMethod()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = HttpRequestUtil.getRequest();
        String token = HttpRequestUtil.getHeader(request, HttpHeaders.AUTHORIZATION);

        log.info("CheckToken: {}",token);

        Session session = this.verify(token);
        // 查找session中的用户类型
//        RoleType loginUserRoleType = RoleType.of(session.getLoginUserRoleType());

        // 接口可能会限制访问角色，检查访问权限
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CheckToken anno = this.getAnnotation(method);
        RoleType[] permissionRoles = anno.permissionRoles();
        // 如果指定了接口访问权限的角色类型，则需判断当前角色是否有权限
//        if (ArrayUtil.isNotEmpty(permissionRoles) && !ArrayUtil.contains(permissionRoles,loginUserRoleType)){
//            throw new ServiceException(ErrorCode.NO_PERMISSION);
//        }

        CurrentRequestHolder.setLoginUserId(session.getLoginUserId());
//        CurrentRequestHolder.setLoginUserRoleType(loginUserRoleType);
    }

    @AfterReturning("pointcutType() || pointcutMethod()")
    public void doAfterReturning(){
        CurrentRequestHolder.removeLoginUserId();
        CurrentRequestHolder.removeLoginUserRoleType();
    }

    @AfterThrowing("pointcutType() || pointcutMethod()")
    public void doAfterThrowing() {
        CurrentRequestHolder.removeLoginUserId();
        CurrentRequestHolder.removeLoginUserRoleType();
    }

    private CheckToken getAnnotation(Method method){
        // 方法上有注解则获取方法上的，没有则获取类上的
        // 不存在两者都没有的情况，都没有的话也不会进入到拦截器中
        if(method.isAnnotationPresent(CheckToken.class)){
            return method.getAnnotation(CheckToken.class);
        }else {
            return method.getDeclaringClass().getAnnotation(CheckToken.class);
        }
    }

    /**
     * 校验token有效性
     */
    private Session verify(String token){
        if(StrUtil.isBlank(token)){
            log.info("Token为空");
            log.error("Token为空");
            throw new ServiceException(ErrorCode.INVALID_TOKEN);
        }
        // 检查Redis中是否存在，存在则认为有效，未过期
        String key = RedisOption.TOKEN.getKey(token);
        if (!redisUtil.hasKey(key)){
            log.info("Redis中不存在");
            log.error("Redis中不存在");
            throw new ServiceException(ErrorCode.INVALID_TOKEN);
        }
        Object value = redisUtil.get(key);
        Session session = JacksonUtil.toObject(value,Session.class);
        if (ObjectUtil.isNotNull(session)){
            log.info("value转session对象失败");
            log.error("value转session对象失败");
            throw new ServiceException(ErrorCode.INVALID_TOKEN);
        }
        // 验证完成后,刷新有效时间
        this.refresh(session);
        return session;
    }

    /**
     * 刷新会话有效时间
     */
    private void refresh(Session session){
        redisUtil.setExpire(RedisOption.TOKEN.getKey(session.getToken()),RedisOption.TOKEN.getTimeout());
//        redisUtil.setExpire(RedisOption.ACCOUNT_TOKEN.getKey(session.getUserUsername() + ":" + session.getToken()),
//                RedisOption.ACCOUNT_TOKEN.getTimeout());
    }
}
