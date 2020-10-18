package com.tcl.uf.common.base.util.tangram.enums;

/**
 * @Author thj.
 * @Date Created in 2020/8/4 10:46
 * @description tangram链接跳转类型
 */
public enum ActionTypeEnum {
    JUMP("jump"), REFRESH("refresh");

    private String value;

    ActionTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

