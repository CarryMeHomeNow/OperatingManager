package com.tcl.commondb.management.model;

import javax.persistence.*;

@Entity
@Table(name = "manage_user_role")
public class ManageUserRole {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "c_id", columnDefinition = "bigint(11) COMMENT 'manage_role角色id'")
    private Long cId;
    @Column(name = "m_id", columnDefinition = "bigint(11) COMMENT 'manage_user用户id'")
    private Long mId;
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

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
