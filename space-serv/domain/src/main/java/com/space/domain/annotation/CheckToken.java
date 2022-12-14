package com.space.domain.annotation;

import com.space.domain.constant.RoleType;

import java.lang.annotation.*;

/**
 * <p>@Retention作用是定义被它所注解的注解保留多久,一共有三种策略:<p/>
 *      source：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；被编译器忽略
 *      class：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期
 *      runtime：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckToken {

    /**
     * 只有这里指定的角色类型有接口的访问权限，默认无，表示所有角色都可以访问。
     */
    RoleType[] permissionRoles() default {};
}
