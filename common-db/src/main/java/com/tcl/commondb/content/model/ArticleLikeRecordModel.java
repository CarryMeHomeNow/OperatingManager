package com.tcl.commondb.content.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article_like_record")
public class ArticleLikeRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id", columnDefinition = "int(32) DEFAULT NULL COMMENT '文章主键'")
    private Long articleId;

    @Column(name = "ssoid", columnDefinition = "bigint(20) DEFAULT NULL COMMENT '用户标识'")
    private Long ssoid;

    @Column(name = "like_status", columnDefinition = "int(1) DEFAULT '1' COMMENT '点赞状态, 1:点赞,0:取消点赞'")
    private Integer likeStatus;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "datetime DEFAULT NULL COMMENT '更新时间'")
    private Date updateTime;

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

    public Long getSsoid() {
        return ssoid;
    }

    public void setSsoid(Long ssoid) {
        this.ssoid = ssoid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
