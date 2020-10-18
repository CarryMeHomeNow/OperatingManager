package com.tcl.uf.content.service.impl;

import com.tcl.commondb.content.model.ArticleContentModel;
import com.tcl.commondb.content.model.ArticleSectionRelationModel;
import com.tcl.commondb.content.model.ArticleTopModel;
import com.tcl.commondb.content.repository.ArticleContentModelRepository;
import com.tcl.commondb.content.repository.ArticleLikeRecordRepository;
import com.tcl.commondb.content.repository.ArticleSectionRelationModelRepository;
import com.tcl.commondb.content.repository.ArticleTopModelRepository;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.util.StringsUtil;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.common.base.util.tangram.TangramRichTextUtil;
import com.tcl.uf.content.annotation.ArticleOperational;
import com.tcl.uf.content.consts.ArticleConstants;
import com.tcl.uf.content.dto.AppArticleListParams;
import com.tcl.uf.content.dto.AppArticleRandParams;
import com.tcl.uf.content.dto.ArticleContentDto;
import com.tcl.uf.content.dto.ArticleContentParams;
import com.tcl.uf.content.dto.ArticleTopDto;
import com.tcl.uf.content.service.ArticleCacheService;
import com.tcl.uf.content.service.ArticleContentService;
import com.tcl.uf.content.vo.AppArticleCacheDetailsVo;
import com.tcl.uf.content.vo.AppArticleDetailsVo;
import com.tcl.uf.content.vo.AppArticleListVo;
import com.tcl.uf.content.vo.ArticleContentDetailsVo;
import com.tcl.uf.content.vo.ArticleContentVo;
import jodd.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author youyun.xu
 * @ClassName: ArticleContentServiceImpl
 * @Description: 文章内容服务Service
 * @date 2020/7/23 15:23
 */
@Service("articleContentService")
public class ArticleContentServiceImpl implements ArticleContentService {

    private static final Logger log = LoggerFactory.getLogger(ArticleContentServiceImpl.class);

    @Autowired
    private ArticleContentModelRepository articleContentModelRepository;

    @Autowired
    private ArticleSectionRelationModelRepository articleSectionRelationModelRepository;

    @Autowired
    private ArticleTopModelRepository articleTopModelRepository;

    @Autowired
    private ArticleCacheService articleCacheService;

    @Autowired
    private ArticleLikeRecordRepository articleLikeRecordRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ArticleOperational(action = "保存或修改")
    public Long saveOrUpdate(ArticleContentDto articleContentDto, TokenData info) {
        //保存文章基本信息
        ArticleContentModel articleContentModel;
        if (articleContentDto.getArticleId() == null) {
            articleContentModel = new ArticleContentModel();
            articleContentModel.setCreateTime(new Date());
            articleContentModel.setUserId(info.getUsername());
            articleContentModel.setUseStatus(ArticleConstants.DELETE_FLAG_EFFECTIVE);
            articleContentModel.setStatus(ArticleConstants.CONTENT_STATUS_UNPUBLISH);
            articleContentModel.setTopStatus(ArticleConstants.TOP_STATUS_NO);
        } else {
            articleContentModel = articleContentModelRepository.findByIdEquals(articleContentDto.getArticleId());
            //删除文章和版块归属
            articleSectionRelationModelRepository.deleteRelationByArticleId(articleContentDto.getArticleId());
        }
        articleContentModel.setTitle(articleContentDto.getTitle());
        articleContentModel.setContentHtml(articleContentDto.getContentHtml());
        articleContentModel.setContentTangram(transform(articleContentDto.getContentHtml()));
        articleContentModel.setArticleCover(articleContentDto.getArticleCover());
        articleContentModel.setArticleSummary(articleContentDto.getArticleSummary());
        articleContentModel.setAdvertisement(articleContentDto.getAdvertisement());
        articleContentModel.setUpdateTime(new Date());
        articleContentModel.setBrowseBase(articleContentDto.getBaseNum());
        articleContentModelRepository.saveAndFlush(articleContentModel);

        //保存文章所属板块信息
        Long articleId = articleContentModel.getId();
        List<Long> sectionIds = articleContentDto.getSection();
        for (Long section : sectionIds) {
            ArticleSectionRelationModel articleSectionRelationModel = new ArticleSectionRelationModel();
            articleSectionRelationModel.setArticleId(articleId);
            articleSectionRelationModel.setSectionId(section);
            articleSectionRelationModel.setCreateTime(new Date());
            articleSectionRelationModelRepository.saveAndFlush(articleSectionRelationModel);
        }
        // 缓存文章
        articleCacheService.clearArticleCache(articleId);
        // 版块关系修改，更新版块文章缓存
        articleCacheService.clearEffectiveSectionCache(articleId);
        return articleContentModel.getId();
    }

