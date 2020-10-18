package com.tcl.uf.content.dto;

import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: ${CLASSNAME}
 * @Description: 文章内容管理
 * @date 2020/8/17 20:24
 */
public class ArticleRejectDto {
    private List<Long> articleId;
    private String remarks;

    public List<Long> getArticleId() {
        return articleId;
    }

    public void setArticleId(List<Long> articleId) {
        this.articleId = articleId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
