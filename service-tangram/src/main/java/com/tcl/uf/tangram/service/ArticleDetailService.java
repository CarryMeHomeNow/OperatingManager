package com.tcl.uf.tangram.service;

/**
 * @author zhongfk on 2020/8/22.
 * @version 1.0
 */
public interface ArticleDetailService {
    /**
     * 文章详情
     * @param
     * @return
     */
    String articleDetail(String reqCode,Long articleId,Long sectionId);
}
