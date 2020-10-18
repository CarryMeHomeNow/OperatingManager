package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 * @Desc 文章 阅读参数
 */
public class ReadCount {
    private Integer textContent;
    private String textAlignment;
    private String textColor;
    private Integer fontSize;
    private Integer lineSpace;

    public Integer getTextContent() {
        return textContent;
    }

    public void setTextContent(Integer textContent) {
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

    public Integer getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(Integer lineSpace) {
        this.lineSpace = lineSpace;
    }

    @Override
    public String toString() {
        return "ReadCount{" +
                "textContent=" + textContent +
                ", textAlignment='" + textAlignment + '\'' +
                ", textColor='" + textColor + '\'' +
                ", fontSize=" + fontSize +
                ", lineSpace=" + lineSpace +
                '}';
    }
}
