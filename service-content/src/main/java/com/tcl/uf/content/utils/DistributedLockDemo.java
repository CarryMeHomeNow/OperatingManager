package com.tcl.uf.content.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistributedLockDemo {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 此函数主要用于演示分布式锁如何使用
     */
    public void example() {

        String lockKey = "DEMO_DISTRIBUTEDLOCK_KEY";
        String lockValue = "DEMO_DISTRIBUTEDLOCK_VALUE";
        boolean lock = redisUtils.tryLock(lockKey, lockValue, 100, 100);
        if (lock) {
            // 1.执行逻辑操作
            sayHello();
            // 2.执行完毕释放锁
            redisUtils.releaseLock(lockKey, lockValue);
        } else {
            // 设置失败次数计数器, 当到达5次时, 返回失败
            int failCount = 1;
            while (failCount <= 5) {
                // 等待100ms重试
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (redisUtils.tryLock(lockKey, lockValue, 100, 100)) {
                    // 1.执行逻辑操作
                    sayHello();
                    // 2.执行完毕释放锁
                    redisUtils.releaseLock(lockKey, lockValue);
                } else {
                    failCount++;
                }
            }
            throw new RuntimeException("现在操作的人太多了, 请稍等再试");
        }
    }

    public void sayHello() {
        System.out.println("Hello world");
    }
}
