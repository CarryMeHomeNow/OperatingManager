package com.tcl.uf.common.base.util.tangram.enums;

/**
 * @Author thj.
 * @Date Created in 2020/8/3 15:54
 * @description 富文本tangram的item类型
 */
public enum ItemTypeEnum {

    TEXT("text"),
    IMAGE("image"),
    IMAGE_TEXT("image_text"),
    VIDEO("video"),
    COMMODITY_LIST("commodityList"),
    COMMODITY_GRID("commodityGrid"),
    COUPON("coupon");

    private String value;

    ItemTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
