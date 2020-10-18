package com.tcl.uf.member.vo;

/**
 * @author gaofan
 * @date 2020/8/27 16:12
 * uf
 */
public class MemberRightsSetVo {

    /**
     * id
     */
    private Integer id;
    /**
     *  权益唯一标识
     */
    private String sn;
    /**
     * 权益名称
     */
    private String name;
    /**
     * 权益图标地址
     */
    private String coverUrl;
    /**
     * 获取条件
     */
    private String getCondition;
    /**
     * 权益介绍
     */
    private String description;
    /**
     * 领取链接
     */
    private String jumpUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getGetCondition() {
        return getCondition;
    }

    public void setGetCondition(String getCondition) {
        this.getCondition = getCondition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
