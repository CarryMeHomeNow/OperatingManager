package com.tcl.uf.content.service.impl;

import com.tcl.commondb.content.model.ArticleSectionModel;
import com.tcl.commondb.content.repository.ArticleSectionModelRepository;
import com.tcl.commondb.content.repository.ArticleSectionRelationModelRepository;
import com.tcl.uf.common.base.constant.CommonConstant;
import com.tcl.uf.content.consts.ArticleConstants;
import com.tcl.uf.content.dto.ArticleSectionDto;
import com.tcl.uf.content.service.ArticleCacheService;
import com.tcl.uf.content.service.ArticleSectionService;
import com.tcl.uf.content.utils.RedisUtils;
import com.tcl.uf.content.vo.ArticleSectionPositionSortVo;
import com.tcl.uf.content.vo.ArticleSectionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("articleCategoryService")
public class ArticleSectionServiceImpl implements ArticleSectionService {

    @Autowired
    private ArticleSectionModelRepository articleSectionModelRepository;

    @Autowired
    private ArticleSectionRelationModelRepository sectionRelationModelRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ArticleCacheService articleCacheService;


    @Override
    public List<ArticleSectionVo> findBackSectionList() {
        List<ArticleSectionModel> sectionList = articleSectionModelRepository.findByUseStatusOrderByPosition(ArticleConstants.DELETE_FLAG_EFFECTIVE);
        return convertSectionListVo(sectionList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ArticleSectionVo> findSectionList() {
        Object value = redisUtils.get(ArticleConstants.REDIS_NAMESPACE_SECTION_LIST);
        List<ArticleSectionVo> sectionListVo;
        List<ArticleSectionModel> list;
        if (value != null) {
            list = (List<ArticleSectionModel>) value;
        } else {
            list = queryAndCacheSectionList();
        }
        sectionListVo = convertSectionListVo(list);
        return sectionListVo;
    }

    private List<ArticleSectionModel> queryAndCacheSectionList() {
        List<ArticleSectionModel> sectionList = articleSectionModelRepository.findByUseStatusOrderByPosition(ArticleConstants.DELETE_FLAG_EFFECTIVE);
        // 版块数据量很小，不设置失效时间，通过编辑与删除维护缓存数据
        redisUtils.set(ArticleConstants.REDIS_NAMESPACE_SECTION_LIST, sectionList);
        return sectionList;
    }

    private List<ArticleSectionVo> convertSectionListVo(List<ArticleSectionModel> sectionList) {
        List<ArticleSectionVo> sectionListVo = new ArrayList<>();
        for (ArticleSectionModel articleSectionModel : sectionList) {
            ArticleSectionVo articleSectionVo = new ArticleSectionVo();
            articleSectionVo.setSectionId(articleSectionModel.getId());
            articleSectionVo.setSectionName(articleSectionModel.getSectionName());
            articleSectionVo.setSubTitle(articleSectionModel.getSubTitle());
            sectionListVo.add(articleSectionVo);
        }
        return sectionListVo;
    }

    private void clearSectionListCache() {
        redisUtils.del(ArticleConstants.REDIS_NAMESPACE_SECTION_LIST);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleSectionVo saveOrUpdate(ArticleSectionDto articleSectionDto) {
        ArticleSectionModel articleSectionModel;
        if(articleSectionDto.getSectionId() == null){
            articleSectionModel = new ArticleSectionModel();
        }else{
            articleSectionModel = articleSectionModelRepository.findByIdEquals(articleSectionDto.getSectionId());
        }
        articleSectionModel.setSectionName(articleSectionDto.getSectionName());
        articleSectionModel.setSubTitle(articleSectionDto.getSubTitle());
        articleSectionModel.setPosition(articleSectionDto.getPosition());
        articleSectionModel.setCreateTime(new Date());
        articleSectionModel.setUseStatus(CommonConstant.DELETE_FLAG_EFFECTIVE);
        articleSectionModelRepository.saveAndFlush(articleSectionModel);

        ArticleSectionVo articleSectionVo=new ArticleSectionVo();
        articleSectionVo.setSectionId(articleSectionModel.getId());
        BeanUtils.copyProperties(articleSectionModel,articleSectionVo);
        // 清除版块列表缓存
        clearSectionListCache();
        return articleSectionVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ArticleSectionModel articleSectionModel= articleSectionModelRepository.findByIdEquals(id);
        if(articleSectionModel != null){
            articleSectionModel.setUseStatus(ArticleConstants.DELETE_FLAG_INVALID);
            articleSectionModelRepository.saveAndFlush(articleSectionModel);
            // 清除关联
            sectionRelationModelRepository.deleteRelationBySectionId(id);
            // 清除版块列表缓存
            clearSectionListCache();
            // 清除生效版块关联缓存
            articleCacheService.clearEffectiveSectionCache(id);
        }
    }

    @Override
    public void positionSort(List<ArticleSectionPositionSortVo> position) {
        List<ArticleSectionModel> articleSectionList= articleSectionModelRepository.findByUseStatusOrderByPosition(ArticleConstants.DELETE_FLAG_EFFECTIVE);
        for(ArticleSectionModel articleSectionModel :articleSectionList){
            Long s1 = articleSectionModel.getId();
            Integer positionValue = null;
            for(ArticleSectionPositionSortVo articleSectionPositionSortVo :position){
                Long s2= articleSectionPositionSortVo.getSectionId();
                if(s1.longValue() == s2.longValue()){
                    positionValue = articleSectionPositionSortVo.getPosition();
                }
            }
            if(positionValue == null){
                continue;
            }
            articleSectionModel.setPosition(positionValue);
            articleSectionModelRepository.saveAndFlush(articleSectionModel);
        }
        // 清除版块列表缓存
        clearSectionListCache();
    }
}
