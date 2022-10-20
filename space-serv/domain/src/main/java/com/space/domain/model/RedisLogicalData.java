package com.space.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Redis缓存逻辑删除实体类
 */
@Getter
@Setter
@Accessors(chain = true)
public class RedisLogicalData {
    private LocalDateTime expireTime;
    private Object data;
}
