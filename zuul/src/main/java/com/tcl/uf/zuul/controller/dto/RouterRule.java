package com.tcl.uf.zuul.controller.dto;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: RouterRule
 * @Description:
 * @date 2018/1/30 上午5:36
 */

public class RouterRule {


    private String apiName;

    private String path;

    /**
     * 服务ID
     */
    private String id;

    /**
     * 外部访问路由
     */
    private String source;

    /**
     * 内部转发路由
     */
    private String target;

    /**
     * 是否需要token / 登入态
     */
    private boolean login;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
