package com.ieng.huaimi.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     Redis缓存工具类
 * </p>
 */
@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public class RedisCache {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> void setObject(String key, T value){
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void setObject(String key, T value, long timeout){
        setObject(key, value, timeout, TimeUnit.SECONDS);
    }

    public <T> void setObject(String key, T value, long timeout, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }


    public <T> T getObject(String key){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public Boolean expire(String key, long timeout){
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    public Boolean expire(String key, long timeout, TimeUnit timeUnit){
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    public Boolean delObject(String key){
        return redisTemplate.delete(key);
    }

}
