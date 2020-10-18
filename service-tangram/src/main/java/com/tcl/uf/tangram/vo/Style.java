package com.tcl.uf.tangram.vo;

import java.util.List;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 */
public class Style {

    private Integer width;

    private String bgColor;

    private List<String> margin;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public List<String> getMargin() {
        return margin;
    }

    public void setMargin(List<String> margin) {
        this.margin = margin;
    }
}
