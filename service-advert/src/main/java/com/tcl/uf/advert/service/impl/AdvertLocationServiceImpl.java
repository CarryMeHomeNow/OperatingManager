package com.tcl.uf.advert.service.impl;

import com.tcl.commondb.advert.model.AdvertLocationGroupModel;
import com.tcl.commondb.advert.model.AdvertLocationModel;
import com.tcl.commondb.advert.repository.AdvertApproveLogModelRepository;
import com.tcl.commondb.advert.repository.AdvertLocationGroupModelRepository;
import com.tcl.commondb.advert.repository.AdvertLocationModelRepository;
import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.dto.LocationDto;
import com.tcl.uf.advert.dto.LocationListParams;
import com.tcl.uf.advert.dto.LocationStatusParams;
import com.tcl.uf.advert.service.AdvertDepartmentService;
import com.tcl.uf.advert.service.AdvertLocationGroupService;
import com.tcl.uf.advert.service.AdvertLocationService;
import com.tcl.uf.advert.service.AdvertMailService;
import com.tcl.uf.advert.vo.LocationDetailVo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertLocationService
 * @Description:广告管理平台广告位管理
 * @date 2020/8/17 19:08
 */
@Service("AdvertLocationService")
public class AdvertLocationServiceImpl implements AdvertLocationService {

    private static final Logger _log = LoggerFactory.getLogger(AdvertLocationServiceImpl.class);

    @Autowired
    AdvertLocationGroupModelRepository avertLocationGroupModelRepository;

    @Autowired
    AdvertLocationModelRepository advertLocationModelRepository;

    @Autowired
    AdvertApproveLogModelRepository advertApproveLogModelRepository;

    @Autowired
    AdvertLocationGroupService advertLocationGroupService;

    @Autowired
    AdvertMailService advertMailService;

    @Autowired
    AdvertDepartmentService advertDepartmentService;

    @Override
    public Long saveOrUpdate(LocationDto locationDto, String username) {

        AdvertLocationModel advertLocationModel ;

        //保存广告位置信息
        if (locationDto.getId() == null) {

            AdvertLocationGroupModel advertLocationGroupModel = avertLocationGroupModelRepository.findByIdEquals(locationDto.getGroupId());

            if(advertLocationGroupModel == null ){
                throw new JMException("该广告组不存在");
            }

            if(locationDto.getFrameId() > advertLocationGroupModel.getFrameNum() ){
                throw new JMException("广告位帧数超过限制");
            }

            advertLocationModel = advertLocationModelRepository.findByIsDeletedAndGidAndFrameId(AdvertConstants.DELETE_FLAG_FALSE, locationDto.getGroupId(), locationDto.getFrameId());
            if(advertLocationModel != null ){
                _log.info("新增广告位已存在：广告组{},帧ID{},广告位ID{}", locationDto.getGroupId(), locationDto.getFrameId(), locationDto.getId());
                throw new JMException("该广告位已存在");
            }

            advertLocationModel = new AdvertLocationModel();
            advertLocationModel.setGid(locationDto.getGroupId());
            advertLocationModel.setFrameId(locationDto.getFrameId());
            advertLocationModel.setTitle(locationDto.getTitle());
            advertLocationModel.setCreator(username);
            advertLocationModel.setAuditStatus(AdvertConstants.AUDIT_STATUS_PASS);
            advertLocationModel.setStatus(AdvertConstants.AD_LOC_STATUS_ONLINE);
            advertLocationModel.setCreateTime(TimeUtils.getTimestamp());
            advertLocationModel.setOnlineTime(TimeUtils.getTimestamp());
            advertLocationModel.setIsDeleted(AdvertConstants.DELETE_FLAG_FALSE);
        } else {
            advertLocationModel = advertLocationModelRepository.findByIsDeletedAndGidAndFrameId(AdvertConstants.DELETE_FLAG_FALSE, locationDto.getGroupId(), locationDto.getFrameId());
            if(advertLocationModel != null && locationDto.getId().equals(advertLocationModel.getId())){
                advertLocationModel.setEditor(username);
                advertLocationModel.setUpdateTime(TimeUtils.getTimestamp());
            }else{
                _log.info("修改广告位信息不一致：广告组{},帧ID{},广告位ID{}", locationDto.getGroupId(), locationDto.getFrameId(), locationDto.getId());
                throw new JMException("该广告位已存在");
            }
        }

        advertLocationModel.setDefaultPic(locationDto.getDefaultPic());
        advertLocationModel.setDefaultUrl(locationDto.getDefaultUrl());
        advertLocationModelRepository.saveAndFlush(advertLocationModel);

        return advertLocationModel.getId();
    }

    @Override
    public LocationDetailVo findDetail(Long locId) {
        LocationDetailVo locationDetailVo = new LocationDetailVo();
        AdvertLocationModel advertLocationModel = advertLocationModelRepository.findByIdEquals(locId);
        if (advertLocationModel != null) {
            AdvertLocationGroupModel advertLocationGroupModel = avertLocationGroupModelRepository.findByIdEquals(advertLocationModel.getGid());
            locationDetailVo.setId(advertLocationModel.getId());
            locationDetailVo.setTitle(advertLocationModel.getTitle());
            locationDetailVo.setAuditStatus(advertLocationModel.getAuditStatus());
            locationDetailVo.setCreator(advertLocationModel.getCreator());
            locationDetailVo.setDefaultPic(advertLocationModel.getDefaultPic());
            locationDetailVo.setDefaultUrl(advertLocationModel.getDefaultUrl());
            locationDetailVo.setFrameId(advertLocationModel.getFrameId());
            if(advertLocationModel.getOfflineTime() != null){
                locationDetailVo.setOfflineTime(TimeUtils.getLongDateStr(advertLocationModel.getOfflineTime()));
            }
            if(advertLocationModel.getOnlineTime() != null){
                locationDetailVo.setOnlineTime(TimeUtils.getLongDateStr(advertLocationModel.getOnlineTime()));
            }
            if(advertLocationModel.getCreateTime() != null){
                locationDetailVo.setCreateTime(TimeUtils.getLongDateStr(advertLocationModel.getCreateTime()));
            }
            if(advertLocationModel.getUpdateTime() != null){
                locationDetailVo.setUpdateTime(TimeUtils.getLongDateStr(advertLocationModel.getUpdateTime()));
            }
            locationDetailVo.setStatus(advertLocationModel.getStatus());
            locationDetailVo.setGroupId(advertLocationModel.getGid());
            locationDetailVo.setGroupName(advertLocationGroupModel.getTitle());
            locationDetailVo.setPicHeight(advertLocationGroupModel.getPicHeight());
            locationDetailVo.setPicWidth(advertLocationGroupModel.getPicWidth());
        }
        return locationDetailVo;
    }

