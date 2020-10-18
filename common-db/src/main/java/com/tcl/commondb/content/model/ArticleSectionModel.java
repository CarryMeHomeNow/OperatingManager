package com.tcl.commondb.content.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article_section_configure")
public class ArticleSectionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "section_name", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '版块名称'")
    private String sectionName;

    @Column(name = "sub_title", columnDefinition = "varchar(150) DEFAULT NULL COMMENT '版块子标题'")
    private String subTitle;

    @Column(name = "position", columnDefinition = "int(4) DEFAULT NULL COMMENT '版块位置'")
    private Integer position;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "use_status", columnDefinition = "int(1) DEFAULT 1 COMMENT '伪删除 1:使用, 0:删除'")
    private Integer useStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
}