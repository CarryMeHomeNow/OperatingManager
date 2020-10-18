package com.tcl.uf.advert.dto;

public class LocationScheduleDto {

    private Long locId;

    private Long groupId;

    private Integer frameId;

    private Long departmentId;

    private String effectiveDate;

    public LocationScheduleDto() {
    }

    public LocationScheduleDto(Long locId, Long departmentId, String effectiveDate) {
        this.locId = locId;
        this.departmentId = departmentId;
        this.effectiveDate = effectiveDate;
    }

    public LocationScheduleDto(Long locId, Long groupId, Integer frameId, Long departmentId, String effectiveDate) {
        this.locId = locId;
        this.groupId = groupId;
        this.frameId = frameId;
        this.departmentId = departmentId;
        this.effectiveDate = effectiveDate;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
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
}