    private String transform(String html) {
        return TangramRichTextUtil.toTangramJson(html);
    }

    @Override
    public ArticleContentDetailsVo findArticleContent(Long id) {
        ArticleContentDetailsVo articleContentDetailsVo = new ArticleContentDetailsVo();
        ArticleContentModel articleContentModel = articleContentModelRepository.findByIdEquals(id);
        if (articleContentModel != null) {
            articleContentDetailsVo.setArticleId(articleContentModel.getId());
            articleContentDetailsVo.setTitle(articleContentModel.getTitle());
            articleContentDetailsVo.setContentHtml(articleContentModel.getContentHtml());
            articleContentDetailsVo.setContentTangramJson(articleContentModel.getContentTangram());
            articleContentDetailsVo.setArticleCover(articleContentModel.getArticleCover());
            articleContentDetailsVo.setArticleSummary(articleContentModel.getArticleSummary());
            articleContentDetailsVo.setAdvertisement(articleContentModel.getAdvertisement());
            articleContentDetailsVo.setStatus(articleContentModel.getStatus());
            articleContentDetailsVo.setTopStatus(articleContentModel.getTopStatus());
            articleContentDetailsVo.setAuditStatus(articleContentModel.getAuditStatus());
            articleContentDetailsVo.setBrowseBase(articleContentModel.getBrowseBase());
            //关联版块信息
            List<ArticleSectionRelationModel> sectionRelation = articleSectionRelationModelRepository.findByArticleId(id);
            List<Long> sectionIds = new ArrayList<Long>();
            if (!CollectionUtils.isEmpty(sectionRelation)) {
                sectionRelation.stream().forEach(map -> {
                    sectionIds.add(map.getSectionId());
                });
            }
            articleContentDetailsVo.setSectionRelation(sectionIds);
        }
        return articleContentDetailsVo;
    }

