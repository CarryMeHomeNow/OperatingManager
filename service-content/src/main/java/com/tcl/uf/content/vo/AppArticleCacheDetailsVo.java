package com.tcl.uf.content.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Description 文章内容缓存对象
 * @Author TangHuaJie
 * @Date Created in 2020/9/1 17:50
 */
public class AppArticleCacheDetailsVo {

    private Long articleId;
    private String title;
    private Integer browseNum;
    @JsonFormat(timezone = "GMT+8", locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String contentTangramJson;
    private String advertisement;
    private String articleCover;
    private Boolean latest;
    private Boolean video;

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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getContentTangramJson() {
        return contentTangramJson;
    }

    public void setContentTangramJson(String contentTangramJson) {
        this.contentTangramJson = contentTangramJson;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public Boolean getLatest() {
        return latest;
    }

    public void setLatest(Boolean latest) {
        this.latest = latest;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }
}
