package com.space.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Redis 缓存数据选项
 */
@AllArgsConstructor
@Getter
public enum RedisOption {
    TOKEN("token:","登录凭证",24*60*60L),
    ACCOUNT_TOKEN("account:","账号凭证",24*60*60L),
    PHONE("phone:","手机验证码",60L),
    BLOG("blog:","博客详情",3*60L),
    FOLLOW("follows:","关注列表",-1L);

    /**
     * Redis Key 或 Key 的前缀
     */
    private final String key;

    /**
     * 描述
     */
    private final String message;

    /**
     * 超时时间
     */
    private final long timeout;

    RedisOption(String key,String message){
        this(key,message,0L);
    }

    public String getKey(String value) {
        return key + value;
    }
}
