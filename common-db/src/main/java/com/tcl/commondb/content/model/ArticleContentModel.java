package com.tcl.commondb.content.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "article_content_configure")
public class ArticleContentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '文章标题'")
    private String title;

    @Column(name = "content_html", columnDefinition = "text DEFAULT NULL COMMENT '文章内容HTML'")
    private String contentHtml;

    @Column(name = "content_tangram", columnDefinition = "text DEFAULT NULL COMMENT '文章内容'")
    private String contentTangram;

    @Column(name = "article_cover", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '文章封面'")
    private String articleCover;

    @Column(name = "article_summary", columnDefinition = "text DEFAULT NULL COMMENT '文章摘要'")
    private String articleSummary;

    @Column(name = "advertisement", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '是否广告'")
    private String advertisement;

    @Column(name = "status", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '文章状态'")
    private String status;

    @Column(name = "audit_status", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '审核状态'")
    private String auditStatus;

    @Column(name = "remarks", columnDefinition = "varchar(120) DEFAULT NULL COMMENT '备注'")
    private String remarks;

    @Column(name = "top_status", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '置顶状态'")
    private String topStatus;

    @Column(name = "top_time", columnDefinition = "varchar(30) DEFAULT NULL COMMENT '置顶时间'")
    private String topTime;

    @Column(name = "top_position", columnDefinition = "int(20) DEFAULT NULL COMMENT '置顶位置'")
    private Long topPosition;

    @Column(name = "top_expired_time", columnDefinition = "datetime DEFAULT NULL COMMENT '置顶过期时间'")
    private Timestamp expiredTime;

    @Column(name = "browse_base", columnDefinition = "int(10) DEFAULT 1 COMMENT '浏览基数'")
    private Integer browseBase;

    @Column(name = "browse_num", columnDefinition = "int(11) DEFAULT 0 COMMENT '浏览数'")
    private Integer browseNum;

    @Column(name = "use_status", columnDefinition = "int(1) DEFAULT 1 COMMENT '伪删除 1:使用, 0:删除'")
    private Integer useStatus;

    @Column(name = "user_id", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '用户标识'")
    private String userId;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "publish_time", columnDefinition = "datetime DEFAULT NULL COMMENT '文章发布时间'")
    private Date publishTime;

    @Column(name = "update_time", columnDefinition = "datetime DEFAULT NULL COMMENT '修改时间'")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getContentTangram() {
        return contentTangram;
    }

    public void setContentTangram(String contentTangram) {
        this.contentTangram = contentTangram;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTopStatus() {
        return topStatus;
    }

    public void setTopStatus(String topStatus) {
        this.topStatus = topStatus;
    }

    public String getTopTime() {
        return topTime;
    }

    public void setTopTime(String topTime) {
        this.topTime = topTime;
    }

    public Long getTopPosition() {
        return topPosition;
    }

    public void setTopPosition(Long topPosition) {
        this.topPosition = topPosition;
    }

    public Timestamp getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Timestamp expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getBrowseBase() {
        return browseBase;
    }

    public void setBrowseBase(Integer browseBase) {
        this.browseBase = browseBase;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
