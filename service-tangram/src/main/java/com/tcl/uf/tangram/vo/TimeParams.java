package com.tcl.uf.tangram.vo;

import java.util.Date;

/**
 * @author zhongfk on 2020/8/28.
 * @version 1.0
 */
public class TimeParams {
    private Date textContent;
    private String textAliment;
    private String textColor;
    private Integer fontSize;
    private Integer lineSpace;

    public Date getTextContent() {
        return textContent;
    }

    public void setTextContent(Date textContent) {
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
}
