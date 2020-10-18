package com.tcl.uf.member.exception;

import com.tcl.uf.common.base.util.ExceptionStackTraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class GlobalExceptionHandler {

	/*private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Value("${spring.application.name}")
	private String serviceName;

	@ExceptionHandler(Exception.class)
	public void handleException(Exception e) {
		log.error("全局打印异常日志:",ExceptionStackTraceUtil.getStackTrace(e));
	}*/
}

