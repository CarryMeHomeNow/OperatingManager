package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 */
public class TextParams {
    /**
     * 售后名称
     */
    private String textContent;
    private String textPosition = "bottom";
    private String textAlignment = "center";
    private String textColor = "#000000";
    private Integer fontSize = 12;
    private Integer lineSpace = 4;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextPosition() {
        return textPosition;
    }

    public void setTextPosition(String textPosition) {
        this.textPosition = textPosition;
    }

    public String getTextAlignment() {
        return textAlignment;
    }

    public void setTextAlignment(String textAlignment) {
        this.textAlignment = textAlignment;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColo) {
        this.textColor = textColo;
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
}
