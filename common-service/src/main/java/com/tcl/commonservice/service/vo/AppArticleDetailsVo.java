package com.tcl.commonservice.service.vo;

public class AppArticleDetailsVo {
    // 文章ID
    private Long articleId;
    // 文章标题
    private String title;
    // 文章浏览数
    private Integer browseNum;
    // 文章tangram内容
    private String contentTangramJson;

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
}
