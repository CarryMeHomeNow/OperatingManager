package com.tcl.uf.advert.dto;

/**
 * @author linhuimin@tcl.com
 * @ClassName: ResourceAppListParams
 * @Description:
 * @date 2020/8/20 15:23
 */
public class ResourceAppListParams {

    private String locationCode; //广告组编码
    private Integer isTest; //是否测试

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }
}
