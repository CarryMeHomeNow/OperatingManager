package com.tcl.commonservice.service.vo;

public class ProductMultiImageVo {

    private String uuid;//主键
    private String delFlag;// 删除标识
    private String opeTime;//操作时间
    private String oper;// 操作者
    private String basicImage; //基础图
    private String bigImage;//大图
    private String centerImage ;//中图
    private String position;//位置
    private String productUuid; //商品ID
    private String smallImage;//小图
    private String terminalType;//终端
    private String basicImageUrl;//基础图URL
    private String bigImageUrl;//大图URL
    private String centerImageUrl;//中图URL
    private String smallImageUrl;//小图URL

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getOpeTime() {
        return opeTime;
    }

    public void setOpeTime(String opeTime) {
        this.opeTime = opeTime;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getBasicImage() {
        return basicImage;
    }

    public void setBasicImage(String basicImage) {
        this.basicImage = basicImage;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getCenterImage() {
        return centerImage;
    }

    public void setCenterImage(String centerImage) {
        this.centerImage = centerImage;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getBasicImageUrl() {
        return basicImageUrl;
    }

    public void setBasicImageUrl(String basicImageUrl) {
        this.basicImageUrl = basicImageUrl;
    }

    public String getBigImageUrl() {
        return bigImageUrl;
    }

    public void setBigImageUrl(String bigImageUrl) {
        this.bigImageUrl = bigImageUrl;
    }

    public String getCenterImageUrl() {
        return centerImageUrl;
    }

    public void setCenterImageUrl(String centerImageUrl) {
        this.centerImageUrl = centerImageUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }
}
