package com.tcl.commondb.content.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author youyun.xu
 * @ClassName: ArticleSectionRelationModel
 * @Description: 文章版块关联表
 * @date 2020/7/27 18:07
 */
@Entity
@Table(name = "article_section_relation")
public class ArticleSectionRelationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id", columnDefinition = "int(32) DEFAULT NULL COMMENT '文章主键'")
    private Long articleId;

    @Column(name = "section_id", columnDefinition = "int(32) DEFAULT NULL COMMENT '版面主键'")
    private Long sectionId;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
