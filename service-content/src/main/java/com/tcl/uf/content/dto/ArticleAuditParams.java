package com.tcl.uf.content.dto;

public class ArticleAuditParams {
    private String title; //文章标题
    private String auditStatus;//审核状态

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}
