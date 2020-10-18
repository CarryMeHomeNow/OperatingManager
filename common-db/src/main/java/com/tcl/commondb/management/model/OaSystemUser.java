package com.tcl.commondb.management.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author zhukaiwei@kuyumall.com
 * @ClassName: UmSyncUser
 * @Description:
 * @date 2018/4/14 下午2:32
 */

@Entity
@Table(name = "oa_system_user")
public class OaSystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", columnDefinition = "varchar(100) NOT NULL COMMENT 'user_id'")
    private String userId;

    @Column(name = "person_code", columnDefinition = "varchar(15) NOT NULL COMMENT '员工工号'")
    private String personCode;
    
    @Column(name = "username", columnDefinition = "varchar(100) NOT NULL COMMENT '用户昵称(人名)'")
    private String username;

    @Column(name = "mobile", columnDefinition = "varchar(30) COMMENT '手机号'")
    private String mobile;

    @Column(name = "email", columnDefinition = "varchar(100) COMMENT 'email'")
    private String email;

    @Column(name = "position", columnDefinition = "varchar(100) COMMENT '职位信息'")
    private String position;

    @Column(name = "user_status", columnDefinition = "varchar(2) COMMENT '激活状态'")
    private String userStatus;

    @Column(name = "department_id", columnDefinition = "varchar(20) COMMENT '授权所属部门'")
    private String departmentId;

    @Column(name = "original_department_id", columnDefinition = "varchar(20) NOT NULL COMMENT 'sap源部门ID'")
    private String originalDepartmentId;
    
    @Column(name = "department_name", columnDefinition = "varchar(50) COMMENT '部门名称'")
    private String departmentName;

    @Column(name = "address", columnDefinition = "varchar(100) COMMENT '工作地址'")
    private String address;

    @Column(name = "sex", columnDefinition = "varchar(2) COMMENT '性别'")
    private String sex;

    @Column(name = "manage_id", columnDefinition = "bigint(11) COMMENT 'manage_user 主键id'")
    private Long manageId;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    @Column(name = "update_time", columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private Timestamp updateTime;

    @Column(name = "um_appid", columnDefinition = "varchar(20) DEFAULT 'TCL' COMMENT '组织代码'")
    private String umAppid;

    @Column(name = "sour_type", columnDefinition = "varchar(20) DEFAULT NULL COMMENT '类型'")
    private String sourType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getOriginalDepartmentId() {
        return originalDepartmentId;
    }

    public void setOriginalDepartmentId(String originalDepartmentId) {
        this.originalDepartmentId = originalDepartmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getManageId() {
        return manageId;
    }

    public void setManageId(Long manageId) {
        this.manageId = manageId;
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

    public String getUmAppid() {
        return umAppid;
    }

    public void setUmAppid(String umAppid) {
        this.umAppid = umAppid;
    }

    public String getSourType() {
        return sourType;
    }

    public void setSourType(String sourType) {
        this.sourType = sourType;
    }
}
