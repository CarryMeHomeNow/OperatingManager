package com.tcl.uf.content.service;

import com.tcl.uf.content.dto.AppArticleLikeParams;

/**
 * @Author thj.
 * @Date Created in 2020/8/12 11:11
 * @description 文章点赞功能
 */
public interface ArticleLikeService {


    void like(AppArticleLikeParams appArticleLikeParams);

    void cancelLike(AppArticleLikeParams appArticleLikeParams);
}
