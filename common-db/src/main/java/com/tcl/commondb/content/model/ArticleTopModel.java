package com.tcl.commondb.content.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article_content_top")
public class ArticleTopModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id", columnDefinition = "int(32) DEFAULT NULL COMMENT '文章主键'")
    private Long articleId;

    @Column(name = "section_id", columnDefinition = "int(32) DEFAULT NULL COMMENT '版面主键'")
    private Long sectionId;

    @Column(name = "top_duration", columnDefinition = "varchar(20) DEFAULT NULL COMMENT '置顶时长'")
    private String topDuration;

    @Column(name = "top_position", columnDefinition = "int(20) DEFAULT NULL COMMENT '置顶位置'")
    private Long topPosition;

    @Column(name = "expired_time", columnDefinition = "datetime DEFAULT NULL COMMENT '过期时间'")
    private Date expiredTime;

    @Column(name = "user_id", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '操作用户'")
    private String userId;

    @Column(name = "last_time", columnDefinition = "datetime DEFAULT NULL COMMENT '最后修改时间'")
    private Date lastTime;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getTopDuration() {
        return topDuration;
    }

    public void setTopDuration(String topDuration) {
        this.topDuration = topDuration;
    }

    public Long getTopPosition() {
        return topPosition;
    }

    public void setTopPosition(Long topPosition) {
        this.topPosition = topPosition;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
