package com.tcl.uf.zuul.common.dto;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: RequestLog
 * @Description: 请求日志
 * @date 2018/5/17 下午3:58
 */
public class RequestLog {
    private String method;

    private String uri;

    private String host;

    private String param;

    private String client;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
