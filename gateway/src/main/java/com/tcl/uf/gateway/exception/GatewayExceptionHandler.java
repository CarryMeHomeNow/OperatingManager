package com.tcl.uf.gateway.exception;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Desc : global exception
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/28 13:57
 */
public class GatewayExceptionHandler extends DefaultErrorWebExceptionHandler {

    private boolean enableDefaultTangramData;

    /**
     * Create a new {@code DefaultErrorWebExceptionHandler} instance.
     *
     * @param errorAttributes    the error attributes
     * @param resourceProperties the resources configuration properties
     * @param errorProperties    the error configuration properties
     * @param applicationContext the current application context
     */
    public GatewayExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        int code = 500;
        Throwable error = super.getError(request);
        if (error instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) error;
            code = responseStatusException.getStatus().value();
        }

        // 异常返回默认数据，配置开关，测试用
        if (enableDefaultTangramData) {
            Optional<String> optional = request.queryParam("reqCode");

            if (optional.isPresent()) {
                String reqCode = optional.get();
                String data = DefaultTangramData.getDataByTangramCode(reqCode);
                return defaultResponse(200, data);
            }
        }

        return response(code, this.buildMessage(request, error));
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return (int) errorAttributes.get("rspCode");
    }

    private String buildMessage(ServerRequest request, Throwable ex) {
        StringBuilder message = new StringBuilder("Failed to handle request [");
        message.append(request.methodName());
        message.append(" ");
        message.append(request.uri());
        message.append("]");
        if (ex != null) {
            message.append(": ");
            message.append(ex.getMessage());
        }
        return message.toString();
    }

    public static Map<String, Object> response(int status, String errorMessage) {
        Map<String, Object> map = new HashMap<>();
        map.put("rspCode", status);
        map.put("rspMsg", errorMessage);
        map.put("data", null);
        return map;
    }

    public static Map<String, Object> defaultResponse(int status, String data) {
        Map<String, Object> map = new HashMap<>();
        map.put("rspCode", status);
        map.put("rspMsg", "默认测试数据");
        map.put("data", JSON.parse(data));
        return map;
    }

    public void setEnableDefaultTangramData(boolean enableDefaultTangramData) {
        this.enableDefaultTangramData = enableDefaultTangramData;
    }
}
