package com.tcl.commondb.management.model;

import javax.persistence.*;

@Entity
@Table(name = "manage_menu")
public class ManageMenu {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "create_time", columnDefinition = "bigint(11) COMMENT '创建时间'")
    private Long createTime;

    @Column(name = "menu_id", columnDefinition = "int(11) COMMENT '菜单id'")
    private Integer menuId;

    @Column(name = "name", columnDefinition = "varchar(128) COMMENT '菜单名称'")
    private String name;

    @Column(name = "parent_id", columnDefinition = "int(11) COMMENT '父菜单'")
    private Integer parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
