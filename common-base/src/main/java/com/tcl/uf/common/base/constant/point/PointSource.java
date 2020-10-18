package com.tcl.uf.common.base.constant.point;

public enum  PointSource {
    OTHER("其他渠道",0);

    private String sourceName;

    private int sourceType;
    private PointSource(String sourceName,int sourceType) {
        this.sourceName = sourceName;
        this.sourceType = sourceType;
    }
}
