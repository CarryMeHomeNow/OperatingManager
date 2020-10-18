package com.tcl.commondb.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 管理后台用户表
 *
 */
@Entity
@Table(name = "manage_user")
public class ManageUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", columnDefinition = "varchar(50) NOT NULL DEFAULT '' COMMENT '用户名'")
    private String username;

    @Column(name = "avatar_status", columnDefinition = "tinyint(1) DEFAULT 0 COMMENT '0标识默认头像, 1自定义头像'")
    private Integer avatarStatus;

    @Column(name = "passwd", columnDefinition = "varchar(50) NOT NULL DEFAULT 'tcl2017' COMMENT '密码'")
    private String passwd;

    @Column(name = "realname", columnDefinition = "varchar(50) NOT NULL DEFAULT '' COMMENT '真实姓名'")
    private String realname;

    @Column(name = "phone", columnDefinition = "varchar(20) NOT NULL DEFAULT '' COMMENT '手机号'")
    private String phone;

    @Column(name = "email", columnDefinition = "varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱'")
    private String email;

    @Column(name = "group_id", columnDefinition = "int(11) DEFAULT NULL COMMENT '所属架构'")
    private Integer groupId;

    @Column(name = "character_id", columnDefinition = "int(11) NOT NULL DEFAULT '0' COMMENT '所属角色'")
    private Integer characterId;

    @Column(name = "authority", columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '权限'")
    private String authority;

    @Column(name = "status", columnDefinition = "tinyint(1) NOT NULL DEFAULT '1' COMMENT '1标识可用 0标识冻结'")
    private Integer status;

    @Column(name = "avatar_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '自定义头像地址'")
    private String avatarUrl;

    @Column(name = "create_time", columnDefinition = "bigint(11) DEFAULT 0 COMMENT '创建时间'")
    private Long createTime;

    @Column(name = "update_time", columnDefinition = "bigint(11) DEFAULT 0 COMMENT '创建时间'")
    private Long updateTime;

    @Column(name = "member_type", columnDefinition = "tinyint(1) DEFAULT NULL COMMENT '(新增)用户来源类型, 1或者无 表示平台创建用户, 2表示 企业微信同步过来的公司员工'")
    private Byte memberType;

    @Column(name = "department_id", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '部门id'")
    private String departmentId;
    @JsonIgnore
    public boolean isFreeze() {
        if (status == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAvatarStatus() {
        return avatarStatus;
    }

    public void setAvatarStatus(Integer avatarStatus) {
        this.avatarStatus = avatarStatus;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getMemberType() {
        return memberType;
    }

    public void setMemberType(Byte memberType) {
        this.memberType = memberType;
    }
}
