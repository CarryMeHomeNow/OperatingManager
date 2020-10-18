package com.tcl.commonservice.service.vo;

import java.util.Objects;

public class ArticleSectionVo {

    private Long sectionId;
    private String sectionName;
    private String createTime;
    private String subTitle;
    private int position;

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "{" +
                "sectionId:" + sectionId +
                ", sectionName:'" + sectionName + '\'' +
                ", createTime:'" + createTime + '\'' +
                ", subTitle:'" + subTitle + '\'' +
                ", position:" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleSectionVo that = (ArticleSectionVo) o;
        return position == that.position &&
                Objects.equals(sectionId, that.sectionId) &&
                Objects.equals(sectionName, that.sectionName) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(subTitle, that.subTitle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sectionId, sectionName, createTime, subTitle, position);
    }
}
