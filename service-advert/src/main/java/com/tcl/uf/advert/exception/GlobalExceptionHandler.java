package com.tcl.uf.advert.exception;

import com.tcl.uf.common.base.constant.CommonErrorConstant;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.ExceptionStackTraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Value("${spring.application.name}")
	private String serviceName;

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseData<Object> handleException(Exception e) {
		log.error(ExceptionStackTraceUtil.getStackTrace(e));
		return new ResponseData<>(CommonErrorConstant.COMMON_FAIL.getCode(),"服务器异常("+serviceName+"),请联系管理员",ExceptionStackTraceUtil.getStackTrace(e));
	}
}
