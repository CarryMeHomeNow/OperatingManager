package com.tcl.uf.activity.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author youyun.xu
 * @ClassName: ControllerLogAspect
 * @Description: 全局异常捕获(只打日志,响应结果由网关统一处理)
 * @date 2020/9/5 11:58
 */
@Aspect
@Component
public class ControllerLogAspect {

    private static final Logger log = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(public * com.tcl.uf.activity.controller.*.*(..))")
    private void e1() {}

    @AfterThrowing(pointcut = "e1()", throwing = "e")
    public void p1(JoinPoint joinPoint,Exception e){
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.error("异常所在类 {} 异常所在方法 {} 异常中的参数 {} 异常 {}",className,methodName,args,e);
    }
}
