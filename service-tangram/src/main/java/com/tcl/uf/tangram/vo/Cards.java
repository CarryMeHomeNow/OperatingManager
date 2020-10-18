package com.tcl.uf.tangram.vo;

import java.util.List;

/**
 * @author zhongfk on 2020/9/3.
 * @version 1.0
 */
public class Cards {
    private String type = "timeline";
    private String cornerRadius;
    private Style style;
    private ImgParams imgParams;
    private TitleParams titleParams;
    private ReadCount readCount;
    private ActionParams actionParams;
    private String tips;
    private List<Cards> cards;

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

    public ReadCount getReadCount() {
        return readCount;
    }

    public void setReadCount(ReadCount readCount) {
        this.readCount = readCount;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }
}
