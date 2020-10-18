package com.tcl.uf.content.dto;

import java.util.List;

public class ArticleTopDto {

    private Long articleId;//文章编码
    private List<Long> section; //置顶版块
    private String topDuration;//置顶时长
    private Long topPosition;//置顶位置顺序

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public List<Long> getSection() {
        return section;
    }

    public void setSection(List<Long> section) {
        this.section = section;
    }

    public String getTopDuration() {
        return topDuration;
    }

    public void setTopDuration(String topDuration) {
        this.topDuration = topDuration;
    }

    public Long getTopPosition() {
        return topPosition;
    }

    public void setTopPosition(Long topPosition) {
        this.topPosition = topPosition;
    }
}
