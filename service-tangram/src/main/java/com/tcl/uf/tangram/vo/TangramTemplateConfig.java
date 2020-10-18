package com.tcl.uf.tangram.vo;

import java.util.Date;

/**
 * @Desc : Tangram组件配置信息
 * @Author Mr.HePeng
 * @Date 2020/8/8 16:56
 */
public class TangramTemplateConfig {

    private String templateName;
    private String tangramCode;
    private String tangramTemplate;
    private String status;
    private String userId;
    private Date createTime;
    private Date updateTime;
    private String updateUserId;

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
}
