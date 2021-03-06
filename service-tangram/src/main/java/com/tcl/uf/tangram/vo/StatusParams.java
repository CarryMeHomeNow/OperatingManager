package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/28.
 * @version 1.0
 */
public class StatusParams {
    private String textContent;
    private String textAliment;
    private String textColor;
    private Double fontSize;
    private Integer lineSpace;

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

    public Double getFontSize() {
        return fontSize;
    }

    public void setFontSize(Double fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(Integer lineSpace) {
        this.lineSpace = lineSpace;
    }
}
