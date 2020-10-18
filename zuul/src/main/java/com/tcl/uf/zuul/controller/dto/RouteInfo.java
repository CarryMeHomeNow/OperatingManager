package com.tcl.uf.zuul.controller.dto;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: RouteInfo
 * @Description:
 * @date 2018/1/31 下午3:32
 */

public class RouteInfo {

    private String id;

    private String path;

    private String apiName;

    private boolean login;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
