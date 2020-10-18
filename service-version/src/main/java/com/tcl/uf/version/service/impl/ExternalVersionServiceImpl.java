package com.tcl.uf.version.service.impl;

import com.tcl.commondb.version.model.ExternalVersionModel;
import com.tcl.commondb.version.model.InternalVersionModel;
import com.tcl.commondb.version.model.PublishPlatformModel;
import com.tcl.commondb.version.repository.ExternalVersionRepository;
import com.tcl.commondb.version.repository.InternalVersionRepository;
import com.tcl.commondb.version.repository.PublishPlatformRepository;
import com.tcl.uf.common.base.constant.CommonConstant;
import com.tcl.uf.common.base.constant.RedisConstant;
import com.tcl.uf.common.base.ex.ProcessControlException;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.version.consts.ExternalConstants;
import com.tcl.uf.version.dto.ExternalListParams;
import com.tcl.uf.version.dto.ExternalOnlineParams;
import com.tcl.uf.version.dto.ExternalUpdateParams;
import com.tcl.uf.version.service.ExternalVersionService;
import com.tcl.uf.version.utils.RedisUtils;
import com.tcl.uf.version.vo.ExternalDetailVo;
import com.tcl.uf.version.vo.ExternalOnlineVo;
import com.tcl.uf.version.vo.NewVersionVo;
import com.tcl.uf.version.vo.PlatformDetailVo;
import org.apache.commons.lang.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("externalVersionService")
public class ExternalVersionServiceImpl implements ExternalVersionService {

    @Autowired
    private ExternalVersionRepository externalVersionRepository;

    @Autowired
    private PublishPlatformRepository publishPlatformRepository;

    @Autowired
    private InternalVersionRepository internalVersionRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void saveOrUpdate(ExternalUpdateParams params) throws ProcessControlException {
        ExternalVersionModel model = new ExternalVersionModel();
        if (params.getId() == null) {
            // 新增
            model.setCreateTime(new Date());
            model.setCreator(params.getUserId());
        } else {
            Optional<ExternalVersionModel> optional = externalVersionRepository.findById(params.getId());
            if (!optional.isPresent()) {
                throw new ProcessControlException("渠道版本不存在");
            }
            model = optional.get();
            model.setUpdateTime(new Date());
            model.setEditor(params.getUserId());
        }
        model.setUseStatus(CommonConstant.DELETE_FLAG_EFFECTIVE);
        BeanUtils.copyProperties(params, model);
        ExternalVersionModel finalModel = model;
        long count = externalVersionRepository.count((root, query, criteriaBuilder) ->
                countPlatformVersionCondition(root, query, criteriaBuilder, finalModel));
        if (count > 0) {
            throw new ProcessControlException("已存在渠道为" + model.getPlatformId() + "的" + model.getAppVer() + "版本");
        }
        externalVersionRepository.saveAndFlush(model);
        redisUtils.del(RedisConstant.REDIS_VERSION_NEW + ":" + model.getPlatformId());
    }

    private Predicate countPlatformVersionCondition(Root<ExternalVersionModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, ExternalVersionModel model) {
        List<Predicate> list = new ArrayList<>();
        list.add(cb.equal(root.get("useStatus").as(Integer.class), CommonConstant.DELETE_FLAG_EFFECTIVE));
        list.add(cb.equal(root.get("platformId").as(String.class), model.getPlatformId()));
        list.add(cb.equal(root.get(ExternalConstants.FEILD_APP_VER).as(String.class), model.getAppVer()));
        if (model.getId() != null) {
            list.add(cb.notEqual(root.get("id").as(Long.class), model.getId()));
        }
        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        return query.getRestriction();
    }


