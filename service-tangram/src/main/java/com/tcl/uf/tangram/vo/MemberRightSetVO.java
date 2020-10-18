package com.tcl.uf.tangram.vo;

import java.util.List;

/**
 * @author zhongfk on 2020/8/24.
 * @version 1.0
 * @Desc 会员权益
 */
public class MemberRightSetVO {

    private String type = "serviceEquityCard";

    private Style style;

    private ImgParams imgParams;

    private TitleParams titleParams;

    private ActionParams actionParams;

    private SubtitleParams subtitleParams;

    private TextParams textParams;

    private List<String> imgEdgeInsets;

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

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }

    public SubtitleParams getSubtitleParams() {
        return subtitleParams;
    }

    public void setSubtitleParams(SubtitleParams subtitleParams) {
        this.subtitleParams = subtitleParams;
    }

    public TextParams getTextParams() {
        return textParams;
    }

    public void setTextParams(TextParams textParams) {
        this.textParams = textParams;
    }

    public List<String> getImgEdgeInsets() {
        return imgEdgeInsets;
    }

    public void setImgEdgeInsets(List<String> imgEdgeInsets) {
        this.imgEdgeInsets = imgEdgeInsets;
    }
}
