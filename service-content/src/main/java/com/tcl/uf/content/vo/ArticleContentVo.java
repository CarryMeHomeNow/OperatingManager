package com.tcl.uf.content.vo;

/**
 * @author youyun.xu
 * @ClassName: ${CLASSNAME}
 * @Description: 文章内容管理
 * @date 2020/7/27 16:17
 */
public class ArticleContentVo {

    private Long articleId;//文章编码
    private String title; //文章标题
    private String contentHtml;//文章内容HTML
    private String contentTangramJson;//文章内容JSON
    private String articleCover;//文章封面
    private String articleSummary;//文章摘要
    private String advertisement;//是否广告
    private String status;//文章状态
    private String topStatus;//置顶状态
    private Integer likeNum;//点赞数
    private Integer browseNum;//浏览数
    private Integer browseBase;//浏览基数

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

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getContentTangramJson() {
        return contentTangramJson;
    }

    public void setContentTangramJson(String contentTangramJson) {
        this.contentTangramJson = contentTangramJson;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTopStatus() {
        return topStatus;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public void setTopStatus(String topStatus) {
        this.topStatus = topStatus;
    }

    public Integer getBrowseBase() {
        return browseBase;
    }

    public void setBrowseBase(Integer browseBase) {
        this.browseBase = browseBase;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }
}
