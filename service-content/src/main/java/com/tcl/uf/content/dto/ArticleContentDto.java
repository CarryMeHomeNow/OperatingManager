package com.tcl.uf.content.dto;

import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: ArticleContentDto
 * @Description: 文章内容实体
 * @date 2020/7/23 10:01
 */
public class ArticleContentDto {

    private Long articleId;
    private String title; //文章标题
    private String contentHtml;//文章内容HTML
    private String articleCover;//文章封面
    private String articleSummary;//文章摘要
    private String advertisement;//是否广告
    private List<Long> section; //所属版块
    private Integer baseNum;//浏览基数

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

    public List<Long> getSection() {
        return section;
    }

    public void setSection(List<Long> section) {
        this.section = section;
    }

    public Integer getBaseNum() {
        return baseNum;
    }

    public void setBaseNum(Integer baseNum) {
        this.baseNum = baseNum;
    }
}
