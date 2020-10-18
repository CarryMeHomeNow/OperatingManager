package com.tcl.uf.advert.vo;

public class LocationDetailVo {

    private Long id;

    private Long groupId;

    private String title;

    private String groupName;

    private Integer frameId;

    private Integer picWidth;

    private Integer picHeight;

    private Integer picSize;

    private Integer frameNum;

    private String defaultPic;

    private String defaultUrl;

    private Integer status;

    private Integer auditStatus;

    private String creator;

    private String editor;

    private String createTime;

    private String updateTime;

    private String offlineTime;

    private String onlineTime;

    public LocationDetailVo() {
    }

    public LocationDetailVo(Long id, String title, Integer frameId, String defaultPic, String defaultUrl, Integer status, Integer auditStatus, String creator, String createTime, String updateTime, String offlineTime, String onlineTime, String editor, Long groupId) {
        this.id = id;
        this.title = title;
        this.frameId = frameId;
        this.defaultPic = defaultPic;
        this.defaultUrl = defaultUrl;
        this.status = status;
        this.auditStatus = auditStatus;
        this.creator = creator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.offlineTime = offlineTime;
        this.onlineTime = onlineTime;
        this.editor = editor;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getPicWidth() {
        return picWidth;
    }

    public void setPicWidth(Integer picWidth) {
        this.picWidth = picWidth;
    }

    public Integer getPicHeight() {
        return picHeight;
    }

    public void setPicHeight(Integer picHeight) {
        this.picHeight = picHeight;
    }

    public Integer getPicSize() {
        return picSize;
    }

    public void setPicSize(Integer picSize) {
        this.picSize = picSize;
    }

    public Integer getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(Integer frameNum) {
        this.frameNum = frameNum;
    }

    public String getDefaultPic() {
        return defaultPic;
    }

    public void setDefaultPic(String defaultPic) {
        this.defaultPic = defaultPic;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(String offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
