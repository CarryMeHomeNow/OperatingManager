package com.tcl.commondb.advert.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "advert_user")
public class AdvertUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_id", columnDefinition = "int(32) NOT NULL COMMENT '业务部门ID'")
    private Long departmentId;

    @Column(name = "mid", columnDefinition = "int(32) NOT NULL COMMENT '用户ID'")
    private Long mid;

    @Column(name = "username", columnDefinition = "varchar(100) NOT NULL COMMENT '用户名称'")
    private String username;

    @Column(name = "oa_email", columnDefinition = "varchar(100) NOT NULL COMMENT 'OA邮箱'")
    private String oaEmail;

    @Column(name = "oa_department_name", columnDefinition = "varchar(50) NOT NULL COMMENT 'OA业务部门'")
    private String oaDepartmentName;

    @Column(name = "status", columnDefinition = "tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态： 0草稿 1审核中 2上线 3下线'")
    private Integer status;

    @Column(name = "audit_status", columnDefinition = "tinyint(4) NOT NULL DEFAULT 0 COMMENT '审核状态：0未审核 1已通过 2已驳回'")
    private Integer auditStatus;

    @Column(name = "audit_time", columnDefinition = "TIMESTAMP NULL DEFAULT NULL COMMENT '审核时间'")
    private Timestamp auditTime;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private Timestamp updateTime;

    @Column(name = "is_deleted", columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除 0:否 1:是'")
    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOaEmail() {
        return oaEmail;
    }

    public void setOaEmail(String oaEmail) {
        this.oaEmail = oaEmail;
    }

    public String getOaDepartmentName() {
        return oaDepartmentName;
    }

    public void setOaDepartmentName(String oaDepartmentName) {
        this.oaDepartmentName = oaDepartmentName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
