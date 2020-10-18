package com.tcl.uf.content.service.impl;

import com.tcl.commondb.content.model.ArticleSectionRelationModel;
import com.tcl.commondb.content.repository.ArticleContentModelRepository;
import com.tcl.commondb.content.repository.ArticleSectionRelationModelRepository;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.content.consts.ArticleConstants;
import com.tcl.uf.content.dto.AppArticleListParams;
import com.tcl.uf.content.service.ArticleCacheService;
import com.tcl.uf.content.utils.RedisUtils;
import com.tcl.uf.content.vo.AppArticleCacheDetailsVo;
import com.tcl.uf.content.vo.AppArticleListVo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author thj.
 * @Date Created in 2020/8/12 15:06
 * @description 文章缓存服务
 */
@Service("articleCacheService")
public class ArticleCacheServiceImpl implements ArticleCacheService {

    @Autowired
    private ArticleContentModelRepository contentModelRepository;

    @Autowired
    private ArticleSectionRelationModelRepository relationModelRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void clearArticleCache(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.forEach(item -> redisUtils.del(ArticleConstants.REDIS_NAMESPACE_ARTICLE + ":" + item));
    }

    @Override
    public void clearArticleCache(Long articleId) {
        redisUtils.del(ArticleConstants.REDIS_NAMESPACE_ARTICLE + ":" + articleId);
    }

    /**
     * 文章对象缓存
     *
     * @param articleId
     * @return
     */
    @Override
    public AppArticleCacheDetailsVo getArticle(Long articleId) {
        Object value = redisUtils.get(ArticleConstants.REDIS_NAMESPACE_ARTICLE + ":" + articleId);
        if (value != null) {
            return (AppArticleCacheDetailsVo) value;
        }
        List<Object[]> objects = contentModelRepository.queryArticleById(articleId);
        AppArticleCacheDetailsVo vo = null;
        if (!objects.isEmpty()) {
            vo = converToAppArticleCacheDetailsVo(objects);
            redisUtils.set(ArticleConstants.REDIS_NAMESPACE_ARTICLE + ":" + articleId, vo);
        }
        return vo;
    }

    private static AppArticleCacheDetailsVo converToAppArticleCacheDetailsVo(List<Object[]> list) {
        Object[] objects = list.get(0);
        AppArticleCacheDetailsVo vo = new AppArticleCacheDetailsVo();
        vo.setArticleId(Long.parseLong(objects[0].toString()));
        vo.setAdvertisement(objects[1] != null ? objects[1].toString() : "");
        vo.setArticleCover(objects[2] != null ? objects[2].toString() : "");
        vo.setTitle(objects[3] != null ? objects[3].toString() : "");
        int baseNum = objects[5] == null ? 0 : (Integer) objects[5];
        int browseNum = objects[4] == null ? 0 : (Integer) objects[4];
        vo.setBrowseNum(baseNum + browseNum);
        vo.setContentTangramJson(objects[6] != null ? objects[6].toString() : "");
        vo.setVideo(false);
        if (objects[7] != null) {
            Document doc = Jsoup.parse(objects[7].toString());
            vo.setVideo(!doc.body().getElementsByTag("video").isEmpty());
        }
        vo.setPublishTime(objects[8] != null ? (Date) objects[8] : null);
        vo.setLatest(((Date) objects[9]).getTime() > TimeUtils.addDay(new Date(), -7).getTime());
        return vo;
    }

