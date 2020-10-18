package com.tcl.uf.common.base.util.tangram.enums;

/**
 * @Author thj.
 * @Date Created in 2020/8/3 16:34
 * @description tangram富文本类型
 */
public enum RichTextTypeEnum {

    COLOR("color"), LINK("link"), FONT("font");

    private String value;

    RichTextTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
