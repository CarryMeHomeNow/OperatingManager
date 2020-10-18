package com.tcl.commondb.member.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="member_link_configure")
public class LinkConfigureModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name",columnDefinition = "varchar(100) DEFAULT NULL COMMENT '配置名称'")
    private String name;

    @Column(name = "type",columnDefinition = "varchar(2) DEFAULT NULL COMMENT '文件类型'")
    private String type;

    @Column(name = "jump_url",columnDefinition = "varchar(100) DEFAULT NULL COMMENT '跳转链接'")
    private String jumpUrl;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
