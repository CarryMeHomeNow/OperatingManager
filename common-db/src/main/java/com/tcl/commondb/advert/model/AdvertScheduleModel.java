package com.tcl.commondb.advert.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "advert_location_schedule")
public class AdvertScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loc_id", columnDefinition = "int(32) NOT NULL COMMENT '广告位ID'")
    private Long locId;

    @Column(name = "group_id", columnDefinition = "int(32) NOT NULL COMMENT '广告组ID'")
    private Long groupId;

    @Column(name = "frame_id", columnDefinition = "tinyint(4) NOT NULL COMMENT '帧ID'")
    private Integer frameId;

    @Column(name = "effective_day", columnDefinition = "int(10) NOT NULL COMMENT '排期日期'")
    private Integer effectiveDay;

    @Column(name = "effective_month", columnDefinition = "int(8) NOT NULL COMMENT '排期月份'")
    private Integer effectiveMonth;

    @Column(name = "effective_date", columnDefinition = "TIMESTAMP NULL DEFAULT NULL COMMENT '排期时间'")
    private Timestamp effectiveDate;

    @Column(name = "department_id", columnDefinition = "int(32) NOT NULL COMMENT '业务部门ID'")
    private Long departmentId;

    @Column(name = "editor", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '修改者'")
    private String editor;

    @Column(name = "creator", columnDefinition = "varchar(100) NOT NULL COMMENT '创建人'")
    private String creator;

    @Column(name = "is_deleted", columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除 0:否 1:是'")
    private Integer isDeleted;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
}
