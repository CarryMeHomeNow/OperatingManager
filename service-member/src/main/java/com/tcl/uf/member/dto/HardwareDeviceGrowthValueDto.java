package com.tcl.uf.member.dto;

/**
 * @author youyun.xu
 * @ClassName: HardwareDeviceGrowthValueDto
 * @Description: 成长值对象
 * @date 2020/8/31 10:30
 */
public class HardwareDeviceGrowthValueDto extends GrowthValueDto{

    private String sn;//设备序列号
    private String model;//设备型号
    private String comment;//备注
    private Integer source;//来源

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
