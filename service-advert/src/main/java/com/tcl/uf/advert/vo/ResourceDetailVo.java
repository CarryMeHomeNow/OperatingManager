package com.tcl.uf.advert.vo;

public class ResourceDetailVo {

    private Long id;

    private String seqNo;

    private Long locId;

    private String locName;

    private Long groupId;

    private String groupName;

    private Integer frameId;

    private Long departmentId;

    private String departmentName;

    private String effectiveDate;

    private String adTitle;

    private String adPic;

    private String adUrl;

    private Integer testFlag;

    private String testAccount;

    private String editor;

    private String creator;

    private Integer status;

    private Integer auditStatus;

    private String auditTime;

    private String createTime;

    private String updateTime;

    public ResourceDetailVo() {
    }

    public ResourceDetailVo(Long id, String seqNo, Long locId, Long departmentId, String adTitle, String adPic, String adUrl, Integer testFlag, String testAccount, String editor, String creator, Integer status, Integer auditStatus, Long groupId, Integer frameId) {
        this.id = id;
        this.seqNo = seqNo;
        this.locId = locId;
        this.departmentId = departmentId;
        this.adTitle = adTitle;
        this.adPic = adPic;
        this.adUrl = adUrl;
        this.testFlag = testFlag;
        this.testAccount = testAccount;
        this.editor = editor;
        this.creator = creator;
        this.status = status;
        this.auditStatus = auditStatus;
        this.groupId = groupId;
        this.frameId = frameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeqNo() {
        return groupId +"_"+ frameId +"_"+ effectiveDate +"_"+ seqNo ;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
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

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
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

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
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
}