    @Override
    public ListWithPage<ExternalDetailVo> externalList(Pageable pageable, ExternalListParams params) {
        Map<String, PublishPlatformModel> map = queryPlatformMap();
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
        orders.add(new Sort.Order(Sort.Direction.DESC, ExternalConstants.FEILD_APP_VER));
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));

        Page<ExternalVersionModel> result = externalVersionRepository.findAll((root, query, criteriaBuilder)
                -> listCondition(root, query, criteriaBuilder, params), page);

        List<ExternalDetailVo> records = new ArrayList<>();
        List<Long> internalVerIdList = new ArrayList<>();
        long total = result.getTotalElements();
        for (ExternalVersionModel model : result.getContent()) {
            ExternalDetailVo vo = new ExternalDetailVo();
            BeanUtils.copyProperties(model, vo);
            PublishPlatformModel platformModel = map.get(model.getPlatformId());
            vo.setPlatformName(platformModel == null ? "" : platformModel.getPlatformName());
            records.add(vo);
            internalVerIdList.add(model.getInternalVerId());
        }
        Map<Long, InternalVersionModel> internalMap = queryInternalMap(internalVerIdList);
        convertInternalVer(records, internalMap);
        return PageUtils.formatData(records, pageable, total);
    }

    @Override
    public Map<String, PublishPlatformModel> queryPlatformMap() {
        List<PublishPlatformModel> platformModels = publishPlatformRepository.findAll();
        Map<String, PublishPlatformModel> map = new HashMap<>();
        platformModels.forEach(item -> map.put(item.getPlatformId(), item));
        return map;
    }

    private Map<Long, InternalVersionModel> queryInternalMap(List<Long> internalVerIdList) {
        Map<Long, InternalVersionModel> map = new HashMap<>();
        if (internalVerIdList.isEmpty()) {
            return map;
        }
        List<InternalVersionModel> list = internalVersionRepository.findAllById(internalVerIdList);
        if (list.isEmpty()) {
            return map;
        }
        list.forEach(item -> map.put(item.getId(), item));
        return map;
    }

    private void convertInternalVer(List<ExternalDetailVo> records, Map<Long, InternalVersionModel> map) {
        for (ExternalDetailVo vo : records) {
            InternalVersionModel model = map.get(vo.getInternalVerId());
            vo.setInternalVer(model != null ? model.getInternalVersion() : "");
        }
    }

    private Predicate listCondition(Root<ExternalVersionModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, ExternalListParams params) {
        List<Predicate> list = new ArrayList<>();
        list.add(cb.equal(root.get("useStatus").as(Integer.class), CommonConstant.DELETE_FLAG_EFFECTIVE));
        if (params != null) {
            if (StringUtils.isNotEmpty(params.getAppId())) {
                list.add(cb.equal(root.get("appId").as(String.class), params.getAppId()));
            }
            if (StringUtils.isNotEmpty(params.getAppName())) {
                list.add(cb.like(root.get("appName").as(String.class), "%" + params.getAppName() + "%"));
            }
            if (StringUtils.isNotEmpty(params.getAppVer())) {
                list.add(cb.equal(root.get(ExternalConstants.FEILD_APP_VER).as(String.class), params.getAppVer()));
            }
            if (null != params.getInternalVerId()) {
                list.add(cb.equal(root.get("internalVerId").as(Integer.class), params.getInternalVerId()));
            }
        }
        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        return query.getRestriction();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            externalVersionRepository.deleteByIdIn(ids);
            List<ExternalVersionModel> list = externalVersionRepository.findAllById(ids);
            list.forEach(item -> redisUtils.del(RedisConstant.REDIS_VERSION_NEW + ":" + item));
        }
    }

    @Override
    public ExternalDetailVo externalDetail(Long externalId) {
        ExternalDetailVo vo = new ExternalDetailVo();
        Optional<ExternalVersionModel> model = externalVersionRepository.findById(externalId);
        if (!model.isPresent()) {
            return vo;
        }
        BeanUtils.copyProperties(model.get(), vo);
        queryPlatformNameAndInternalVer(model.get(), vo);
        return vo;
    }

    @Override
    public ListWithPage<ExternalOnlineVo> onlineList(Pageable pageable) {
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<Object[]> records = externalVersionRepository.onlineList(page);
        long total = records.getTotalElements();
        List<ExternalOnlineVo> list = new ArrayList<>();
        if (!records.isEmpty()) {
            for (Object[] objects : records) {
                ExternalOnlineVo vo = new ExternalOnlineVo();
                vo.setPlatformId(objects[0] != null ? objects[0].toString() : "");
                vo.setPlatformName(objects[1] != null ? objects[1].toString() : "");
                vo.setAppVer(objects[2] != null ? objects[2].toString() : "");
                list.add(vo);
            }
        }
        return PageUtils.formatData(list, pageable, total);
    }

    /**
     * 根据渠道id获取改渠道app最新版本信息
     *
     * @param platformId
     * @return
     */
    @Override
    public NewVersionVo findNewVersionByPlatformId(String platformId) {
        NewVersionVo newVersion = null;
        Object object = redisUtils.get(RedisConstant.REDIS_VERSION_NEW + ":" + platformId);
        if (object == null) {
            newVersion = getNewVersion(platformId);
            redisUtils.set(RedisConstant.REDIS_VERSION_NEW + ":" + platformId,newVersion);
        }else {
            newVersion = (NewVersionVo) object;
        }
        return newVersion;
    }

    /**
     * 根据渠道id获取最新版本
     * @param platformId
     * @return
     */
    private NewVersionVo getNewVersion(String platformId) {
        String sql = "select ev.platform_id platformId , pp.platform_name platformName , ev.app_name appName , " +
                "ev.app_ver appVer , concat('',ev.id) id , iv.internal_ver internalVer , ev.dl_url downloadUrl , " +
                "ev.score_url scoreUrl ,ev.detail_url detailUrl ,concat('',ev.create_time) createdTime " +
                "from app_external_version ev,app_publish_platform pp,app_internal_version iv " +
                "where ev.platform_id = pp.platform_id and ev.internal_ver_id = iv.id and ev.use_status = 1 and " +
                "ev.platform_id =  '" + platformId + "' and ev.app_ver = (select max(app_ver) from app_external_version where platform_id = '" + platformId + " ' and ev.use_status = 1 ) ";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(NewVersionVo.class));
        List<NewVersionVo> resultList = nativeQuery.getResultList();
        if (resultList.isEmpty()) {
            return new NewVersionVo();
        }
        return resultList.get(0);
    }

    @Override
    public ExternalDetailVo onlineDetail(ExternalOnlineParams params) {
        ExternalDetailVo vo = new ExternalDetailVo();
        List<ExternalVersionModel> records = externalVersionRepository.queryByPlatformIdAndAppVerAndUseStatus(
                params.getPlatformId(), params.getAppVer(), CommonConstant.DELETE_FLAG_EFFECTIVE);
        if (records.isEmpty()) {
            return vo;
        }
        BeanUtils.copyProperties(records.get(0), vo);
        queryPlatformNameAndInternalVer(records.get(0), vo);
        return vo;
    }

    @Override
    public List<PlatformDetailVo> platformList() {
        List<PublishPlatformModel> records = publishPlatformRepository.findByAndUseStatusOrderByPlatformIdAscCreateTimeDesc(CommonConstant.DELETE_FLAG_EFFECTIVE);
        List<PlatformDetailVo> list = new ArrayList<>();
        if (records.isEmpty()) {
            return list;
        }
        for (PublishPlatformModel model : records) {
            PlatformDetailVo vo = new PlatformDetailVo();
            vo.setCreateTime(model.getCreateTime());
            vo.setId(model.getId());
            vo.setPlatformId(model.getPlatformId());
            vo.setPlatformName(model.getPlatformName());
            vo.setUpdateTime(model.getUpdateTime());
            list.add(vo);
        }
        return list;
    }

    private void queryPlatformNameAndInternalVer(ExternalVersionModel model, ExternalDetailVo vo) {
        if (StringUtils.isNotEmpty(model.getPlatformId())) {
            List<PublishPlatformModel> platList = publishPlatformRepository.queryByPlatformId(model.getPlatformId());
            if (platList != null && !platList.isEmpty()) {
                vo.setPlatformName(platList.get(0).getPlatformName());
            }
        }
        if (model.getInternalVerId() != null) {
            InternalVersionModel internalModel = internalVersionRepository.findByIdEquals(model.getInternalVerId());
            if (internalModel != null) {
                vo.setInternalVer(internalModel.getInternalVersion());
            }
        }
    }
}
