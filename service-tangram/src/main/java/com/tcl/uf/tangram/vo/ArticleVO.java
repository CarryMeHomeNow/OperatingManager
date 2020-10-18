package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 * @Desc 文章列表
 */
public class ArticleVO {
    private String type = "timeline";
    private String cornerRadius;
    private Style style;
    private ImgParams imgParams;
    private TitleParams titleParams;
    private ReadCount readCount;
    private ActionParams actionParams;
    private String tips;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(String cornerRadius) {
        this.cornerRadius = cornerRadius;
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


    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }

    public ReadCount getReadCount() {
        return readCount;
    }

    public void setReadCount(ReadCount readCount) {
        this.readCount = readCount;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "type='" + type + '\'' +
                ", cornerRadius='" + cornerRadius + '\'' +
                ", style=" + style +
                ", imgParams=" + imgParams +
                ", titleParams=" + titleParams +
                ", readCount=" + readCount +
                ", actionParams=" + actionParams +
                ", tips='" + tips + '\'' +
                '}';
    }
}
