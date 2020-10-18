package com.tcl.uf.common.base.util.tangram.enums;

/**
 * @Author thj.
 * @Date Created in 2020/8/3 14:48
 * @description tangram布局类型
 */
public enum LayoutTypeEnum {

    ONE_COLUMN("container-oneColumn"),
    TWO_COLUMN("container-twoColumn"),
    THREE_COLUMN("container-threeColumn"),
    FOUR_COLUMN("container-fourColumn"),
    FIVE_COLUMN("container-fiveColumn"),
    ONE_PLUSN("container-onePlusN"),
    FLOW("container-flow");


    private String value;

    LayoutTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
