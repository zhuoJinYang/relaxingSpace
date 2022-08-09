package com.rspace.domain.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) //代表注解被JVM加载后依旧存在(该注解和下面的@Documented这里都可有可无,因为@Target已经被标注)
@Documented //元注解，可以修饰其他注解，生成文档会显示被标注的注解
public @interface ApiResultAdviceIgnore {
}
