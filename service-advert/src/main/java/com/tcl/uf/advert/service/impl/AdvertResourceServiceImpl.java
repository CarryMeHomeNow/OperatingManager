package com.tcl.uf.advert.service.impl;

import com.tcl.commondb.advert.model.*;
import com.tcl.commondb.advert.repository.*;
import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.dto.*;
import com.tcl.uf.advert.exception.JMailException;
import com.tcl.uf.advert.service.*;
import com.tcl.uf.advert.vo.ResourceAppListVo;
import com.tcl.uf.advert.vo.ResourceDetailVo;
import com.tcl.uf.common.base.constant.CommonErrorConstant;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertResourceServiceImpl
 * @Description:广告管理平台投放资源管理
 * @date 2020/8/20 15:32
 */
@Service("AdvertResourceService")
public class AdvertResourceServiceImpl implements AdvertResourceService {

    private static final Logger _log = LoggerFactory.getLogger(AdvertResourceServiceImpl.class);

    @Autowired
    AdvertUserModelRepository advertUserModelRepository;

    @Autowired
    AdvertDepartmentModelRepository advertDepartmentModelRepository;

    @Autowired
    AdvertApproveLogModelRepository advertApproveLogModelRepository;

    @Autowired
    AdvertLocationGroupModelRepository avertLocationGroupModelRepository;

    @Autowired
    AdvertLocationModelRepository advertLocationModelRepository;

    @Autowired
    AdvertResourceModelRepository advertResourceModelRepository;

    @Autowired
    AdvertMailService advertMailService;

    @Autowired
    AdvertDepartmentService advertDepartmentService;

    @Autowired
    AdvertCacheService advertCacheService;

    @Autowired
    AdvertScheduleModelRepository advertScheduleModelRepository;

    @Autowired
    AdvertUserRoleModelRepository advertUserRoleModelRepository;

    @Autowired
    AdvertLocationModelRepository avertLocationModelRepository;

    @Autowired
    AdvertLocationGroupModelRepository advertLocationGroupModelRepository;

    @Autowired
    AdvertLocationGroupService advertLocationGroupService;

    @Autowired
    AdvertLocationService advertLocationService;

