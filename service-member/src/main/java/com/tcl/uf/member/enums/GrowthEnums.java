package com.tcl.uf.member.enums;

public enum GrowthEnums {

    GROWTH_SOURCE_FIRST_LOGIN_DEVICE(1,"设备首次登录账户"),
    GROWTH_SOURCE_FIRST_BIND_DEVICE(2,"首次配网绑定设备"),
    GROWTH_SOURCE_BUY_VIRTUAL_PRODUCTS(3,"购买虚拟权益价值产品(影视会员)"),
    GROWTH_SOURCE_BUY_BIND_DEVICE(4,"购买增值服务(延保服务、清洗服务、送货服务)");

    GrowthEnums(Integer source,String sourceName) {}

    private Integer source;
    private String sourceName;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
