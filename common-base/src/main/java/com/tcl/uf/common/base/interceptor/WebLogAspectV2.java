package com.tcl.uf.common.base.interceptor;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.JMException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * WebLogAspectV2
 *
 * @author yuanhehng.deng
 */
@Component
@Aspect
@Order(1)
public class WebLogAspectV2 {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebLogAspectV2.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Around("execution(* com.tcl.uf.*.controller..*(..))")
    public Object aroundController(ProceedingJoinPoint jp) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Object retObj = null;
        // 真正执行
        try {
            retObj = jp.proceed(jp.getArgs());
        }  catch (JMException e) {
            return handleJMException(e);
        }
        return retObj;
    }


    private Object handleJMException(JMException e) {
        ResponseData<Object> resp = new ResponseData<>();
        resp.setData(null);
        resp.setData(-1);
        resp.setCode(e.getErrCode());
        String cusmsg = e.getMsgZ();
        if (cusmsg == null) {
            resp.setMsgZ("服务器内部错误");
        } else {
            resp.setMsgZ(cusmsg);
        }
        if(e.getMsgE() != null){
            resp.setMsgE(e.getMsgE());
        }else{
            resp.setMsgE("error");
        }
        LOGGER.error(resp.getMsgZ());
        return resp;
    }
}
