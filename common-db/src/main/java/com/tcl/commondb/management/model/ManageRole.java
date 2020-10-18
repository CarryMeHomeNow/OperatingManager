package com.tcl.commondb.management.model;

import javax.persistence.*;

/**
 * 角色
 */
@Entity
@Table(name = "manage_role")
public class ManageRole {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", columnDefinition = "varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称'")
    private String name;
    @Column(name = "characte", columnDefinition = "varchar(50) DEFAULT '' COMMENT '角色code'")
    private String characte;
    @Column(name = "create_time", columnDefinition = "bigint(11) COMMENT '创建时间'")
    private Long createTime;
    @Column(name = "is_delete", columnDefinition = "tinyint(1) DEFAULT 1 COMMENT '是否已删除 1否 2是'")
    private Byte isDelete;
    @Column(name = "parent_id", columnDefinition = "bigint(11) COMMENT '父角色ID'")
    private Long parentId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacte() {
        return characte;
    }

    public void setCharacte(String characte) {
        this.characte = characte;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
