package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/24.
 * @version 1.0
 * @Desc 成长值
 */
public class GrowthParams {
    /**
     * 当前成长值
     */
    private String currentValue;

    private String remainValue;

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getRemainValue() {
        return remainValue;
    }

    public void setRemainValue(String remainValue) {
        this.remainValue = remainValue;
    }

    @Override
    public String toString() {
        return "GrowthParams{" +
                "currentValue='" + currentValue + '\'' +
                ", remainValue='" + remainValue + '\'' +
                '}';
    }
}
