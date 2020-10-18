package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/27.
 * @version 1.0
 */
public class ButtonParams {

    private String textContent;
    private String textAliment;
    private String textColor;
    private Integer fontSize;
    private Integer lineSpace;

    /**
     * 是否展示
     */
    private Boolean show;
    /**
     * 跳转参数
     */
    private ActionParams actionParams;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextAliment() {
        return textAliment;
    }

    public void setTextAliment(String textAliment) {
        this.textAliment = textAliment;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(Integer lineSpace) {
        this.lineSpace = lineSpace;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }
}
