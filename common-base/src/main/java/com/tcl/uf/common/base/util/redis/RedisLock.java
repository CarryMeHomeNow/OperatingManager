package com.tcl.uf.common.base.util.redis;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


/**
 * redis分布式锁
 * @author dengyuanheng
 */
public class RedisLock {
    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private RLock lock;

    private String resId;

    private RedisLock(String resId, boolean isFair) {
        this.resId = resId;
        if (isFair) {
            lock = RedissonClientManager.getClient().getFairLock("LOCK_" + resId);
        } else {
            lock = RedissonClientManager.getClient().getLock("LOCK_" + resId);
        }
    }

    public static RedisLock getLock(String resId) {
        return new RedisLock(resId, false);
    }

    public static RedisLock getFairLock(String resId) {
        return new RedisLock(resId, true);
    }

    /**
     * @param waitTime  TimeUnit.SECONDS 等待时长超过该值，就不等了。此时应返回用户，系统繁忙，请稍后再试
     * @param leaseTime TimeUnit.SECONDS 自动释放锁时间，默认30S（源码中也是该值）
     * @return 是否成功取到锁
     */
    public boolean tryLock(int waitTime, int leaseTime) {
        if (waitTime <= 0) {
            waitTime = 60;
        }

        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean tryLock(int waitTime) {
        return tryLock(waitTime, 30);
    }

    public void unLock() {
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
            logger.debug(resId + " release lock");
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("no lock need to release");
            }
        }
    }

    public boolean tryLock() {
        return lock.tryLock();
    }
}
