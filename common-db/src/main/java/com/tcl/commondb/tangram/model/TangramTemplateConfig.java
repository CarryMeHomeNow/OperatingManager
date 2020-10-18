package com.tcl.commondb.tangram.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhongfk on 2020/8/14.
 * @version 1.0
 */
@Entity
@Table(name = "tangram_template_config")
public class TangramTemplateConfig implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "template_name", columnDefinition = "varchar(54) NOT NULL COMMENT '模板名称'")
    private String templateName;

    @Column(name = "template_code", columnDefinition = "varchar(32) NOT NULL COMMENT '模板编号'")
    private String tangramCode;

    @Column(name = "tangram_template", columnDefinition = "text NOT NULL COMMENT '模板'")
    private String tangramTemplate;

    @Column(name = "status", columnDefinition = "varchar(2) NOT NULL COMMENT '状态'")
    private String status;

    @Column(name = "user_id", columnDefinition = "varchar(32) NOT NULL COMMENT '创建人id'")
    private String userId;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "datetime NULL COMMENT '更新时间'")
    private Date updateTime;

    @Column(name = "update_user_id", columnDefinition = "varchar(32) NOT NULL COMMENT '更新用户'")
    private String updateUserId;

    @Column(name = "version", columnDefinition = "varchar(32) NOT NULL COMMENT '版本号'")
    private String version;

    @Column(name = "request_path", columnDefinition = "varchar(128) NOT NULL COMMENT '请求路径，/xxx'")
    private String requestPath;

    @Column(name = "route_path", columnDefinition = "varchar(128) NOT NULL COMMENT '路由路径，/xxx'")
    private String routePath;

    @Column(name = "route_serve", columnDefinition = "varchar(32) NOT NULL COMMENT '路由服务，域名、服务名、IP:PORT'")
    private String routeServe;

    @Column(name = "app_age", columnDefinition = "int(12) NOT NULL COMMENT 'app端相对过期时间，单位s'")
    private Integer appAge;

    @Column(name = "gw_age", columnDefinition = "int(12) NOT NULL COMMENT '网关相对过期时间，单位s'")
    private Integer gwAge;

    @Column(name = "is_gw_cache", columnDefinition = "varchar(2) NOT NULL COMMENT '是否是网关缓存，0-否，1-是'")
    private String isGwCache;

    @Column(name = "cache_type", columnDefinition = "varchar(2) NULL COMMENT '缓存类型，1-ehcache 2-redis'")
    private String cacheType;

    @Column(name = "cache_request_header", columnDefinition = "varchar(32) NULL COMMENT '缓存请求头，a=1|b=2|c=3格式'")
    private String cacheRequestHeader;

    @Column(name = "cache_request_body", columnDefinition = "varchar(32) NULL COMMENT '缓存请求体，a=1|b=2|c=3格式'")
    private String cacheRequestBody;

    @Column(name = "check_token", columnDefinition = "varchar(2) NULL COMMENT '是否校验token，0-否，1-是'")
    private String checkToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTangramCode() {
        return tangramCode;
    }

    public void setTangramCode(String tangramCode) {
        this.tangramCode = tangramCode;
    }

    public String getTangramTemplate() {
        return tangramTemplate;
    }

    public void setTangramTemplate(String tangramTemplate) {
        this.tangramTemplate = tangramTemplate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRoutePath() {
        return routePath;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public String getRouteServe() {
        return routeServe;
    }

    public void setRouteServe(String routeServe) {
        this.routeServe = routeServe;
    }

    public Integer getAppAge() {
        return appAge;
    }

    public void setAppAge(Integer appAge) {
        this.appAge = appAge;
    }

    public Integer getGwAge() {
        return gwAge;
    }

    public void setGwAge(Integer gwAge) {
        this.gwAge = gwAge;
    }

    public String getIsGwCache() {
        return isGwCache;
    }

    public void setIsGwCache(String isGwCache) {
        this.isGwCache = isGwCache;
    }

    public String getCacheRequestHeader() {
        return cacheRequestHeader;
    }

    public void setCacheRequestHeader(String cacheRequestHeader) {
        this.cacheRequestHeader = cacheRequestHeader;
    }

    public String getCacheRequestBody() {
        return cacheRequestBody;
    }

    public void setCacheRequestBody(String cacheRequestBody) {
        this.cacheRequestBody = cacheRequestBody;
    }

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public String getCheckToken() {
        return checkToken;
    }

    public void setCheckToken(String checkToken) {
        this.checkToken = checkToken;
    }
}
