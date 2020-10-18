package com.tcl.uf.common.base.interceptor;

import com.tcl.uf.common.base.annotation.DRedisLock;
import com.tcl.uf.common.base.util.redis.RedisLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;


/**
 * redis分布式锁拦截器
 *
 * @author dengyuanheng
 */
@Aspect
public class RedisRockInterceptor {
    private static final Logger log = LoggerFactory.getLogger(RedisRockInterceptor.class);
    @Pointcut("@annotation(dRedisLock)")
    public void redisLockAspect(DRedisLock dRedisLock) {
    }

    @Around(value = "redisLockAspect(dRedisLock)", argNames = "pjp,dRedisLock")
    public Object doAround(ProceedingJoinPoint pjp, DRedisLock dRedisLock) throws Throwable {
        // 获取锁资源
        RedisLock lock;
        try {
            Object target = pjp.getTarget();
            Method targetMethod = ((MethodSignature) pjp.getSignature()).getMethod();
            String key = dRedisLock.key();
            if (key.contains("#")) {
                key = parse(target, key, targetMethod, pjp.getArgs());
            }

            String prefixKey = dRedisLock.prefixKey();
            if (StringUtils.isEmpty(prefixKey)) {
                String className = target.getClass().getSimpleName();
                String methodName = targetMethod.getName();
                prefixKey = className + "_" + methodName;
            }

            lock = RedisLock.getLock(prefixKey + "_" + key);
        } catch (Exception e) {
            log.warn("redisLockAspect", e);
            // 获取锁资源失败，直接执行原方法
            return pjp.proceed();
        }

        // 开始锁
        try {
            boolean acquire = lock.tryLock(dRedisLock.waitSeconds(), dRedisLock.leaseSeconds());
            if (!acquire) {
                return null;
            }
            return pjp.proceed();
        } finally {
            lock.unLock();
        }
    }

    /**
     * 支持 #p0 参数索引的表达式解析
     *
     * @param rootObject 根对象,method 所在的对象
     * @param sPEL       表达式
     * @param method     目标方法
     * @param args       方法入参
     * @return 解析后的字符串
     */
    public String parse(Object rootObject, String sPEL, Method method, Object[] args) {
        //获取被拦截方法参数名列表
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] argNames = u.getParameterNames(method);
        if (argNames == null) {
            throw new IllegalArgumentException("DRedisLock入参不能为空");
        }
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new MethodBasedEvaluationContext(rootObject, method, args, u);
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < argNames.length; i++) {
            context.setVariable(argNames[i], args[i]);
        }
        return parser.parseExpression(sPEL).getValue(context, String.class);
    }

}