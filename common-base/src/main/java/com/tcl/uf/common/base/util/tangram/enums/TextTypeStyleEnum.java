package com.tcl.uf.common.base.util.tangram.enums;

/**
 * @Author thj.
 * @Date Created in 2020/8/3 14:46
 * @description tangram的text类型item的样式属性
 */
public enum TextTypeStyleEnum {

    TEXT_ALIGMENT("text-align"),
    BG_COLOR("background-color"),
    TEXT_BG_COLOR("background-color"),
    FONT_SIZE("font-size"),
    LINE_SPACE("line-height"),
    LINK_URL("href"),
    TEXT_COLOR("color");

    private String htmlStyleKey;

    TextTypeStyleEnum(String htmlStyleKey) {
        this.htmlStyleKey = htmlStyleKey;
    }

    public String htmlStyleKey() {
        return htmlStyleKey;
    }
}
