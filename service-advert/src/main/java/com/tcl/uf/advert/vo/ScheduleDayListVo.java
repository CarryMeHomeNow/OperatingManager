package com.tcl.uf.advert.vo;

import java.util.Map;

public class ScheduleDayListVo {

    private Long locId;

    private Long groupId;

    private String groupName;

    private Integer frameId;

    private String frameName;

    private Map<String, String> dayMap;

    public ScheduleDayListVo(Long locId, Long groupId, String groupName, Integer frameId, String frameName) {
        this.locId = locId;
        this.groupId = groupId;
        this.groupName = groupName;
        this.frameId = frameId;
        this.frameName = frameName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getFrameId() {
        return frameId;
    }

    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    public String getFrameName() {
        return "第"+frameName+"帧";
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }

    public Map<String, String> getDayMap() {
        return dayMap;
    }

    public void setDayMap(Map<String, String> dayMap) {
        this.dayMap = dayMap;
    }
}
