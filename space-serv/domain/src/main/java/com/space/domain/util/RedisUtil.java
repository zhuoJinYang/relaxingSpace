package com.space.domain.util;

import cn.hutool.core.util.BooleanUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 操作工具类
 */
@Component("redisUtil")
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /* ==================================common================================== */

    /**
     * 指定缓存的有效时间
     * 时间:秒
     */
    public boolean setExpire(String key,long time){
        if (time > 0) {
            return BooleanUtil.isTrue(redisTemplate.expire(key,time, TimeUnit.SECONDS));
        }
        return false;
    }

    /**
     * 根据key获取过期时间
     * 返回0代表永久有效
     */
    public long getExpire(String key){
        return Optional.ofNullable(redisTemplate.getExpire(key,TimeUnit.SECONDS))
                .orElseThrow(() -> new RuntimeException("Redis数据不存在"));
    }

    /**
     * 判断key是否存在
     */
    public boolean hasKey(String key){
        return BooleanUtil.isTrue(redisTemplate.hasKey(key));
    }

    /**
     * 删除数据
     */
    public void delete(String key){
        redisTemplate.delete(key);
    }

    /**
     * 批量删除数据
     */
    public void delete(List<String> keys){
        redisTemplate.delete(keys);
    }

    /**
     * 删除一个或多个数据
     */
    public void delete(String... keys){
        if (keys == null || keys.length <= 0) {
            return;
        }
        if(keys.length == 1){
            delete(keys[0]);
        }else {
            delete(Arrays.asList(keys));
        }
    }

    /* =============================String============================= */

    /**
     * 设置String类型key的对象
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置String类型key的对象，附带过期时间
     * 若timeout<=0，则不限制
     */
    public void set(String key, Object value, long time) {
        if (time <= 0){
            set(key, value);
        }
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 设置String类型key的对象，附带过期时间和时间单位
     * 若timeout<=0，则不限制
     */
    public void set(String key, Object value, long time, TimeUnit unit) {
        if (time <= 0){
            set(key, value);
        }
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 若key不存在，设置String类型key的对象，附带过期时间和时间单位
     * 若timeout<=0，则不限制
     */
    public boolean setIf(String key, Object value) {
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value);
        return BooleanUtil.isTrue(flag);
    }

    /**
     * 若key不存在，设置String类型key的对象，附带过期时间和时间单位
     * 若timeout<=0，则不限制
     */
    public boolean setIf(String key, Object value, long time, TimeUnit unit) {
        if (time <= 0){
            return setIf(key, value);
        }
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value, time, unit);
        return BooleanUtil.isTrue(flag);
    }

    /**
     * 获取指定key的对象数据
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 递增
     */
    public long incr(String key,long step){
        if (step < 0){
            throw new RuntimeException("递增因子要大于0");
        }
        return Optional.ofNullable(redisTemplate.opsForValue().increment(key, step))
                .orElseThrow(() -> new RuntimeException("Redis数据递增出错"));
    }

    /**
     * 递减
     */
    public long decr(String key){
        return Optional.ofNullable(redisTemplate.opsForValue().decrement(key))
                .orElseThrow(() -> new RuntimeException("Redis数据递减出错"));
    }

    /**
     * 递减
     */
    public long decr(String key, long step) {
        if (step < 0) {
            throw new RuntimeException("递减因子要大于0");
        }
        return Optional.ofNullable(redisTemplate.opsForValue().decrement(key, step))
                .orElseThrow(() -> new RuntimeException("Redis数据递减出错"));
    }

    /* ============================== Set ============================== */

    /**
     * 添加数据到Set集合中
     */
    public void sAdd(String key,Object... values){
        redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 获取Set集合数据
     */
    public Set<Object> sGet(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 从Set集合中随机获取一个数据
     */
    public Object sRandomGet(String key){
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 从Set集合中随机获取多个数据(可能重复)
     */
    public List<Object> sRandomGet(String key,long count){
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 从Set集合中随机获取多个数据(不重复)
     */
    public Set<Object> sDistinctRandomGet(String key,long count){
        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * 判断Set集合中是否存在指定数据
     */
    public boolean sHasValue(String key, Object value) {
        return hasKey(key) && Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key,value));
    }

    /**
     * 从Set集合中移除数据
     */
    public void sRemove(String key,Object ... values){
        redisTemplate.opsForSet().remove(key, values);
    }
}
