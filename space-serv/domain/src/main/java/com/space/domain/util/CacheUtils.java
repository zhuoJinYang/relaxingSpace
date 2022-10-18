package com.space.domain.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.space.domain.constant.Constant;
import com.space.domain.model.RedisData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * redis缓存工具类
 */
@Component
@Slf4j
public class CacheUtils {

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = new ThreadPoolExecutor(
            0,10,60L,TimeUnit.SECONDS,new LinkedBlockingQueue<>()
    );

    @Resource
    private RedisUtil redisUtil;

    /**
     * 设置逻辑过期数据
     * @param key key
     * @param value 添加逻辑过期的数据
     * @param time 逻辑过期时间
     * @param unit 逻辑过期时间单位
     */
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit){
        RedisData redisData = new RedisData().setData(value)
                .setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        redisUtil.set(key,redisData);
    }

    /**
     * 缓存查找(缓存穿透结果方案)
     * @param keyPrefix key前缀
     * @param id id
     * @param type 类型
     * @param dbFallback 数据库查询方法
     * @param time 过期时间
     * @param unit 时间单位
     * @param <R> class类型
     * @param <ID> id类型
     * @return redis查询到的结果/redis中没有则返回数据库查询到的结果
     */
    public <R,ID> R queryWithPassThrough(
            String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit
    ){
        String key = keyPrefix + id;
        Object o = redisUtil.get(key);
        // 若缓存存在,直接返回
        if (ObjectUtil.isNotEmpty(o)){
            return BeanUtil.toBean(o,type);
        }
        R r = dbFallback.apply(id);
        // 若数据库无数据,缓存空值
        if (ObjectUtil.isEmpty(r)){
            redisUtil.set(key,"", Constant.CACHE_NULL_TTL);
            return null;
        }
        redisUtil.set(key, r, time, unit);
        return r;
    }

    /**
     * 缓存查找(通过互斥锁解决缓存击穿)
     * @param keyPrefix key前缀
     * @param id id
     * @param type 类型
     * @param dbFallback 数据库查询
     * @param time 过期时间
     * @param unit 时间单位
     * @param <R> class类型
     * @param <ID> id类型
     * @return redis查询到的结果/redis中没有则返回数据库查询到的结果
     */
    public <R, ID> R queryWithMutex(
            String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit
    ){
        String key = keyPrefix + id;
        Object o = redisUtil.get(key);
        if (ObjectUtil.isNotEmpty(o)){
            return BeanUtil.toBean(o, type);
        }
        if (o != null){
            return null;
        }
        String lockKey = "lock:" + keyPrefix + id;
        R r = null;
        try {
            boolean isLock = tryLock(lockKey);
            if (!isLock){
                Thread.sleep(50);
                return queryWithMutex(keyPrefix, id, type, dbFallback, time, unit);
            }
            r = dbFallback.apply(id);
            if (ObjectUtil.isEmpty(r)){
                redisUtil.set(key,"", Constant.CACHE_NULL_TTL);
                return null;
            }
            redisUtil.set(key, r, time, unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            unlock(lockKey);
        }
        return r;
    }

    /**
     * 缓存查找(通过逻辑过期解决缓存击穿)
     * @param keyPrefix key前缀
     * @param id id
     * @param type 类型
     * @param dbFallback 数据库查询
     * @param time 过期时间
     * @param unit 时间单位
     * @param <R> class类型
     * @param <ID> id类型
     * @return redis查询到的结果/redis中没有则返回数据库查询到的结果
     */
    public <R, ID> R queryWithLogicalExpire(
            String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit
    ){
        String key = keyPrefix + id;
        Object o = redisUtil.get(key);
        if (ObjectUtil.isEmpty(o)){
            return null;
        }
        RedisData redisData = BeanUtil.toBean(o, RedisData.class);
        R r = BeanUtil.toBean(redisData.getData(), type);
        LocalDateTime expireTime = redisData.getExpireTime();
        if (expireTime.isAfter(LocalDateTime.now())){
            return r;
        }
        String lockKey = "lock:" + keyPrefix + id;
        boolean isLock = tryLock(lockKey);
        if (isLock) {
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
                    R newR = dbFallback.apply(id);
                    this.setWithLogicalExpire(key,newR,time,unit);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    unlock(lockKey);
                }
            });
        }
        return r;
    }

    private boolean tryLock(String key){
        return redisUtil.setIf(key, "1", 10, TimeUnit.SECONDS);
    }

    private void unlock(String key) {
        redisUtil.delete(key);
    }
}