    @Override
    public ListWithPage<ArticleContentVo> findList(Pageable pageable, ArticleContentParams articleContentParams) {
        Long sumTotal = null;
        List<ArticleContentVo> records = null;
        List<Long> ids = filter(articleContentParams);
        //构造初始状态排序条件
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
        Page<ArticleContentModel> result = articleContentModelRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> createConfigurationRecordQueryCondition(root, criteriaQuery, criteriaBuilder, articleContentParams, ids), page);
        //数据库实体对象转VO对象
        if (!result.isEmpty()) {
            sumTotal = result.getTotalElements();
            records = configurationRecordMemoryValueToViewObject(result.getContent());
        } else {
            sumTotal = Long.getLong("0");
            records = new ArrayList<>();
        }
        return PageUtils.formatData(records, pageable, sumTotal);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ArticleOperational(action = "置顶")
    public void articleTop(ArticleTopDto articleTopDto) {
        Long articleId = articleTopDto.getArticleId();
        ArticleContentModel articleContentModel = articleContentModelRepository.findByIdEquals(articleId);
        if (articleContentModel != null) {
            articleContentModel.setTopStatus(ArticleConstants.TOP_STATUS_YES);
            articleContentModel.setTopTime(TimeUtils.getCurrentTime());
            String topDuration = articleTopDto.getTopDuration();
            int hour = 0;
            try {
                hour = Integer.parseInt(topDuration);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (hour == 0) {
                articleContentModel.setExpiredTime(TimeUtils.getTimestamp());
            } else {
                String expiredTime = TimeUtils.addDateMinut(TimeUtils.getLongDateStr(), hour);
                articleContentModel.setExpiredTime(TimeUtils.getTimestamp(expiredTime));
            }
            articleContentModel.setTopPosition(articleTopDto.getTopPosition());
            articleContentModelRepository.saveAndFlush(articleContentModel);
        }

        List<ArticleTopModel> topModel = new ArrayList<ArticleTopModel>();
        List<Long> sectionList = articleTopDto.getSection();
        for (Long sectionId : sectionList) {
            ArticleTopModel articleTopModel = new ArticleTopModel();
            articleTopModel.setArticleId(articleId);
            articleTopModel.setSectionId(sectionId);
            articleTopModel.setTopDuration(articleTopDto.getTopDuration());
            articleTopModel.setTopPosition(articleTopDto.getTopPosition());
            articleTopModel.setCreateTime(new Date());
            topModel.add(articleTopModel);
        }
        if (!CollectionUtils.isEmpty(topModel)) {
            articleTopModelRepository.saveAll(topModel);
        }
        // 缓存文章 版块关系可能修改，更新版块文章缓存
        articleCacheService.clearArticleCache(articleId);
        // 版块关系修改，更新版块文章缓存
        articleCacheService.clearEffectiveSectionCacheByArticle(articleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ArticleOperational(action = "取消置顶")
    public void cancelArticleTop(Long articleId) {
        ArticleContentModel articleContentModel = articleContentModelRepository.findByIdEquals(articleId);
        if (articleContentModel != null) {
            articleContentModel.setTopStatus(ArticleConstants.TOP_STATUS_NO);
            articleContentModel.setTopPosition(null);
            articleContentModel.setExpiredTime(null);
            articleContentModelRepository.saveAndFlush(articleContentModel);
        }
        articleTopModelRepository.deleteByArticleIdEquals(articleId);
        // 缓存文章
        articleCacheService.clearArticleCache(articleId);
        articleCacheService.clearEffectiveSectionCacheByArticle(articleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ArticleOperational(action = "删除文章")
    public void delete(List<Long> articleIds) {
        if (!CollectionUtils.isEmpty(articleIds)) {
            articleContentModelRepository.deleteArticle(articleIds);
            // 删除文章
            articleCacheService.clearArticleCache(articleIds);
            // 版块关系修改，更新版块文章缓存
            articleCacheService.clearEffectiveSectionCacheByArticle(articleIds);
            // 过期浏览数缓存
            articleCacheService.expireBrowseCache(articleIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ArticleOperational(action = "文章下线")
    public void offline(List<Long> articleIds) {
        // 删除缓存文章
        articleCacheService.clearArticleCache(articleIds);
        // 版块关系修改，更新版块文章缓存
        articleCacheService.clearEffectiveSectionCacheByArticle(articleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ArticleOperational(action = "文章发布")
    public void publish(List<Long> articleIds) {
        articleContentModelRepository.publist(ArticleConstants.AUDIT_STATUS_WAIT, ArticleConstants.CONTENT_STATUS_AUDIT_WAIT, articleIds, new Date());
        // 缓存文章
        articleCacheService.clearArticleCache(articleIds);
        // 版块关系修改，更新版块文章缓存
        articleCacheService.clearEffectiveSectionCacheByArticle(articleIds);
    }

    @Override
    public ListWithPage<AppArticleListVo> appArticleList(AppArticleListParams params) {
        ListWithPage<AppArticleListVo> resultList = articleCacheService.getAppArticleListCache(params);
        if (resultList == null) {
            List<Long> cacheList = articleCacheService.getEffectiveSection(params.getSectionId());
            log.info("cacheList:{},{}", params.getSectionId(), cacheList);
            if (cacheList == null || cacheList.isEmpty()) {
                cacheList = articleCacheService.cacheEffectiveSection(params.getSectionId());
            }
            if (cacheList.isEmpty() || (cacheList.size() == 1 && cacheList.contains(0L))) {
                // 该版本下没有生效的文章
                log.info("该版块下没有生效列表");
                return new ListWithPage<>();
            }
            List<Object[]> result = queryAppArticleList(params, cacheList);
            List<Date> expireList = new ArrayList<>();
            List<AppArticleListVo> list = convertToAppArticleListVo(result, expireList);
            resultList = PageUtils.formatData(list, PageRequest.of(params.getPage(), params.getSize()), Long.parseLong(String.valueOf(cacheList.size())));
            getAppListTtlAndCache(params, resultList, expireList);
        }
        return resultList;
    }

    /**
     * 缓存过期策略：没有置顶的文章则默认60分钟，有则取最小置顶过期时间-当前时间（单位秒）
     *
     * @param params
     * @param resultList
     * @param expireList
     */
    private void getAppListTtlAndCache(AppArticleListParams params, ListWithPage<AppArticleListVo> resultList, List<Date> expireList) {
        if (expireList.isEmpty()) {
            articleCacheService.cacheAppArticleList(params, resultList, 3600L);
            return;
        }
        Date min = expireList.stream().min(TimeUtils::compare).orElse(null);
        if (min != null) {
            long expire = (min.getTime() - (new Date()).getTime()) / 1000;
            if (expire > 5) {
                articleCacheService.cacheAppArticleList(params, resultList, expire);
            }
        }
    }

    /**
     * 根据审核状态，文章删除状态，查询文章列表，置顶的文章在前面，
     * 置顶已过期的文章将置顶为赋值为int最大值加100再排序
     *
     * @param articleIds
     * @param articleIds
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Object[]> queryAppArticleList(AppArticleListParams params, List<Long> articleIds) {
        StringBuilder qrySql = new StringBuilder();
        qrySql.append("select a.id, a.advertisement, a.article_cover, a.title, a.browse_num, a.browse_base, a.create_time, a.content_html, a.top_expired_time,")
                .append("if(a.top_expired_time> ?,a.top_position, ?) topP ")
                .append("from article_content_configure a where ");
        if (articleIds != null && !articleIds.isEmpty()) {
            qrySql.append(" a.id in ( ");
            int size = articleIds.size();
            for (int i = 0; i < size; i++) {
                if (i < size - 1) {
                    qrySql.append(articleIds.get(i)).append(",");
                } else {
                    qrySql.append(articleIds.get(i)).append(" ) ");
                }
            }
        }
        int size = params.getSize() > 0 ? params.getSize() : 10;
        int page = params.getPage() > 0 ? params.getPage() : 1;
        qrySql.append(" order by topP asc, create_time desc limit ").append((page - 1) * size).append(" , ").append(size);
        String sql = qrySql.toString();
        log.info("sql string:{}", sql);
        Query qry = entityManager.createNativeQuery(sql);
        qry.setParameter(1, new Date(), TemporalType.DATE);
        qry.setParameter(2, 2147483747L);
        return qry.getResultList();
    }

    private List<AppArticleListVo> convertToAppArticleListVo(List<Object[]> result, List<Date> expireList) {
        List<AppArticleListVo> list = new ArrayList<>();
        Date now = new Date();
        for (Object[] objs : result) {
            AppArticleListVo vo = new AppArticleListVo();
            vo.setArticleId(Long.parseLong(String.valueOf(objs[0])));
            vo.setAdvertisement(objs[1] != null ? (String) objs[1] : null);
            vo.setArticleCover(objs[2] != null ? (String) objs[2] : null);
            vo.setTitle((String) objs[3]);
            int num = articleCacheService.getBrowseNum(vo.getArticleId());
            int baseNum = objs[5] == null ? 0 : Integer.parseInt(String.valueOf(objs[5]));
            int browseNum = objs[4] == null ? 0 : Integer.parseInt(String.valueOf(objs[4]));
            vo.setBrowseNum(num + baseNum + browseNum);
            Date date = TimeUtils.addDay(now, -7);
            vo.setLatest(((Date) objs[6]).getTime() > date.getTime());
            if (objs[7] != null) {
                Document doc = Jsoup.parse(objs[7].toString());
                vo.setVideo(!doc.body().getElementsByTag("video").isEmpty());
            }
            list.add(vo);
            // 过期时间比当前时间大
            if (objs[8] != null && TimeUtils.compare(now, (Date) objs[8]) < 0) {
                expireList.add((Date) objs[8]);
            }
        }
        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppArticleDetailsVo appArticleDetail(Long articleId, Long ssoid) {
        AppArticleCacheDetailsVo model = articleCacheService.getArticle(articleId);
        AppArticleDetailsVo vo = null;
        // 不是空对象则增加浏览次数
        if (model != null) {
            vo = new AppArticleDetailsVo();
            vo.setArticleId(model.getArticleId());
            int num = articleCacheService.getBrowseNum(model.getArticleId());
            vo.setBrowseNum(model.getBrowseNum() + num);
            vo.setContentTangramJson(model.getContentTangramJson());
            vo.setTitle(model.getTitle());
            vo.setPublishTime(model.getPublishTime());
            if (ssoid != null) {
                long count = articleLikeRecordRepository.countByArticleIdAndSsoidAndLikeStatus(articleId, ssoid, ArticleConstants.STATUS_LIKE);
                vo.setUserLike(count > 0);
            }
            // 浏览次数增加 定时任务刷回db
            articleCacheService.increaseBrowseNum(articleId);
        }
        return vo;
    }

    @Override
    public List<AppArticleListVo> appArticleRand(AppArticleRandParams appArticleRandParams) {
        List<Long> cacheList = articleCacheService.getEffectiveSection(appArticleRandParams.getSectionId());
        log.info("cacheList:{},{}", appArticleRandParams.getSectionId(), cacheList);
        if (cacheList == null || cacheList.isEmpty()) {
            cacheList = articleCacheService.cacheEffectiveSection(appArticleRandParams.getSectionId());
        }
        if (cacheList.isEmpty() || (cacheList.size() == 1 && cacheList.contains(0L))) {
            // 该版本下没有生效的文章
            log.info("该版块下没有生效列表");
            return new ArrayList<>();
        }
        // 文章数大于4需要随机抽取
        int size = cacheList.size();
        Set<Long> idSet = new HashSet<>(4);
        if (size > 4) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            while (idSet.size() < 4) {
                int index = random.nextInt(size);
                idSet.add(cacheList.get(index));
            }
        } else {
            idSet = new HashSet<>(cacheList);
        }
        List<AppArticleListVo> resultList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (Long id : idSet) {
            AppArticleCacheDetailsVo model = articleCacheService.getArticle(id);
            if (model != null) {
                int num = articleCacheService.getBrowseNum(model.getArticleId());
                AppArticleListVo vo = new AppArticleListVo();
                BeanUtils.copyProperties(model, vo);
                vo.setBrowseNum(model.getBrowseNum() + num);
                resultList.add(vo);
            }
        }
        log.info("redis cost:{}", (System.currentTimeMillis() - start));
        return resultList;
    }

    private Predicate createConfigurationRecordQueryCondition(Root<ArticleContentModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, ArticleContentParams paramBody, List<Long> ids) {
        List<Predicate> list = new ArrayList<>();
        list.add(cb.equal(root.get("useStatus").as(Integer.class), ArticleConstants.DELETE_FLAG_EFFECTIVE));

        if (!StringUtils.isEmpty(paramBody.getTitle())) {
            list.add(cb.like(root.get("title").as(String.class), "%" + paramBody.getTitle() + "%"));
        }
        if (!StringUtils.isEmpty(paramBody.getTopStatus())) {
            list.add(cb.equal(root.get("topStatus").as(String.class), paramBody.getTopStatus()));
        }
        if (!StringUtils.isEmpty(paramBody.getStatus())) {
            list.add(cb.equal(root.get("status").as(String.class), paramBody.getStatus()));
        }
        // 列表过滤
        if (!CollectionUtils.isEmpty(ids)) {
            Path<Object> path = root.get("id");
            CriteriaBuilder.In<Object> in = cb.in(path);
            for (Long id : ids) {
                in.value(id);
            }
            list.add(cb.and(in));
        }
        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        return query.getRestriction();
    }

    private List<ArticleContentVo> configurationRecordMemoryValueToViewObject(List<ArticleContentModel> records) {
        List<ArticleContentVo> list = new ArrayList<>();
        for (ArticleContentModel entity : records) {
            ArticleContentVo viewObject = new ArticleContentVo();
            viewObject.setArticleId(entity.getId());
            viewObject.setTitle(entity.getTitle());
            viewObject.setContentHtml(entity.getContentHtml());
            viewObject.setArticleCover(entity.getArticleCover());
            viewObject.setArticleSummary(entity.getArticleSummary());
            viewObject.setAdvertisement(entity.getAdvertisement());
            viewObject.setStatus(entity.getStatus());
            viewObject.setTopStatus(entity.getTopStatus());
            viewObject.setBrowseBase(entity.getBrowseBase());
            viewObject.setBrowseNum(entity.getBrowseNum());
            // 设置浏览数
            int num = articleCacheService.getBrowseNum(entity.getId());
            viewObject.setLikeNum(num + StringsUtil.parseInt(entity.getBrowseBase()));
            // 设置点赞数
            int likeCount = articleLikeRecordRepository.countByArticleIdAndLikeStatus(entity.getId(), ArticleConstants.STATUS_LIKE);
            viewObject.setLikeNum(likeCount);
            list.add(viewObject);
        }
        return list;
    }

    private List<Long> filter(ArticleContentParams articleContentParams) {
        if (articleContentParams.getSectionId() == null) {
            return new ArrayList<>();
        }
        List<Long> ids = null;
        List<ArticleSectionRelationModel> list = articleSectionRelationModelRepository.findBySectionId(articleContentParams.getSectionId());
        if (!CollectionUtils.isEmpty(list)) {
            ids = new ArrayList<>();
            for (ArticleSectionRelationModel articleSectionRelationModel : list) {
                ids.add(articleSectionRelationModel.getArticleId());
            }
        }
        return ids;
    }

    @Override
    public void addBrowseNum() {
        log.info("任务开始");
        Set<String> keySet = articleCacheService.keys(ArticleConstants.REDIS_NAMESPACE_ARTICLE_BROWSE + "*");
        for (String key : keySet) {
            String[] nps = key.split(":");
            if (nps.length == 0) {
                continue;
            }
            try {
                long id = Long.parseLong(nps[nps.length - 1]);
                int num = articleCacheService.getBrowseNum(id);
                if (num > 0) {
                    int count = articleCacheService.decreaseBrowseNum(key, id, num);
                    log.debug("浏览数{}，{}，成功更新记录数{}", key, num, count);
                }
            } catch (Exception e) {
                log.error("redis文章浏览数更新，id解析异常", e);
            }
        }
        log.info("任务结束");
    }

    @Override
    @Transactional
    public void updateBrowse(Integer updateBrowse,Long articleId) {
        articleContentModelRepository.updateBrowse(updateBrowse,articleId);
        // 缓存文章
        articleCacheService.clearArticleCache(articleId);
    }
}
