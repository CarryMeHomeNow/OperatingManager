package com.tcl.uf.common.base.manage;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author youyun.xu
 * @ClassName: RedisManager
 * @Description:
 * @date 2018/4/1 下午3:40
 */

public class RedisManager {

    private StringRedisTemplate redisTemplate;
    private static RedisManager redisManager;

    /**
     * 功能：构造函数初始化StringRedisTemplate
     * */
    public RedisManager(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisManager = this;
    }

    /**
     * 功能：向REDIS里存入数据
     * */
    public static void set(String key, String value) {
        redisManager.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 功能：向REDIS里存入数据和设置缓存时间  
     * */
    public static void set(String key, String value, int second) {
        redisManager.redisTemplate.opsForValue().set(key, value, new Integer(second).longValue(), TimeUnit.SECONDS);
    }

    /**
     * 功能：根据KEY获取存储的元素
     * */
    public static String get(String key) {
        return redisManager.redisTemplate.opsForValue().get(key);
    }

    /**
     * 功能：根据KEY删除对应元素
     * */
    public static void del(String key) {
        redisManager.redisTemplate.delete(key);
    }
    
    /**
     * 功能：VAL做-1操作
     * */
    public static void valueSubstract(String key) {
    	redisManager.redisTemplate.boundValueOps(key).increment(-1);
    }
    
    /**
     * 功能：VAL做+1操作
     * */
    public static void valueAdd(String key) {
    	redisManager.redisTemplate.boundValueOps(key).increment(1);
    }
    
    /**
     * 功能：根据key获取过期时间
     * */
    public static long getExpire(String key) {
    	return redisManager.redisTemplate.getExpire(key);
    }
    
    /**
     * 功能：根据key获取过期时间并换算成指定单位 (默认秒)
     * */
    public static long getExpireByTimeUnit(String key) {
    	return redisManager.redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }
    
    /**
     * 功能：
     * */
    public static boolean hasKey(String key) {
    	return redisManager.redisTemplate.hasKey(key);//检查key是否存在，返回boolean值 
    }
    
    /**
     * 功能：向指定key中存放set集合
     * */
    public static long opsForSetAdd(String key,String... params) {
    	return redisManager.redisTemplate.opsForSet().add(key, params);
    }
    
    /**
     * 功能：设置过期时间
     * */
    public static boolean setExpire(String key,long timeout) {
    	
    	return redisManager.redisTemplate.expire(key,1000, TimeUnit.MILLISECONDS);
    }
    
    /**
     * 功能：根据key查看集合中是否存在指定数据
     * */
    public static boolean isMember(String key,Object element) {
    	return redisManager.redisTemplate.opsForSet().isMember(key, element);
    }
    
    /**
     * 功能：根据key获取set集合
     * */
    public static Set<String> getMemberByKey(String key) {
    	return redisManager.redisTemplate.opsForSet().members(key);
    }
}
