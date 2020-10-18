package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/29.
 * @version 1.0
 * @Desc 优惠券
 */
public class CouponsVO {

    private String type;
    private Style style;
    private StatusParams statusParams;
    private TitleParams titleParams;
    private ExpireTimeParams expireTimeParams;
    private RangParams rangParams;
    private CouponTypeParams couponTypeParams;
    private ButtonParams buttonParams;
    private Boolean readPointStatus;
    private ActionParams actionParams;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public StatusParams getStatusParams() {
        return statusParams;
    }

    public void setStatusParams(StatusParams statusParams) {
        this.statusParams = statusParams;
    }

    public TitleParams getTitleParams() {
        return titleParams;
    }

    public void setTitleParams(TitleParams titleParams) {
        this.titleParams = titleParams;
    }

    public ExpireTimeParams getExpireTimeParams() {
        return expireTimeParams;
    }

    public void setExpireTimeParams(ExpireTimeParams expireTimeParams) {
        this.expireTimeParams = expireTimeParams;
    }

    public RangParams getRangParams() {
        return rangParams;
    }

    public void setRangParams(RangParams rangParams) {
        this.rangParams = rangParams;
    }

    public CouponTypeParams getCouponTypeParams() {
        return couponTypeParams;
    }

    public void setCouponTypeParams(CouponTypeParams couponTypeParams) {
        this.couponTypeParams = couponTypeParams;
    }

    public ButtonParams getButtonParams() {
        return buttonParams;
    }

    public void setButtonParams(ButtonParams buttonParams) {
        this.buttonParams = buttonParams;
    }

    public Boolean getReadPointStatus() {
        return readPointStatus;
    }

    public void setReadPointStatus(Boolean readPointStatus) {
        this.readPointStatus = readPointStatus;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }
}