    @Override
    public ListWithPage<LocationDetailVo> findList(Pageable pageable, LocationListParams locationListParams) {
        Long sumTotal = null;
        List<LocationDetailVo> records = null;

        //构造初始状态排序条件
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "gid"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "frameId"));
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
        Page<AdvertLocationModel> result = advertLocationModelRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> createConfigurationRecordQueryCondition(root, criteriaQuery, criteriaBuilder, locationListParams), page);

        List<LocationDetailVo> resultList = new ArrayList<>();
        Set<Long> groupIds = new HashSet<>();
        result.forEach(item -> {
            String offlineTime = null;
            String onlineTime = null;
            String createTime = null;
            String updateTime = null;
            if (item.getCreateTime() != null) {
                createTime = TimeUtils.getLongDateStr(item.getCreateTime());
            }
            if (item.getUpdateTime() != null) {
                updateTime = TimeUtils.getLongDateStr(item.getUpdateTime());
            }
            if (item.getOfflineTime() != null) {
                offlineTime = TimeUtils.getLongDateStr(item.getOfflineTime());
            }
            if (item.getOnlineTime() != null) {
                onlineTime = TimeUtils.getLongDateStr(item.getOnlineTime());
            }
            LocationDetailVo vo = new LocationDetailVo(item.getId(), item.getTitle(), item.getFrameId(), item.getDefaultPic(), item.getDefaultUrl(), item.getStatus(),
                    item.getAuditStatus(), item.getCreator(), createTime, updateTime, offlineTime, onlineTime, item.getEditor(), item.getGid());
            resultList.add(vo);
            groupIds.add(item.getGid());
        });

        Map<Long, AdvertLocationGroupModel> groupMap = advertLocationGroupService.getGroupMap(groupIds);
        resultList.forEach(e -> {
            if (groupMap.containsKey(e.getGroupId())) {
                e.setGroupName(groupMap.get(e.getGroupId()).getTitle());
                e.setPicWidth(groupMap.get(e.getGroupId()).getPicWidth());
                e.setPicHeight(groupMap.get(e.getGroupId()).getPicHeight());
                e.setPicSize(groupMap.get(e.getGroupId()).getPicSize());
                e.setFrameNum(groupMap.get(e.getGroupId()).getFrameNum());
            }
        });

        //数据库实体对象转VO对象
        if (!result.isEmpty()) {
            sumTotal = result.getTotalElements();
            records = resultList;
        } else {
            sumTotal = Long.getLong("0");
            records = new ArrayList<>();
        }
        return PageUtils.formatData(records, pageable, sumTotal);
    }

    @Override
    public void updateStatus(LocationStatusParams locationStatusParams, String username) {
        //校验用户状态
        AdvertLocationModel advertLocationModel = advertLocationModelRepository.findByIsDeletedAndId(AdvertConstants.DELETE_FLAG_FALSE, locationStatusParams.getLocId());
        if(advertLocationModel == null){
            throw new JMException("广告位不存在");
        }
        if(advertLocationModel.getAuditStatus() != AdvertConstants.AUDIT_STATUS_PASS){
            throw new JMException("广告位未审核");
        }

        if (locationStatusParams.getStatus() != AdvertConstants.AD_LOC_STATUS_ONLINE && locationStatusParams.getStatus() != AdvertConstants.AD_LOC_STATUS_OFFLINE){
            throw new JMException("非法状态参数");
        }

        //更新广告位状态
        advertLocationModel.setStatus(locationStatusParams.getStatus());
        advertLocationModel.setEditor(username);
        advertLocationModel.setUpdateTime(TimeUtils.getTimestamp());
        advertLocationModelRepository.saveAndFlush(advertLocationModel);
    }

    @Override
    public Map<Long, AdvertLocationModel> getLocationMap(Set<Long> locIds) {
        List<AdvertLocationModel> locationList = advertLocationModelRepository.findAllByIdIn(locIds);
        return locationList.stream().collect(Collectors.toMap(AdvertLocationModel::getId, advertLocationModel -> advertLocationModel));
    }

    private Predicate createConfigurationRecordQueryCondition(Root<AdvertLocationModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, LocationListParams paramBody) {
        List<Predicate> list = new ArrayList<>();
        list.add(cb.equal(root.get("isDeleted").as(Integer.class), AdvertConstants.DELETE_FLAG_FALSE));

        list.add(cb.equal(root.get("auditStatus").as(Integer.class), AdvertConstants.AUDIT_STATUS_PASS));

        if (!StringUtils.isEmpty(paramBody.getStatus())) {
            list.add(cb.equal(root.get("status").as(String.class), paramBody.getStatus()));
        }

        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        return query.getRestriction();
    }
}
