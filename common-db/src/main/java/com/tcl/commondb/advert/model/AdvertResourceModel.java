package com.tcl.commondb.advert.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "advert_location_resource")
public class AdvertResourceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seq_no", columnDefinition = "int(4) NULL DEFAULT 0 COMMENT '投放序号'")
    private Integer seqNo;

    @Column(name = "loc_id", columnDefinition = "int(32) NOT NULL COMMENT '广告位ID'")
    private Long locId;

    @Column(name = "group_id", columnDefinition = "int(32) NOT NULL COMMENT '广告组ID'")
    private Long groupId;

    @Column(name = "frame_id", columnDefinition = "tinyint(4) NOT NULL COMMENT '帧ID'")
    private Integer frameId;

    @Column(name = "department_id", columnDefinition = "int(32) NOT NULL COMMENT '业务部门ID'")
    private Long departmentId;

    @Column(name = "user_id", columnDefinition = "int(32) NOT NULL COMMENT '业务方用户ID'")
    private Long userId;

    @Column(name = "start_time", columnDefinition = "bigint(11) NOT NULL COMMENT '排期开始时间'")
    private Long startTime;

    @Column(name = "end_time", columnDefinition = "bigint(11) NOT NULL COMMENT '排期结束时间'")
    private Long endTime;

    @Column(name = "effective_day", columnDefinition = "int(10) NOT NULL COMMENT '排期日期'")
    private Integer effectiveDay;

    @Column(name = "effective_month", columnDefinition = "int(8) NOT NULL COMMENT '排期月份'")
    private Integer effectiveMonth;

    @Column(name = "effective_date", columnDefinition = "TIMESTAMP NULL DEFAULT NULL COMMENT '排期时间'")
    private Timestamp effectiveDate;

    @Column(name = "ad_title", columnDefinition = "varchar(100) NOT NULL COMMENT '资源标题'")
    private String adTitle;

    @Column(name = "ad_pic", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '图片地址'")
    private String adPic;

    @Column(name = "ad_link", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '资源链接'")
    private String adUrl;

    @Column(name = "test_flag", columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '测试标签： 0否 1是'")
    private Integer testFlag;

    @Column(name = "test_ac", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '测试账号'")
    private String testAccount;

    @Column(name = "editor", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '编辑者'")
    private String editor;

    @Column(name = "creator", columnDefinition = "varchar(100) NOT NULL COMMENT '创建人'")
    private String creator;

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

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getFrameId() {
        return frameId;
    }

    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getEffectiveDay() {
        return effectiveDay;
    }

    public void setEffectiveDay(Integer effectiveDay) {
        this.effectiveDay = effectiveDay;
    }

    public Integer getEffectiveMonth() {
        return effectiveMonth;
    }

    public void setEffectiveMonth(Integer effectiveMonth) {
        this.effectiveMonth = effectiveMonth;
    }

    public Timestamp getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Timestamp effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdPic() {
        return adPic;
    }

    public void setAdPic(String adPic) {
        this.adPic = adPic;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public Integer getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Integer testFlag) {
        this.testFlag = testFlag;
    }

    public String getTestAccount() {
        return testAccount;
    }

    public void setTestAccount(String testAccount) {
        this.testAccount = testAccount;
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
