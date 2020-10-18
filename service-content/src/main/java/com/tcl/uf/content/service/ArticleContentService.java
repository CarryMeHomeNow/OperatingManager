package com.tcl.uf.content.service;

import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.content.dto.AppArticleListParams;
import com.tcl.uf.content.dto.AppArticleRandParams;
import com.tcl.uf.content.dto.ArticleContentDto;
import com.tcl.uf.content.dto.ArticleContentParams;
import com.tcl.uf.content.dto.ArticleTopDto;
import com.tcl.uf.content.vo.AppArticleDetailsVo;
import com.tcl.uf.content.vo.AppArticleListVo;
import com.tcl.uf.content.vo.ArticleContentDetailsVo;
import com.tcl.uf.content.vo.ArticleContentVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleContentService {

    Long saveOrUpdate(ArticleContentDto articleContentDto,TokenData info);

    ArticleContentDetailsVo findArticleContent(Long id);

    ListWithPage<ArticleContentVo> findList(Pageable pageable, ArticleContentParams articleContentParams);

    void articleTop(ArticleTopDto articleTopDto);

    void cancelArticleTop(Long articleId);

    void delete(List<Long> articleIds);

    void offline(List<Long> articleIds);

    void publish(List<Long> articleIds);

    ListWithPage<AppArticleListVo> appArticleList(AppArticleListParams params);

    AppArticleDetailsVo appArticleDetail(Long articleId, Long ssoid);

    List<AppArticleListVo> appArticleRand(AppArticleRandParams appArticleRandParams);

    void addBrowseNum();

    void updateBrowse(Integer browseBase,Long articleId);
}
