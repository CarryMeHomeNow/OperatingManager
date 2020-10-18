package com.tcl.uf.common.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * redis分布式锁
 * @author dengyuanheng
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DRedisLock {
    /**
     * 分布式锁 key ，支持 #p0 参数索引的 EL 表达式解析
     */
    String key();

    /**
     * key 的前缀，不填默认为 className_methodName ，最终生成的 key 是 prefixKey_key
     */
    String prefixKey() default "";

    /**
     * 获取不到锁时，等待时长
     */
    int waitSeconds() default 6;

    /**
     * 自动释放锁的时间
     */
    int leaseSeconds() default 30;
}
