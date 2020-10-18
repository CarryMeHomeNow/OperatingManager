package com.tcl.commondb.tangram.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhongfk on 2020/8/14.
 * @version 1.0
 */
@Entity
@Table(name = "tangram_template_module_config")
public class TangramTemplateModuleConfig implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "module_code", columnDefinition = "varchar(54) NOT NULL COMMENT '组件编号'")
    private String moduleCode;

    @Column(name = "module_name", columnDefinition = "varchar(54) NOT NULL COMMENT '组件名称'")
    private String moduleName;

    @Column(name = "tangram_template", columnDefinition = "json NOT NULL COMMENT '模板'")
    private String tangramTemplate;

    @Column(name = "invoke_module_class", columnDefinition = "varchar(32) NOT NULL COMMENT '组件调用class'")
    private String invokeModuleClass;

    @Column(name = "invoke_module_method", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '组件调用Method'")
    private String invokeModuleMethod;

    @Column(name = "data_source", columnDefinition = "varchar(64)  DEFAULT COMMENT '数据源'")
    private String dataSource;

    @Column(name = "url_info", columnDefinition = "varchar(128) DEFAULT  NULL COMMENT 'url信息'")
    private String urlInfo;

    @Column(name = "invoke_api_class", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '三方API调用Class'")
    private String invokeApiClass;

    @Column(name = "invoke_api_method", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '三方API调用Method'")
    private String invokeApiMethod;

    @Column(name = "data_type", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '组件返回类型'")
    private String dataType;

    @Column(name = "status", columnDefinition = "varchar(2) NOT NULL COMMENT '组件状态 1-启用  2-停用'")
    private String status;

    @Column(name = "user_id", columnDefinition = "varchar(30) NOT NULL COMMENT '创建人'")
    private String userId;

    @Column(name = "create_time", columnDefinition = "datetime NULL COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "datetime NULL COMMENT '更新时间'")
    private Date updateTime;

    @Column(name = "update_user_id", columnDefinition = "varchar(30) NOT NULL COMMENT '最后一次修改用户id'")
    private String updateUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getTangramTemplate() {
        return tangramTemplate;
    }

    public void setTangramTemplate(String tangramTemplate) {
        this.tangramTemplate = tangramTemplate;
    }

    public String getInvokeModuleClass() {
        return invokeModuleClass;
    }

    public void setInvokeModuleClass(String invokeModuleClass) {
        this.invokeModuleClass = invokeModuleClass;
    }

    public String getInvokeModuleMethod() {
        return invokeModuleMethod;
    }

    public void setInvokeModuleMethod(String invokeModuleMethod) {
        this.invokeModuleMethod = invokeModuleMethod;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getUrlInfo() {
        return urlInfo;
    }

    public void setUrlInfo(String urlInfo) {
        this.urlInfo = urlInfo;
    }

    public String getInvokeApiClass() {
        return invokeApiClass;
    }

    public void setInvokeApiClass(String invokeApiClass) {
        this.invokeApiClass = invokeApiClass;
    }

    public String getInvokeApiMethod() {
        return invokeApiMethod;
    }

    public void setInvokeApiMethod(String invokeApiMethod) {
        this.invokeApiMethod = invokeApiMethod;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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
}
