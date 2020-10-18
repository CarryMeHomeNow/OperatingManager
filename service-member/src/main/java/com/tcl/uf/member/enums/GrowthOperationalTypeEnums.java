package com.tcl.uf.member.enums;

/**
 * @author youyun.xu
 * @ClassName: GrowthOperationalTypeEnums
 * @Description: 成长值流水操作类型
 * @date 2020/9/2 17:00
 */
public enum GrowthOperationalTypeEnums {
    ADD(1),
    SUBTRACT(2);

    GrowthOperationalTypeEnums(Integer value){}

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
