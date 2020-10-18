package com.tcl.uf.advert.vo;

public class UserListVo {

    private Long userId;
    private String userName;
    private String departmentName;
    private Long departmentId;
    private String oaEmail;
    private String oaDepartmentName;
    private String createTime;
    private String confirmTime;
    private Integer auditStatus;
    private Integer status;

    public UserListVo(Long userId, String userName, String departmentName, String oaEmail, String oaDepartmentName, String createTime, String confirmTime, Integer auditStatus, Integer status, Long departmentId) {
        this.userId = userId;
        this.userName = userName;
        this.departmentName = departmentName;
        this.oaEmail = oaEmail;
        this.oaDepartmentName = oaDepartmentName;
        this.createTime = createTime;
        this.confirmTime = confirmTime;
        this.auditStatus = auditStatus;
        this.status = status;
        this.departmentId = departmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
