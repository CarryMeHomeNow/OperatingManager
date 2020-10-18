package com.tcl.uf.zuul.model;

import javax.persistence.*;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: RouterRule
 * @Description:
 * @date 2018/1/30 下午8:40
 */

@Entity
@Table(name = "pre_uf_router")
public class RouterRuleV {

    @Id
    @GeneratedValue
    private Long id;


    /**
     * 路由配置
     */
    private String path;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "api_name")
    private String apiName;

    /**
     * 目标地址
     */
    @Column(name = "target")
    private String target;

    /**
     * 是否需要登入态
     */
    @Column(name = "login")
    private boolean login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean getLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
