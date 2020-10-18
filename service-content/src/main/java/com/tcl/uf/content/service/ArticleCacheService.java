package com.tcl.uf.content.service;


import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.content.dto.AppArticleListParams;
import com.tcl.uf.content.vo.AppArticleCacheDetailsVo;
import com.tcl.uf.content.vo.AppArticleListVo;

import java.util.List;
import java.util.Set;

public interface ArticleCacheService {

    void clearArticleCache(List<Long> ids);

    void clearArticleCache(Long articleId);

    AppArticleCacheDetailsVo getArticle(Long articleId);

    void increaseBrowseNum(Long articleId);

    int decreaseBrowseNum(String key, Long articleId, Integer num);

    Set<String> keys(String pattern);

    int getBrowseNum(Long articleId);

    void expireBrowseCache(List<Long> ids);

    List<Long> cacheEffectiveSection(Long sectionId);

    void clearEffectiveSectionCache(Long sectionId);

    void clearEffectiveSectionCacheByArticle(Long articleId);

    void clearEffectiveSectionCacheByArticle(List<Long> articleIds);

    List<Long> getEffectiveSection(Long sectionId);

    ListWithPage<AppArticleListVo> getAppArticleListCache(AppArticleListParams params);

    void cacheAppArticleList(AppArticleListParams params, ListWithPage<AppArticleListVo> listWithPage, long ttl);

}
