package com.tcl.commondb.content.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article_operational_record")
public class ArticleOperationalRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id", columnDefinition = "int(32) DEFAULT NULL COMMENT '文章主键'")
    private Long articleId;

    @Column(name = "operational", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '当前动作'")
    private String operational;

    @Column(name = "user_id", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '用户标识'")
    private String userId;

    @Column(name = "remarks", columnDefinition = "text DEFAULT NULL COMMENT '操作备注'")
    private String remarks;

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

    public String getOperational() {
        return operational;
    }

    public void setOperational(String operational) {
        this.operational = operational;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
