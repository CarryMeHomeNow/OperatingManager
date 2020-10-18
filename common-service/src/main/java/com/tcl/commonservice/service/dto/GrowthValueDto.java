package com.tcl.commonservice.service.dto;

/**
 * @author youyun.xu
 * @ClassName: GrowthValueDto
 * @Description: 成长值对象
 * @date 2020/8/31 10:30
 */
public class GrowthValueDto {
    private Long ssoid;  //用户唯一标识
    private int growthValue;//成长值
    private String comment;//备注
    private String source;//渠道来源

    public Long getSsoid() {
        return ssoid;
    }

    public void setSsoid(Long ssoid) {
        this.ssoid = ssoid;
    }

    public int getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(int growthValue) {
        this.growthValue = growthValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
