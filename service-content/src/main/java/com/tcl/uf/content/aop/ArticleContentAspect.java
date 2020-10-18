package com.tcl.uf.content.aop;
import java.util.Date;


import com.alibaba.fastjson.JSON;
import com.tcl.commondb.content.model.ArticleOperationalRecordModel;
import com.tcl.commondb.content.repository.ArticleOperationalRecordModelRepository;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.TokenData;

import com.tcl.uf.content.annotation.ArticleOperational;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author youyun.xu
 * @ClassName: ArticleContentAspect
 * @Description: 切面监控文章操作状态
 * @date 2020/8/31 11:36
 */
@Aspect
@Component
public class ArticleContentAspect extends AbstractBaseController {

    @Autowired
    private ArticleOperationalRecordModelRepository operationalRecordModelRepository;

    // 定义一个切入点(保存或新增)
    /* @Pointcut("execution(public * com.tcl.uf.content.service.impl.ArticleContentServiceImpl.saveOrUpdate(..))")
    public void saveOrUpdate() {}*/

    // 定义切入点
    @Pointcut("@annotation(com.tcl.uf.content.annotation.ArticleOperational)")
    public void operational() {}

    /**
     * 获取参数列表
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    private static Map<String, Object> getFieldsName(JoinPoint joinPoint) {
        // 参数值
        Object[] args = joinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }

    @AfterReturning(value = "operational()")
    public void p1(JoinPoint joinPoint){
        //获取执行参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        TokenData info = getManageUserInfo(request);
        Map<String,Object> params = getFieldsName(joinPoint);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ArticleOperational articleOperational= method.getDeclaredAnnotation(ArticleOperational.class);
        //返回正常记录操作日志
        saveArticleOperationalRecord(articleOperational.action(),info.getUsername(),JSON.toJSONString(params));
    }

    /*@Around("auditReject()")
    public Object p8(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        //执行前织入
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object object= thisJoinPoint.proceed();
        //执行后织入
        HttpServletRequest request = attributes.getRequest();
        TokenData info = getManageUserInfo(request);
        Map<String,Object> params = getFieldsName(thisJoinPoint);
        //返回正常记录操作日志
        saveArticleOperationalRecord("审批拒绝",info.getUsername(),JSON.toJSONString(params));
        return object;
    }*/

    private void saveArticleOperationalRecord(String operational,String userId,String remarks){
        ArticleOperationalRecordModel articleOperationalRecordModel=new ArticleOperationalRecordModel();
        articleOperationalRecordModel.setArticleId(0L);
        articleOperationalRecordModel.setOperational(operational);
        articleOperationalRecordModel.setUserId(userId);
        articleOperationalRecordModel.setCreateTime(new Date());
        articleOperationalRecordModel.setRemarks(remarks);
        operationalRecordModelRepository.saveAndFlush(articleOperationalRecordModel);
    }
}
