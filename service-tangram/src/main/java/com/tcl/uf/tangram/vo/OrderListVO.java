package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/28.
 * @version 1.0
 */
public class OrderListVO {

    private String type;
    private Style style;
    private OrderParams orderParams;
    private ImgParams imgParams;
    private TimeParams timeParams;
    private TitleParams titleParams;
    private StatusParams statusParams;
    private PointsParams pointsParams;
    private Integer num;
    private ButtonParams buttonParams;

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

    public OrderParams getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(OrderParams orderParams) {
        this.orderParams = orderParams;
    }

    public ImgParams getImgParams() {
        return imgParams;
    }

    public void setImgParams(ImgParams imgParams) {
        this.imgParams = imgParams;
    }

    public TimeParams getTimeParams() {
        return timeParams;
    }

    public void setTimeParams(TimeParams timeParams) {
        this.timeParams = timeParams;
    }

    public TitleParams getTitleParams() {
        return titleParams;
    }

    public void setTitleParams(TitleParams titleParams) {
        this.titleParams = titleParams;
    }

    public StatusParams getStatusParams() {
        return statusParams;
    }

    public void setStatusParams(StatusParams statusParams) {
        this.statusParams = statusParams;
    }

    public PointsParams getPointsParams() {
        return pointsParams;
    }

    public void setPointsParams(PointsParams pointsParams) {
        this.pointsParams = pointsParams;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public ButtonParams getButtonParams() {
        return buttonParams;
    }

    public void setButtonParams(ButtonParams buttonParams) {
        this.buttonParams = buttonParams;
    }
}
