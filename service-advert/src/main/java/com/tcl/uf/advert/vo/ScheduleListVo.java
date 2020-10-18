package com.tcl.uf.advert.vo;

public class ScheduleListVo {

    private Long locId;

    private Long groupId;

    private Long departmentId;

    private String groupName;

    private String locName;

    private String departmentName;

    private Integer dayOfWeek;

    private String effectiveDay;

    public ScheduleListVo(Long locId, Long groupId, Long departmentId, String groupName, String locName, String departmentName, Integer dayOfWeek, String effectiveDay) {
        this.locId = locId;
        this.groupId = groupId;
        this.departmentId = departmentId;
        this.groupName = groupName;
        this.locName = locName;
        this.departmentName = departmentName;
        this.dayOfWeek = dayOfWeek;
        this.effectiveDay = effectiveDay;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLocName() {
        return "第"+locName+"帧";
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getEffectiveDay() {
        return effectiveDay;
    }

    public void setEffectiveDay(String effectiveDay) {
        this.effectiveDay = effectiveDay;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
