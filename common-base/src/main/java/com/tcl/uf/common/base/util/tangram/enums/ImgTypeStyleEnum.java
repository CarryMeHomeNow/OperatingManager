package com.tcl.uf.common.base.util.tangram.enums;

/**
 * @Author thj.
 * @Date Created in 2020/8/4 13:42
 * @description tangram图片属性
 */
public enum ImgTypeStyleEnum {

    IMG_URL("src"),
    IMG_PLACEHOLDER("no such"),
    IMG_WIDTH("width"),
    IMG_HEIGHT("height"),
    CORNER_RADIUS("border-radius");

    private String htmlStyleKey;

    ImgTypeStyleEnum(String htmlStyleKey) {
        this.htmlStyleKey = htmlStyleKey;
    }

    public String htmlStyleKey() {
        return htmlStyleKey;
    }
}
