package com.tcl.uf.tangram.vo;

import java.util.Arrays;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 * @Desc 售后服务
 */
public class AfterSales {

    private ImgParams imgParams;
    private TextParams textParams;
    private ActionParams actionParams;
    private String type = "imageText";
    private Style style;
    private String[] imgimgEdgeInsets = new String[]{"0","10", "25", "10"};
    private BadgeParams badgeParams;

    public ImgParams getImgParams() {
        return imgParams;
    }

    public void setImgParams(ImgParams imgParams) {
        this.imgParams = imgParams;
    }

    public TextParams getTextParams() {
        return textParams;
    }

    public void setTextParams(TextParams textParams) {
        this.textParams = textParams;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }

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

    public String[] getImgimgEdgeInsets() {
        return imgimgEdgeInsets;
    }

    public void setImgimgEdgeInsets(String[] imgimgEdgeInsets) {
        this.imgimgEdgeInsets = imgimgEdgeInsets;
    }

    public BadgeParams getBadgeParams() {
        return badgeParams;
    }

    public void setBadgeParams(BadgeParams badgeParams) {
        this.badgeParams = badgeParams;
    }

    @Override
    public String toString() {
        return "AfterSales{" +
                "imgParams=" + imgParams +
                ", textParams=" + textParams +
                ", actionParams=" + actionParams +
                ", type='" + type + '\'' +
                ", style=" + style +
                ", imgimgEdgeInsets=" + Arrays.toString(imgimgEdgeInsets) +
                ", badgeParams=" + badgeParams +
                '}';
    }
}
