package com.tcl.uf.content.vo;

public class ArticleAuditVo {

    private Long articleId;//文章编码
    private String title; //文章标题
    private String sectionNames;
    private String contentHtml;//文章内容HTML
    private String auditStatus;//审核状态
    private String updateTime;//更新时间
    private String remarks;//备注

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSectionNames() {
        return sectionNames;
    }

    public void setSectionNames(String sectionNames) {
        this.sectionNames = sectionNames;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }
}
