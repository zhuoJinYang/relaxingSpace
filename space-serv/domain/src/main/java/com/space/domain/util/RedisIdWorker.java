package com.space.domain.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Redis生成Id工具类
 */
@Component
public class RedisIdWorker {

    // 默认开始时间戳
    private static final long BEGIN_TIMESTAMP = 923702400L;

    // 序列化位数
    private static final int COUNT_BITS = 32;

    @Resource
    private RedisUtil redisUtil;

    public long nextId(String keyPrefix) {
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSecond - BEGIN_TIMESTAMP;
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        long count = redisUtil.incr("incr:" + keyPrefix + ":" + date, 1);
        return timestamp << COUNT_BITS | count;
    }
}