    @Autowired
    AdvertUserService advertUserService;


    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = JMailException.class)
    public void resourceAudit(ResourceAuditParams resourceAuditParams, String username) {
        //校验资源状态
        AdvertResourceModel advertResourceModel = advertResourceModelRepository.findByIsDeletedAndId(AdvertConstants.DELETE_FLAG_FALSE, resourceAuditParams.getResourceId());

        if(advertResourceModel == null){
            throw new JMException("资源不存在");
        }
        if(advertResourceModel.getAuditStatus() != AdvertConstants.AUDIT_STATUS_DRAFT){
            throw new JMException("资源已审核");
        }

        String mailTitle = AdvertConstants.MAIL_TEMPLATE_RESOURCE_AUDIT_TITLE;
        String mailContent ;
        //更新审核状态
        if(resourceAuditParams.getAuditStatus() == AdvertConstants.AUDIT_STATUS_PASS){
            //更新相同时间段同一个广告位的其他资源为下线状态
            advertResourceModelRepository.batchUpdateStatus(advertResourceModel.getLocId(), advertResourceModel.getEffectiveDay(), AdvertConstants.AUDIT_STATUS_PASS, AdvertConstants.AD_RES_STATUS_OFFLINE);
            advertResourceModel.setAuditStatus(AdvertConstants.AUDIT_STATUS_PASS);
            advertResourceModel.setAuditTime(TimeUtils.getTimestamp());
            advertResourceModel.setStatus(AdvertConstants.AD_RES_STATUS_ONLINE);
            //生成数据缓存
            advertCacheService.setLocResourceCache(advertResourceModel.getLocId(), advertResourceModel.getEffectiveDay(), advertResourceModel);
            mailContent = AdvertConstants.MAIL_TEMPLATE_RESOURCE_AUDIT_PASS;

        }else if(resourceAuditParams.getAuditStatus() == AdvertConstants.AUDIT_STATUS_REJECT){
            advertResourceModel.setAuditStatus(AdvertConstants.AUDIT_STATUS_REJECT);
            advertResourceModel.setAuditTime(TimeUtils.getTimestamp());
            advertResourceModel.setStatus(AdvertConstants.AD_RES_STATUS_DRAFT);
            mailContent = AdvertConstants.MAIL_TEMPLATE_RESOURCE_AUDIT_REJECT;
        }else{
            throw new JMException("非法状态参数");
        }
        advertResourceModelRepository.saveAndFlush(advertResourceModel);

        mailContent = mailContent.replace("{title}", advertResourceModel.getAdTitle());
        mailContent = mailContent.replace("{effectiveDate}", TimeUtils.getLongDate(advertResourceModel.getEffectiveDate()));
        mailContent = mailContent.replace("{remark}", resourceAuditParams.getRemarks());

        //写入审核日志
        AdvertApproveLogModel advertApproveLogModel = new AdvertApproveLogModel();
        advertApproveLogModel.setApplyId(advertResourceModel.getId());
        advertApproveLogModel.setApplyType(AdvertConstants.AUDIT_TYPE_AD_RES);
        advertApproveLogModel.setApprover(username);
        advertApproveLogModel.setApproveResult(resourceAuditParams.getAuditStatus());
        advertApproveLogModel.setRemark(resourceAuditParams.getRemarks());
        advertApproveLogModel.setCreateTime(TimeUtils.getTimestamp());
        advertApproveLogModelRepository.saveAndFlush(advertApproveLogModel);

        //发送通知邮件
        AdvertUserModel advertUserModel = advertUserModelRepository.findByIdEquals(advertResourceModel.getUserId());
        try {
            advertMailService.sendSimpleMail(advertUserModel.getOaEmail(), mailTitle, mailContent);
        } catch (JMailException e) {
            _log.error("发送审批通知邮件失败email=={} message=={} ",advertUserModel.getOaEmail(),e.getMessage());
        }
    }

    @Override
    public ListWithPage<ResourceDetailVo> resourceAuditList(Pageable pageable, ResourceAuditListParams resourceAuditListParams) {

        if (resourceAuditListParams.getAuditStatus() == null) {
            throw new JMException("审核状态不能为空");
        }

        ResourceListParams resourceListParams = new ResourceListParams();

        if (StringUtils.isNotBlank(resourceAuditListParams.getSeqNo())) {
            String[] seqNo = resourceAuditListParams.getSeqNo().split("_");
            try {
                resourceListParams.setGroupId(Long.parseLong(seqNo[0]));
                resourceListParams.setFrameId(Integer.parseInt(seqNo[1]));
                resourceListParams.setEffectiveDay(Integer.parseInt(TimeUtils.formatDate(TimeUtils.parseDate(seqNo[2], "yyyy-MM-dd"), "yyyyMMdd")));
                resourceListParams.setSeqNo(seqNo[3]);
            } catch (Exception e) {
                return PageUtils.formatData(new ArrayList<>(), pageable, Long.parseLong("0"));
            }
        }
        resourceListParams.setAuditStatus(resourceAuditListParams.getAuditStatus());
        resourceListParams.setUsername(resourceAuditListParams.getUsername());
        resourceListParams.setStartTime(resourceAuditListParams.getStartTime());
        resourceListParams.setEndTime(resourceAuditListParams.getEndTime());

        return resourceList(pageable,resourceListParams);
    }

    @Override
    public ListWithPage<ResourceDetailVo> resourceStatusList(Pageable pageable, ResourceStatusListParams resourceStatusListParams, String username) {

        ResourceListParams resourceListParams = new ResourceListParams();

        if (resourceStatusListParams.getListType() == AdvertConstants.PAGE_LIST_TYPE_RESOURCE_ONLINE || resourceStatusListParams.getListType() == AdvertConstants.PAGE_LIST_TYPE_RESOURCE_OFFLINE) {
            resourceListParams.setListType(resourceStatusListParams.getListType());
        }else{
            throw new JMException("列表类型参数错误");
        }

        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole(AdvertConstants.ADVERT_ROLE_ADMIN)) {
            AdvertUserModel userModel = advertUserService.findUserByName(username);
            resourceListParams.setDepartmentId(userModel.getDepartmentId());
        }

        resourceListParams.setGroupId(resourceStatusListParams.getGroupId());
        resourceListParams.setFrameId(resourceStatusListParams.getFrameId());
        resourceListParams.setStartTime(resourceStatusListParams.getStartTime());
        resourceListParams.setEndTime(resourceStatusListParams.getEndTime());
        return resourceList(pageable, resourceListParams);
    }

    @Override
    public ListWithPage<ResourceDetailVo> resourceList(Pageable pageable, ResourceListParams resourceListParams) {

        List<ResourceDetailVo> resourceVoList = new ArrayList<>();

        Long sumTotal = null;
        List<ResourceDetailVo> records = null;

        //构造初始状态排序条件
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
        Page<AdvertResourceModel> result = advertResourceModelRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> createRecordQueryCondition(root, criteriaQuery, criteriaBuilder, resourceListParams), page);

        Set<Long> groupIds = new HashSet<>();
        Set<Long> locIds = new HashSet<>();
        Set<Long> departmentIds = new HashSet<>();

        result.forEach(item -> {
            ResourceDetailVo vo = transResourceModelToVo(item);
            resourceVoList.add(vo);
            groupIds.add(item.getGroupId());
            locIds.add(item.getLocId());
            departmentIds.add(item.getDepartmentId());
        });

        //数据库实体对象转VO对象
        if (!resourceVoList.isEmpty()) {
            List<ResourceDetailVo> resultList = fillResourceVoTitle(resourceVoList, groupIds, departmentIds, locIds);
            sumTotal = result.getTotalElements();
            records = resultList;
        } else {
            sumTotal = Long.getLong("0");
            records = new ArrayList<>();
        }
        return PageUtils.formatData(records, pageable, sumTotal);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(ResourceStatusParams resourceStatusParams, String username) {
        AdvertResourceModel resourceModel = advertResourceModelRepository.findByIsDeletedAndId(AdvertConstants.DELETE_FLAG_FALSE, resourceStatusParams.getResourceId());

        if (resourceModel == null) {
            throw new JMException("资源ID不存在");
        }

        if (resourceModel.getAuditStatus() != AdvertConstants.AUDIT_STATUS_PASS) {
            throw new JMException("未审核通过资源不可进行上下线操作");
        }

        Integer today = Integer.parseInt(TimeUtils.formatDate(new Date(), "yyyyMMdd"));

        if (resourceModel.getEffectiveDay() < today) {
            throw new JMException("已过期资源不可进行上下线操作");
        }

        Subject subject = SecurityUtils.getSubject();

        if (!subject.hasRole(AdvertConstants.ADVERT_ROLE_ADMIN)) {
            AdvertUserModel userModel = advertUserService.findUserByName(username);
            if (!userModel.getDepartmentId().equals(resourceModel.getDepartmentId())) {
                throw new JMException("非本业务方资源不可修改");
            }
        }

        if (resourceStatusParams.getStatus() == AdvertConstants.AD_RES_STATUS_ONLINE) {
            if(resourceModel.getStatus() == AdvertConstants.AD_RES_STATUS_OFFLINE){
                advertResourceModelRepository.updateStatus(resourceModel.getId(), AdvertConstants.AD_RES_STATUS_AUDITING);
                advertResourceModelRepository.updateAuditStatus(resourceModel.getId(), AdvertConstants.AUDIT_STATUS_DRAFT);
            }else{
                advertResourceModelRepository.batchUpdateStatus(resourceModel.getLocId(), resourceModel.getEffectiveDay(), AdvertConstants.AUDIT_STATUS_PASS, AdvertConstants.AD_RES_STATUS_OFFLINE);
                advertResourceModelRepository.updateStatus(resourceModel.getId(), AdvertConstants.AD_RES_STATUS_ONLINE);
                advertCacheService.setLocResourceCache(resourceModel.getLocId(), resourceModel.getEffectiveDay(), resourceModel);
            }
        } else if (resourceStatusParams.getStatus() == AdvertConstants.AD_RES_STATUS_OFFLINE) {
            advertResourceModelRepository.updateStatus(resourceModel.getId(), AdvertConstants.AD_RES_STATUS_OFFLINE);
            advertCacheService.clearLocResourceCache(resourceModel.getLocId(), resourceModel.getEffectiveDay());
        } else {
            throw new JMException("状态参数非法");
        }

    }

    @Override
    public List<ResourceAppListVo> findAppList(ResourceAppListParams appListParams) {

        //获取指定广告组
        AdvertLocationGroupModel locationGroupModel = avertLocationGroupModelRepository.findFirstByCodeEquals(appListParams.getLocationCode());
        if (locationGroupModel == null) {
            throw new JMException(CommonErrorConstant.ADVERT_CODE_MISSING.getCode(),CommonErrorConstant.ADVERT_CODE_MISSING.getMsg());
        }

        List<ResourceAppListVo> resultList = new ArrayList<>();
        Set<Long> departmentIds = new HashSet<>();
        Integer today = Integer.parseInt(TimeUtils.formatDate(new Date(), "yyyyMMdd"));

        //获取指定广告组中生效的广告位
        List<AdvertLocationModel> locationList = advertLocationModelRepository.findAllByIsDeletedAndStatusAndGidOrderByFrameIdAsc(AdvertConstants.DELETE_FLAG_FALSE, AdvertConstants.AD_LOC_STATUS_ONLINE, locationGroupModel.getId());
        if (locationList.isEmpty()) {
            return resultList;
        }

        //遍历广告位获取当天该广告位下已审核的广告投放资源
        locationList.forEach(item -> {
            ResourceAppListVo resourceAppListVo = new ResourceAppListVo(item.getFrameId(), locationGroupModel.getTitle(), item.getDefaultPic(), item.getDefaultUrl(), TimeUtils.getLongDateStr(item.getCreateTime()));
            AdvertResourceModel advertResourceModel = getScheduleResource(item.getId(), today ,appListParams.getIsTest());
            if(advertResourceModel != null){
                resourceAppListVo.setResourceId(advertResourceModel.getId());
                resourceAppListVo.setAdLinkUrl(advertResourceModel.getAdUrl());
                resourceAppListVo.setAdPicUrl(advertResourceModel.getAdPic());
                resourceAppListVo.setAdTitle(advertResourceModel.getAdTitle());
                resourceAppListVo.setDepartmentId(advertResourceModel.getDepartmentId());
                resourceAppListVo.setTestAccount(advertResourceModel.getTestAccount());
                if(advertResourceModel.getCreateTime() != null){
                    resourceAppListVo.setCreateTime(TimeUtils.getLongDateStr(advertResourceModel.getCreateTime()));
                }
                departmentIds.add(advertResourceModel.getDepartmentId());
            }
            resultList.add(resourceAppListVo);
        });

        //获取业务部门名称
        Map<Long, AdvertDepartmentModel> departmentMap = advertDepartmentService.getDepartmentMap(departmentIds);
        resultList.forEach(e -> {
            if (departmentMap.containsKey(e.getDepartmentId())) {
                e.setDepartmentName(departmentMap.get(e.getDepartmentId()).getDepartmentName());
            }
        });

        return resultList;
    }

    /**
     * 获取指定广告位指定日期已生效的投放素材
     * @param locId 广告位ID
     * @param effectiveDay 排期日期
     * @param isTest 是否获取测试数据
     * @return AdvertResourceModel
     * @date 2020/8/20 18:12
     */
    private AdvertResourceModel getScheduleResource(Long locId, Integer effectiveDay, Integer isTest) {

        AdvertResourceModel advertResourceModel;

        if (isTest != null && isTest == AdvertConstants.COMMON_STATUS_TRUE) {
            //获取测试数据
            advertResourceModel = advertResourceModelRepository.findFirstByLocIdAndTestFlagOrderByCreateTimeDesc(locId, isTest);
        } else {
            // 获取指定广告位指定日期已生效的投放素材
            advertResourceModel = advertCacheService.getLocResourceCache(locId, effectiveDay);
            if (advertResourceModel == null) {
                advertResourceModel = advertCacheService.setLocResourceCache(locId, effectiveDay, null);
            }
        }

        return advertResourceModel;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(ResourceDetailDto resourceDetailDto, String username) {

        Subject subject = SecurityUtils.getSubject();

        AdvertLocationModel advertLocationModel = advertLocationModelRepository.findByIsDeletedAndGidAndFrameId(AdvertConstants.DELETE_FLAG_FALSE, resourceDetailDto.getGroupId(), resourceDetailDto.getFrameId());
        if (advertLocationModel == null) {
            throw new JMException("广告位不存在");
        }

        Date effectiveDate = TimeUtils.parseDate(resourceDetailDto.getEffectiveDate(), "yyyy-MM-dd");
        Integer effectiveDay = Integer.parseInt(TimeUtils.formatDate(effectiveDate, "yyyyMMdd"));
        Integer effectiveMonth = Integer.parseInt(TimeUtils.formatDate(effectiveDate, "yyyyMM"));

        AdvertUserModel advertUserModel = advertUserModelRepository.findFirstByIsDeletedAndUsername(AdvertConstants.DELETE_FLAG_FALSE, username);

        if (!subject.hasRole(AdvertConstants.ADVERT_ROLE_ADMIN)) {
            AdvertScheduleModel advertScheduleModel = advertScheduleModelRepository.findFirstByIsDeletedAndLocIdAndEffectiveDayAndDepartmentId(
                    AdvertConstants.DELETE_FLAG_FALSE, advertLocationModel.getId(), effectiveDay, advertUserModel.getDepartmentId());
            if (advertScheduleModel == null) {
                throw new JMException("广告位排期不存在");
            }
        }

        AdvertResourceModel advertResourceModel;
        if (resourceDetailDto.getId() != null) {
            advertResourceModel = advertResourceModelRepository.findByIsDeletedAndId(AdvertConstants.DELETE_FLAG_FALSE, resourceDetailDto.getId());
            if (advertResourceModel == null) {
                throw new JMException("广告资源ID不存在");
            }
            if (!subject.hasRole(AdvertConstants.ADVERT_ROLE_ADMIN)) {
                if(advertResourceModel.getAuditStatus() != AdvertConstants.AUDIT_STATUS_DRAFT){
                    throw new JMException("已审核的记录不可修改");
                }
                if(!advertResourceModel.getDepartmentId().equals(advertUserModel.getDepartmentId())){
                    throw new JMException("非本业务方资源不可修改");
                }
            }
            advertResourceModel.setUpdateTime(TimeUtils.getTimestamp());
            advertResourceModel.setEditor(username);
        } else {
            if (resourceDetailDto.getCoverFlag() != null && resourceDetailDto.getCoverFlag() == AdvertConstants.COMMON_STATUS_FALSE) {
                advertResourceModel = advertResourceModelRepository.findFirstByIsDeletedAndLocIdAndEffectiveDayOrderByCreateTimeDesc(
                        AdvertConstants.DELETE_FLAG_FALSE, advertLocationModel.getId(), effectiveDay);

                if (advertResourceModel != null && advertResourceModel.getStatus() != AdvertConstants.AD_RES_STATUS_DRAFT) {
                    throw new JMException(AdvertConstants.RESOURCE_STATUS_CODE_EXISTS, "相同广告位日期已有填报记录");
                }
            }
            advertResourceModel = new AdvertResourceModel();
            advertResourceModel.setCreateTime(TimeUtils.getTimestamp());
            advertResourceModel.setCreator(username);
            advertResourceModel.setStatus(AdvertConstants.AD_RES_STATUS_AUDITING);
            advertResourceModel.setAuditStatus(AdvertConstants.AUDIT_STATUS_DRAFT);
            advertResourceModel.setIsDeleted(AdvertConstants.DELETE_FLAG_FALSE);
            advertResourceModel.setSeqNo(0);
        }

        advertResourceModel.setLocId(advertLocationModel.getId());
        advertResourceModel.setGroupId(advertLocationModel.getGid());
        advertResourceModel.setFrameId(advertLocationModel.getFrameId());
        advertResourceModel.setAdPic(resourceDetailDto.getAdPic());
        advertResourceModel.setAdTitle(resourceDetailDto.getAdTitle());
        advertResourceModel.setAdUrl(resourceDetailDto.getAdUrl());
        advertResourceModel.setDepartmentId(advertUserModel.getDepartmentId() != null ? advertUserModel.getDepartmentId() : 0);
        advertResourceModel.setUserId(advertUserModel.getId() != null ? advertUserModel.getId() : 0);
        advertResourceModel.setEffectiveDate(TimeUtils.getTimestamp(effectiveDate.getTime()));
        advertResourceModel.setEffectiveDay(effectiveDay);
        advertResourceModel.setEffectiveMonth(effectiveMonth);
        advertResourceModel.setStartTime(effectiveDate.getTime());
        advertResourceModel.setEndTime(effectiveDate.getTime() + 86400 * 1000);
        advertResourceModel.setTestFlag(resourceDetailDto.getTestFlag());

        if (resourceDetailDto.getTestFlag() == AdvertConstants.COMMON_STATUS_TRUE) {
            advertResourceModel.setTestAccount(resourceDetailDto.getTestAccount());
            advertResourceModelRepository.clearTestFlag(advertLocationModel.getId(), effectiveDay);
        }

        advertResourceModelRepository.saveAndFlush(advertResourceModel);

        if (advertCacheService.getResourceSeqNoCache(advertResourceModel.getLocId(), effectiveDay) == null) {
            advertCacheService.setResourceSeqNoCache(advertResourceModel.getLocId(), effectiveDay);
        }
        advertCacheService.increaseResourceSeqNo(advertResourceModel.getLocId(), effectiveDay, advertResourceModel.getId());

        return advertResourceModel.getId();
    }

    @Override
    public ResourceDetailVo findDetail(Long resourceId) {
        AdvertResourceModel resourceModel = advertResourceModelRepository.findByIsDeletedAndId(AdvertConstants.DELETE_FLAG_FALSE,resourceId);
        if(resourceModel == null){
            return null;
        }

        AdvertLocationModel locationModel = avertLocationModelRepository.findByIdEquals(resourceModel.getLocId());
        AdvertLocationGroupModel locationGroupModel = advertLocationGroupModelRepository.findByIdEquals(locationModel.getGid());
        AdvertDepartmentModel departmentModel = advertDepartmentModelRepository.findFirstByIdEquals(resourceModel.getDepartmentId());

        ResourceDetailVo resourceDetailVo = transResourceModelToVo(resourceModel);
        resourceDetailVo.setDepartmentName(departmentModel.getDepartmentName());
        resourceDetailVo.setGroupId(locationModel.getGid());
        resourceDetailVo.setGroupName(locationGroupModel.getTitle());
        resourceDetailVo.setFrameId(locationModel.getFrameId());
        resourceDetailVo.setLocName(locationModel.getTitle());

        return resourceDetailVo;
    }

    private ResourceDetailVo transResourceModelToVo(AdvertResourceModel resourceModel) {

        if (resourceModel == null) {
            return null;
        }

        ResourceDetailVo resourceDetailVo = new ResourceDetailVo(resourceModel.getId(), resourceModel.getSeqNo().toString(), resourceModel.getLocId(), resourceModel.getDepartmentId(),
                resourceModel.getAdTitle(), resourceModel.getAdPic(), resourceModel.getAdUrl(), resourceModel.getTestFlag(), resourceModel.getTestAccount(), resourceModel.getEditor(),
                resourceModel.getCreator(), resourceModel.getStatus(), resourceModel.getAuditStatus(), resourceModel.getGroupId(), resourceModel.getFrameId());

        if(resourceModel.getCreateTime() != null){
            resourceDetailVo.setCreateTime(TimeUtils.getLongDateStr(resourceModel.getCreateTime()));
        }
        if(resourceModel.getAuditTime() != null){
            resourceDetailVo.setAuditTime(TimeUtils.getLongDateStr(resourceModel.getAuditTime()));
        }
        if(resourceModel.getUpdateTime() != null){
            resourceDetailVo.setUpdateTime(TimeUtils.getLongDateStr(resourceModel.getUpdateTime()));
        }
        if(resourceModel.getEffectiveDate() != null){
            resourceDetailVo.setEffectiveDate(TimeUtils.getLongDate(resourceModel.getEffectiveDate()));
        }

        return resourceDetailVo;
    }

    private List<ResourceDetailVo> fillResourceVoTitle(List<ResourceDetailVo> resourceVoList, Set<Long> groupIds, Set<Long> departmentIds, Set<Long> locIds ) {
        if (resourceVoList.isEmpty()) {
            return resourceVoList;
        }

        Map<Long, AdvertLocationGroupModel> groupMap = advertLocationGroupService.getGroupMap(groupIds);
        Map<Long, AdvertDepartmentModel> departmentMap = advertDepartmentService.getDepartmentMap(departmentIds);
        Map<Long, AdvertLocationModel> locationMap = advertLocationService.getLocationMap(locIds);

        resourceVoList.forEach(e -> {
            if (groupMap.containsKey(e.getGroupId())) {
                e.setGroupName(groupMap.get(e.getGroupId()).getTitle());
            }
            if (departmentMap.containsKey(e.getDepartmentId())) {
                e.setDepartmentName(departmentMap.get(e.getDepartmentId()).getDepartmentName());
            }
            if (locationMap.containsKey(e.getLocId())) {
                e.setLocName(locationMap.get(e.getLocId()).getTitle());
            }
        });

        return resourceVoList;
    }

    private Predicate createRecordQueryCondition(Root<AdvertResourceModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, ResourceListParams paramBody) {
        List<Predicate> baseList = new ArrayList<>();
        List<Predicate> list = new ArrayList<>();
        Integer today = Integer.parseInt(TimeUtils.formatDate(new Date(), "yyyyMMdd"));
        baseList.add(cb.equal(root.get("isDeleted").as(Integer.class), AdvertConstants.DELETE_FLAG_FALSE));
        String fieldStatus = "status";
        String fieldEffectiveDay = "effectiveDay";

        if (paramBody.getAuditStatus() != null) {
            baseList.add(cb.equal(root.get("auditStatus").as(Integer.class), paramBody.getAuditStatus()));
        }

        if (paramBody.getStatus() != null) {
            baseList.add(cb.equal(root.get(fieldStatus).as(Integer.class), paramBody.getStatus()));
        }

        if (paramBody.getGroupId() != null) {
            baseList.add(cb.equal(root.get("groupId").as(Long.class), paramBody.getGroupId()));
        }

        if (paramBody.getFrameId() != null) {
            baseList.add(cb.equal(root.get("frameId").as(Integer.class), paramBody.getFrameId()));
        }

        if (paramBody.getEffectiveDay() != null) {
            baseList.add(cb.equal(root.get(fieldEffectiveDay).as(Integer.class), paramBody.getEffectiveDay()));
        }

        if(StringUtils.isNotBlank(paramBody.getStartTime())){
            baseList.add(cb.greaterThanOrEqualTo(root.get("effectiveDate").as(String.class),paramBody.getStartTime()));
        }

        if(StringUtils.isNotBlank(paramBody.getEndTime())){
            baseList.add(cb.lessThanOrEqualTo(root.get("effectiveDate").as(String.class),paramBody.getEndTime()));
        }

        if(StringUtils.isNotBlank(paramBody.getUsername())){
            baseList.add(cb.equal(root.get("creator").as(String.class),paramBody.getUsername()));
        }

        if(paramBody.getDepartmentId() != null){
            baseList.add(cb.equal(root.get("departmentId").as(Long.class), paramBody.getDepartmentId()));
        }

        if(StringUtils.isNotBlank(paramBody.getSeqNo())){
            baseList.add(cb.equal(root.get("seqNo").as(String.class), paramBody.getSeqNo()));
        }

        if(paramBody.getListType() != null){
            if(paramBody.getListType() == AdvertConstants.PAGE_LIST_TYPE_RESOURCE_OFFLINE){
                list.add(cb.notEqual(root.get(fieldStatus).as(Integer.class), AdvertConstants.AD_RES_STATUS_ONLINE));
                list.add(cb.lessThan(root.get(fieldEffectiveDay).as(Integer.class), today));
            }else if(paramBody.getListType() == AdvertConstants.PAGE_LIST_TYPE_RESOURCE_ONLINE){
                baseList.add(cb.equal(root.get(fieldStatus).as(Integer.class), AdvertConstants.AD_RES_STATUS_ONLINE));
                baseList.add(cb.greaterThanOrEqualTo(root.get(fieldEffectiveDay).as(Integer.class), today));
            }
        }

        Predicate res;
        Predicate[] bp = new Predicate[baseList.size()];
        Predicate[] p = new Predicate[list.size()];
        Predicate bpd = cb.and(baseList.toArray(bp));
        Predicate lpd = cb.or(list.toArray(p));

        if(list.isEmpty()){
            res = cb.and(bpd);
        }else{
            res = cb.and(bpd, lpd);
        }

        query.where(res);
        return query.getRestriction();
    }

}
