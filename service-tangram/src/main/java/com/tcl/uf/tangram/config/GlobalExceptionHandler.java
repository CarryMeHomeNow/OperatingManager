package com.tcl.uf.tangram.config;

import com.tcl.uf.common.base.util.ExceptionStackTraceUtil;
import com.tcl.uf.tangram.dto.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Desc : 全局异常处理类，对于controller未知异常或常见异常进行统一处理
 * @Author yxlong
 * @Date 2020/8/21 2:41 下午
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 指定要处理的异常，这里是所有异常，可针对不同的异常做不同处理
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ServerResponse<String> handleException(Exception ex){
        //记录日志
        //自定义返回的code值和msg
        log.error("服务器未知异常：{}",ExceptionStackTraceUtil.getStackTrace(ex)) ;
        return ServerResponse.failed("-1",ex.getMessage());
    }
}
