package com.tcl.commonservice.service.dto;

public class AppArticleLikeParams {

    private Long articleId;
    private Long ssoid;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getSsoid() {
        return ssoid;
    }

    public void setSsoid(Long ssoid) {
        this.ssoid = ssoid;
    }
}
