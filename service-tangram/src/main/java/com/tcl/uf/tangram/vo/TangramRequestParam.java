package com.tcl.uf.tangram.vo;

import javax.validation.constraints.NotNull;

/**
 * @Desc : APP请求参数VO
 * @Author Mr.HePeng
 * @Date 2020/8/8 16:49
 */
public class TangramRequestParam {

    /** 请求编号 */
    @NotNull(message = "模板编号不能为空")
    private String reqCode;
    /**板块id */
    private Long sectionId;
    /**
     * 年月 年月日
     */
    private String date;
    /**当前页*/
    private Integer page;
    /**当前页大小 */
    private Integer size;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 账号id
     */
    private Long accountId;
    /**
     * 权益id
     */
    private Integer rightSetId;
    /**
     * 业务id
     */
    private Long businessId;
    /**
     * 版本号
     */
    private String version;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRightSetId() {
        return rightSetId;
    }

    public void setRightSetId(Integer rightSetId) {
        this.rightSetId = rightSetId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
