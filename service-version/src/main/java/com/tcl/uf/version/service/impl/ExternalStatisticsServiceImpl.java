package com.tcl.uf.version.service.impl;

import com.tcl.commondb.version.model.ExternalVersionStatisticsModel;
import com.tcl.commondb.version.model.PublishPlatformModel;
import com.tcl.commondb.version.repository.ExternalVersionStatisticsRepository;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.version.dto.ExternalStatisticsParams;
import com.tcl.uf.version.service.ExternalStatisticsService;
import com.tcl.uf.version.service.ExternalVersionService;
import com.tcl.uf.version.vo.ExternalStatisticsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("externalStatisticsService")
public class ExternalStatisticsServiceImpl implements ExternalStatisticsService {

    @Autowired
    private ExternalVersionStatisticsRepository statisticsRepository;

    @Autowired
    private ExternalVersionService externalVersionService;

    @Override
    public ListWithPage<ExternalStatisticsVo> statList(Pageable pageable, ExternalStatisticsParams params) {
        Map<String, PublishPlatformModel> platformMap = externalVersionService.queryPlatformMap();
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "statisticsTime"));
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));

        Page<ExternalVersionStatisticsModel> result = statisticsRepository.findAll((root, query, criteriaBuilder)
                -> listCondition(root, query, criteriaBuilder, params), page);
        long total = result.getTotalElements();
        List<ExternalStatisticsVo> records = new ArrayList<>();
        for (ExternalVersionStatisticsModel model : result) {
            ExternalStatisticsVo vo = new ExternalStatisticsVo();
            BeanUtils.copyProperties(model, vo);
            PublishPlatformModel platformModel = platformMap.get(model.getPlatformId());
            vo.setPlatformName(platformModel == null ? "" : platformModel.getPlatformName());
            records.add(vo);
        }
        return PageUtils.formatData(records, pageable, total);
    }

    private Predicate listCondition(Root<ExternalVersionStatisticsModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, ExternalStatisticsParams params) {
        List<Predicate> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(params.getAppId())) {
            list.add(cb.equal(root.get("appId").as(String.class), params.getAppId()));
        }
        if (StringUtils.isNotEmpty(params.getAppName())) {
            list.add(cb.like(root.get("appName").as(String.class), "%" + params.getAppName() + "%"));
        }

        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        return query.getRestriction();
    }
}
