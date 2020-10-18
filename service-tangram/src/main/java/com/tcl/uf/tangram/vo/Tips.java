package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 */
public class Tips {
    /**
     * 广告标识
     */
    private String tip;
    private String tipColor;
    private Integer fontSize;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTipColor() {
        return tipColor;
    }

    public void setTipColor(String tipColor) {
        this.tipColor = tipColor;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }
}
