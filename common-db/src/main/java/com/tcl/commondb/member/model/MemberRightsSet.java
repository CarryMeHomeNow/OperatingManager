package com.tcl.commondb.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "member_rights_set")
public class MemberRightsSet {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "sn", columnDefinition = "varchar(255) COMMENT '权益唯一标识'")
    private String sn;

    @Column(name = "name",columnDefinition = "varchar(255) COMMENT '权益名称'")
    private String name;

    @Column(name = "cover_url",columnDefinition = "varchar(255) COMMENT '权益图标地址'")
    private String coverUrl;

    @Column(name = "get_condition",columnDefinition = "varchar(255) COMMENT '获取条件'")
    private String getCondition;

    @Column(name = "description",columnDefinition = "text COMMENT ' 权益介绍'")
    private String description;

    @Column(name = "jump_url",columnDefinition = "varchar(255) COMMENT '领取链接'")
    private String jumpUrl;

    @Column(name = "is_delete",columnDefinition = "int(1) ZEROFILL NULL COMMENT COMMENT '伪删除 0:使用, 1:删除'")
    private Integer isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    @Column(name = "create_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    @Column(name = "update_time",columnDefinition = "timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间'")
    private Timestamp updateTime;

    @Column(name = "editor", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '编辑者'")
    private String editor;

    @Column(name = "creator", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '创建者'")
    private String creator;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getGetCondition() {
        return getCondition;
    }

    public void setGetCondition(String getCondition) {
        this.getCondition = getCondition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
