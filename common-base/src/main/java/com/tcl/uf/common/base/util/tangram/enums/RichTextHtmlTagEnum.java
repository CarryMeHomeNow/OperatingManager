package com.tcl.uf.common.base.util.tangram.enums;

/**
 * @Author thj.
 * @Date Created in 2020/8/7 16:35
 * @description 富文本编辑器支持的HTML标签
 */
public enum RichTextHtmlTagEnum {

    P("p"), SPAN("span"), IMG("img"), VIDEO("video"), STRONG("strong"), A("a"), DIV("div");

    private String value;

    RichTextHtmlTagEnum(String value) {
        this.value = value;
    }

    public String tag() {
        return this.value;
    }
}