    @Override
    public void increaseBrowseNum(Long articleId) {
        redisUtils.incr(getBrowseKey(articleId), 1L);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int decreaseBrowseNum(String key, Long articleId, Integer num) {
        int count = contentModelRepository.addBrowseNum(articleId, num);
        redisUtils.decr(key, num);
        // 更新文章缓存
        clearArticleCache(articleId);
        return count;
    }

    @Override
    public Set<String> keys(String pattern) {
        Set<String> set = redisUtils.keys(pattern);
        return set != null ? set : new HashSet<>();
    }

    @Override
    public int getBrowseNum(Long articleId) {
        Object value = redisUtils.get(getBrowseKey(articleId));
        return value != null ? Integer.valueOf(value.toString()) : 0;
    }

    @Override
    public void expireBrowseCache(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // 一天过期
        ids.forEach(item -> redisUtils.expire(getBrowseKey(item), 86400));
    }

    private static String getBrowseKey(Long articleId) {
        return ArticleConstants.REDIS_NAMESPACE_ARTICLE_BROWSE + ":" + articleId;
    }

    /**
     * 将生效（未删除、已审核、已发布）的文章放入redis set
     * key 版块ID ；value为版块下生效的文章ID
     *
     * @param sectionId
     * @return
     */
    @Override
    public List<Long> cacheEffectiveSection(Long sectionId) {
        List<Long> ids = relationModelRepository.queryEffectiveArticleId(sectionId);
        // 该版块下不存在生效的文章,存入一个默认值
        if (ids.isEmpty()) {
            ids.add(0L);
        }
        redisUtils.sSet(ArticleConstants.REDIS_NAMESPACE_SECTION_RELATION + sectionId, ids.toArray());
        return ids;
    }

    /**
     * 清除缓存:版块文章关系、app端文章列表
     *
     * @param sectionId
     */
    @Override
    public void clearEffectiveSectionCache(Long sectionId) {
        redisUtils.del(ArticleConstants.REDIS_NAMESPACE_SECTION_RELATION + sectionId);
        Set<String> set = redisUtils.keys(ArticleConstants.REDIS_NAMESPACE_ARTICLE_APP_LIST + "*");
        if (!set.isEmpty()) {
            redisUtils.del(new ArrayList<>(set));
        }
    }

    @Override
    public void clearEffectiveSectionCacheByArticle(Long articleId) {
        List<ArticleSectionRelationModel> list = relationModelRepository.findByArticleId(articleId);
        if (list.isEmpty()) {
            return;
        }
        Set<Long> ids = list.stream().map(ArticleSectionRelationModel::getSectionId).collect(Collectors.toSet());
        ids.forEach(this::clearEffectiveSectionCache);
    }

    @Override
    public void clearEffectiveSectionCacheByArticle(List<Long> articleIds) {
        List<ArticleSectionRelationModel> list = relationModelRepository.findByArticleIdIn(articleIds);
        if (list.isEmpty()) {
            return;
        }
        Set<Long> ids = list.stream().map(ArticleSectionRelationModel::getSectionId).collect(Collectors.toSet());
        ids.forEach(this::clearEffectiveSectionCache);
    }

    /**
     * 获取生效版块文章关系缓存
     *
     * @param sectionId
     * @return
     */
    @Override
    public List<Long> getEffectiveSection(Long sectionId) {
        Set<Object> set = redisUtils.sGet(ArticleConstants.REDIS_NAMESPACE_SECTION_RELATION + sectionId);
        if (set == null || set.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> idList = new ArrayList<>();
        set.forEach(item -> idList.add(Long.parseLong(item.toString())));
        return idList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListWithPage<AppArticleListVo> getAppArticleListCache(AppArticleListParams params) {
        String key = ArticleConstants.REDIS_NAMESPACE_ARTICLE_APP_LIST + params.getSectionId() + "_" + params.getPage() + "_" + params.getSize();
        Object value = redisUtils.get(key);
        ListWithPage<AppArticleListVo> resultList = null;
        if (value != null) {
            resultList = (ListWithPage<AppArticleListVo>) value;
        }
        return resultList;
    }

    @Override
    public void cacheAppArticleList(AppArticleListParams params, ListWithPage<AppArticleListVo> listWithPage, long ttl) {
        String key = ArticleConstants.REDIS_NAMESPACE_ARTICLE_APP_LIST + params.getSectionId() + "_" + params.getPage() + "_" + params.getSize();
        redisUtils.set(key, listWithPage, ttl);
    }
}
