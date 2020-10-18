package com.tcl.uf.advert.vo;

public class ResourceAppListVo {

    private String locationName;
    private Integer locationFrameId;
    private Long resourceId;
    private Long departmentId;
    private String departmentName;
    private String adTitle;
    private String adPicUrl;
    private String adLinkUrl;
    private String createTime;
    private String testAccount;

    public ResourceAppListVo(Integer locationFrameId, String locationName, String adPicUrl, String adLinkUrl, String createTime) {
        this.locationFrameId = locationFrameId;
        this.locationName = locationName;
        this.adPicUrl = adPicUrl;
        this.adLinkUrl = adLinkUrl;
        this.createTime = createTime;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getLocationFrameId() {
        return locationFrameId;
    }

    public void setLocationFrameId(Integer locationFrameId) {
        this.locationFrameId = locationFrameId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdPicUrl() {
        return adPicUrl;
    }

    public void setAdPicUrl(String adPicUrl) {
        this.adPicUrl = adPicUrl;
    }

    public String getAdLinkUrl() {
        return adLinkUrl;
    }

    public void setAdLinkUrl(String adLinkUrl) {
        this.adLinkUrl = adLinkUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTestAccount() {
        return testAccount;
    }

    public void setTestAccount(String testAccount) {
        this.testAccount = testAccount;
    }
}
