package com.tcl.commonservice.service.vo;

public class CouponVo {
    private String uuid;//	优惠券类型ID
    private String couponTypeName;//	优惠券名称
    private String denomination;//	面额
    private String startTime;//	开始时间
    private String endTime;//	结束时间
    private String scope;//	是否显示[0显示，1不显示（商品详情页和购物车）]
    private Integer issuedNum;//	发行量
    private String collectType;//	是否公开领取(0否；1是)
    private String usePC;//	是否pc可用
    private String useTV;//	是否tv可用
    private String useMT;//	是否移动端可用
    private String raleType;//	关联类型(1.全部可用；2关联商品；3关联分类)
    private String couponTypeCanUse;//	使用商品类型(1.配置使用商品类型的优惠券；0配置不可用商品的类型)
    private String limitNumType;//	是否限制数量(1无限领取；2限制领取)
    private String limitNum;//	限领数量
    private String couConditionType;//	是否限制使用金额(1无限制；2限制)
    private String couCondition;//	限制金额
    private String cansend;//	是否能转赠
    private String productUuids;//	关联商品uuid(多个逗号隔开)
    private String productCategoryUuids;//	关联分类
    private String orderNum;//	排序
    private String firstNote;//	第一备注
    private String secondNote;//	第二备注
    private String state;//	状态(是否展示在官网首页的状态（1有效，0失效）)
    private String channelType;//	渠道类型
    private String groupNo;//	券组编码
    private String groupDesc;//	券组描述
    private String terminalType;//	终端类型
    private String channelCouponUrl;//	渠道优惠券url

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCouponTypeName() {
        return couponTypeName;
    }

    public void setCouponTypeName(String couponTypeName) {
        this.couponTypeName = couponTypeName;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getIssuedNum() {
        return issuedNum;
    }

    public void setIssuedNum(Integer issuedNum) {
        this.issuedNum = issuedNum;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    public String getUsePC() {
        return usePC;
    }

    public void setUsePC(String usePC) {
        this.usePC = usePC;
    }

    public String getUseTV() {
        return useTV;
    }

    public void setUseTV(String useTV) {
        this.useTV = useTV;
    }

    public String getUseMT() {
        return useMT;
    }

    public void setUseMT(String useMT) {
        this.useMT = useMT;
    }

    public String getRaleType() {
        return raleType;
    }

    public void setRaleType(String raleType) {
        this.raleType = raleType;
    }

    public String getCouponTypeCanUse() {
        return couponTypeCanUse;
    }

    public void setCouponTypeCanUse(String couponTypeCanUse) {
        this.couponTypeCanUse = couponTypeCanUse;
    }

    public String getLimitNumType() {
        return limitNumType;
    }

    public void setLimitNumType(String limitNumType) {
        this.limitNumType = limitNumType;
    }

    public String getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(String limitNum) {
        this.limitNum = limitNum;
    }

    public String getCouConditionType() {
        return couConditionType;
    }

    public void setCouConditionType(String couConditionType) {
        this.couConditionType = couConditionType;
    }

    public String getCouCondition() {
        return couCondition;
    }

    public void setCouCondition(String couCondition) {
        this.couCondition = couCondition;
    }

    public String getCansend() {
        return cansend;
    }

    public void setCansend(String cansend) {
        this.cansend = cansend;
    }

    public String getProductUuids() {
        return productUuids;
    }

    public void setProductUuids(String productUuids) {
        this.productUuids = productUuids;
    }

    public String getProductCategoryUuids() {
        return productCategoryUuids;
    }

    public void setProductCategoryUuids(String productCategoryUuids) {
        this.productCategoryUuids = productCategoryUuids;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getFirstNote() {
        return firstNote;
    }

    public void setFirstNote(String firstNote) {
        this.firstNote = firstNote;
    }

    public String getSecondNote() {
        return secondNote;
    }

    public void setSecondNote(String secondNote) {
        this.secondNote = secondNote;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getChannelCouponUrl() {
        return channelCouponUrl;
    }

    public void setChannelCouponUrl(String channelCouponUrl) {
        this.channelCouponUrl = channelCouponUrl;
    }
}
