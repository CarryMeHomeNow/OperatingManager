package com.tcl.commondb.management.model;

import javax.persistence.*;

/**
 * 菜单角色
 */
@Entity
@Table(name = "manage_menu_role")
public class ManageMenuRole {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "c_id", columnDefinition = "bigint(11) COMMENT '角色id:manage_role 表id'")
    private Long cId;

    @Column(name = "menu_id", columnDefinition = "int(11) COMMENT '菜单id'")
    private Integer menuId;



    @Column(name = "create_time", columnDefinition = "bigint(11) COMMENT '创建时间'")
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
