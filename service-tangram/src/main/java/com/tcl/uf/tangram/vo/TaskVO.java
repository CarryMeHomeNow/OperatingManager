package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/27.
 * @version 1.0
 * @DESC 积分任务
 */
public class TaskVO {

    private String type;

    private Style style;

    private ImgParams imgParams;
    /**
     * 文章参数
     */
    private TitleParams titleParams;

    private PointValues pointValues;
    /**
     * 按钮参数
     */
    private ButtonParams buttonParams;
    /**
     * 按钮描述参数
     */
    private ButtonDescParams buttonDescParams;
    /**
     * 跳转参数
     */
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

    public ImgParams getImgParams() {
        return imgParams;
    }

    public void setImgParams(ImgParams imgParams) {
        this.imgParams = imgParams;
    }

    public TitleParams getTitleParams() {
        return titleParams;
    }

    public void setTitleParams(TitleParams titleParams) {
        this.titleParams = titleParams;
    }

    public PointValues getPointValues() {
        return pointValues;
    }

    public void setPointValues(PointValues pointValues) {
        this.pointValues = pointValues;
    }

    public ButtonParams getButtonParams() {
        return buttonParams;
    }

    public void setButtonParams(ButtonParams buttonParams) {
        this.buttonParams = buttonParams;
    }

    public ButtonDescParams getButtonDescParams() {
        return buttonDescParams;
    }

    public void setButtonDescParams(ButtonDescParams buttonDescParams) {
        this.buttonDescParams = buttonDescParams;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }
}
