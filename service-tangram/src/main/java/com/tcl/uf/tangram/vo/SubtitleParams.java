package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/24.
 * @version 1.0
 * @Desc
 */
public class SubtitleParams {

    private String textContent = "优先接入";

    private String textAlignment = "left";

    private String textColor = "#000000";

    private Integer fontSize = 13;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
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

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }
}
