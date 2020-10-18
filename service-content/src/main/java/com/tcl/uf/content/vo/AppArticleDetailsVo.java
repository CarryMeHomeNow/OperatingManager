package com.tcl.uf.content.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AppArticleDetailsVo {
    // 文章ID
    private Long articleId;
    // 文章标题
    private String title;
    // 文章浏览数
    private Integer browseNum;
    // 创建时间
    @JsonFormat(timezone = "GMT+8", locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    // 文章tangram内容
    private String contentTangramJson;
    // 登陆用户是否已经点赞
    private boolean userLike;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public String getContentTangramJson() {
        return contentTangramJson;
    }

    public void setContentTangramJson(String contentTangramJson) {
        this.contentTangramJson = contentTangramJson;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public boolean getUserLike() {
        return userLike;
    }

    public void setUserLike(boolean userLike) {
        this.userLike = userLike;
    }
}
