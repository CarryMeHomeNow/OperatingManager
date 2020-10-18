package com.tcl.commonservice.service.vo;

import java.util.List;

public class ProductVo {
    private String uuid;
    private String productNo;//商品sku
    private String productName;//商品名称
    private String sellingPrice;//售价
    private Integer stock;//库存
    private String categoryUuid;
    private String categoryName;//商品分类(例如：全部分类>智能硬件)
    private String updateTime;//更新时间
    private String shelvesTime;
    private String terminalType;//终端类型(01 pc，02手机，03电视)
    private String buyState;//是否可售(1可售，0不可售)
    private String productType;//商品类型
    private String auditState;//审核状态
    private String storeUuid;
    private String salsnum;
    private String browseVolume;
    private String visitorVolume;
    private String lockNum;
    private String imageUrl;//图片封面
    private String platformUuid;
    private List<ProductMultiImageVo> productMutiImageDtoList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategoryUuid() {
        return categoryUuid;
    }

    public void setCategoryUuid(String categoryUuid) {
        this.categoryUuid = categoryUuid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getShelvesTime() {
        return shelvesTime;
    }

    public void setShelvesTime(String shelvesTime) {
        this.shelvesTime = shelvesTime;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getBuyState() {
        return buyState;
    }

    public void setBuyState(String buyState) {
        this.buyState = buyState;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid;
    }

    public String getSalsnum() {
        return salsnum;
    }

    public void setSalsnum(String salsnum) {
        this.salsnum = salsnum;
    }

    public String getBrowseVolume() {
        return browseVolume;
    }

    public void setBrowseVolume(String browseVolume) {
        this.browseVolume = browseVolume;
    }

    public String getVisitorVolume() {
        return visitorVolume;
    }

    public void setVisitorVolume(String visitorVolume) {
        this.visitorVolume = visitorVolume;
    }

    public String getLockNum() {
        return lockNum;
    }

    public void setLockNum(String lockNum) {
        this.lockNum = lockNum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlatformUuid() {
        return platformUuid;
    }

    public void setPlatformUuid(String platformUuid) {
        this.platformUuid = platformUuid;
    }

    public List<ProductMultiImageVo> getProductMutiImageDtoList() {
        return productMutiImageDtoList;
    }

    public void setProductMutiImageDtoList(List<ProductMultiImageVo> productMutiImageDtoList) {
        this.productMutiImageDtoList = productMutiImageDtoList;
    }
}
