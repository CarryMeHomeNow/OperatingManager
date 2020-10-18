package com.tcl.uf.content.service.impl;

import com.tcl.commondb.content.model.ArticleContentModel;
import com.tcl.commondb.content.repository.ArticleContentModelRepository;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.content.annotation.ArticleOperational;
import com.tcl.uf.content.consts.ArticleConstants;
import com.tcl.uf.content.dto.ArticleAuditParams;
import com.tcl.uf.content.dto.ArticleRejectDto;
import com.tcl.uf.content.service.ArticleAuditService;
import com.tcl.uf.content.service.ArticleCacheService;
import com.tcl.uf.content.vo.ArticleAuditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service("articleAuditService")
public class ArticleAuditServiceImpl implements ArticleAuditService {

    @Autowired
    private ArticleContentModelRepository articleContentModelRepository;

    @Autowired
    private ArticleCacheService articleCacheService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ArticleOperational(action = "文章审核通过")
    public void auditPass(List<Long> articleId) {
        articleContentModelRepository.auditContent(ArticleConstants.AUDIT_STATUS_PASS,ArticleConstants.CONTENT_STATUS_PUBLISH,articleId);
        // 文章状态修改 缓存版块文章关系
        articleCacheService.clearEffectiveSectionCacheByArticle(articleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ArticleOperational(action = "文章审核驳回")
    public void auditReject(ArticleRejectDto articleRejectDto) {
        articleContentModelRepository.auditContentReject(ArticleConstants.AUDIT_STATUS_REJECT,ArticleConstants.CONTENT_STATUS_AUDIT_REJECT,articleRejectDto.getArticleId(),articleRejectDto.getRemarks());
        // 文章状态修改 缓存版块文章关系
        articleCacheService.clearEffectiveSectionCacheByArticle(articleRejectDto.getArticleId());
    }

    @Override
    public ListWithPage<ArticleAuditVo> findList(Pageable pageable, ArticleAuditParams articleAuditParams) {
        Long sumTotal = null;
        List<ArticleAuditVo> records = null;
        //构造初始状态排序条件
        List<Sort.Order> orders=new ArrayList< Sort.Order>();
        orders.add( new Sort.Order(Sort.Direction.DESC, "createTime"));

        Pageable page = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), Sort.by(orders));
        Page<ArticleContentModel> result = articleContentModelRepository.findAll(new Specification<ArticleContentModel>() {
            @Override
            public Predicate toPredicate(Root<ArticleContentModel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return createConfigurationRecordQueryCondition(root,criteriaQuery,criteriaBuilder,articleAuditParams);
            }
        },page);
        //数据库实体对象转VO对象
        if (result != null){
            sumTotal = result.getTotalElements();
            records = configurationRecordMemoryValueToViewObject(result.getContent());
        }else {
            sumTotal = Long.getLong("0") ;
            records = new ArrayList<ArticleAuditVo>();
        }
        return PageUtils.formatData(records, pageable,sumTotal);
    }

    private Predicate createConfigurationRecordQueryCondition(Root<ArticleContentModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, ArticleAuditParams paramBody){
        List<Predicate> list = new ArrayList<Predicate>();
        list.add(cb.equal(root.get("useStatus").as(Integer.class), ArticleConstants.DELETE_FLAG_EFFECTIVE));
        if(!StringUtils.isEmpty(paramBody.getTitle())){
            list.add(cb.like(root.get("title").as(String.class), "%" + paramBody.getTitle() + "%"));
        }
        //文章审核
        Path<Object> path = root.get("auditStatus");
        CriteriaBuilder.In<Object> in = cb.in(path);
        if(!StringUtils.isEmpty(paramBody.getAuditStatus())){
            in.value(paramBody.getAuditStatus());
        }else{
            in.value(ArticleConstants.AUDIT_STATUS_WAIT);
            in.value(ArticleConstants.AUDIT_STATUS_PASS);
            in.value(ArticleConstants.AUDIT_STATUS_REJECT);
        }
        list.add(cb.and(in));
        //执行查询
        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        return query.getRestriction();
    }

    private List<ArticleAuditVo> configurationRecordMemoryValueToViewObject(List<ArticleContentModel> records){
        //查询
        List<Long> articleIds=new ArrayList<Long>();
        for(ArticleContentModel articleContent:records){
            articleIds.add(articleContent.getId());
        }
        //此处考虑关联查询或原生SQL关联查询(关联版块信息)
        List<ArticleAuditVo> list = new ArrayList<ArticleAuditVo>();
        for (ArticleContentModel entity : records) {
            ArticleAuditVo viewObject = new ArticleAuditVo();
            viewObject.setArticleId(entity.getId());
            viewObject.setTitle(entity.getTitle());
            viewObject.setSectionNames("版块名称");
            viewObject.setAuditStatus(entity.getAuditStatus());
            if (entity.getUpdateTime() != null) {
                viewObject.setUpdateTime(TimeUtils.getTimeStr(entity.getUpdateTime()));
            }
            viewObject.setContentHtml(entity.getContentHtml());
            list.add(viewObject);
        }
        return list;
    }
}
