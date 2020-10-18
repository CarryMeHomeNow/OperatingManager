package com.tcl.uf.tangram.vo;

import java.util.Date;

/**
 * @Desc : Tangram组件配置信息
 * @Author Mr.HePeng
 * @Date 2020/8/8 16:57
 */
public class TangramModuleConfig {

    private String moduleCode;
    private String moduleName;
    private String tangramTemplate;
    private String invokeModuleClass;
    private String invokeModuleMethod;
    private String dataSource;
    private String urlInfo;
    private String invokeApiClass;
    private String invokeApiMethod;
    private String dataType;
    private String status;
    private String userId;
    private Date createTime;
    private Date updateTime;
    private String updateUserId;

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
