package com.tcl.uf.content.dto;

/**
 * @author youyun.xu
 * @ClassName: ArticleContentParams
 * @Description: 查询文章内容信息
 * @date 2020/7/27 16:15
 */
public class ArticleContentParams {

    private String title;
    private Long sectionId;
    private String status;
    private String topStatus;

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
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

    public void setTopStatus(String topStatus) {
        this.topStatus = topStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
