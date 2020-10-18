package com.tcl.uf.version.service.impl;

import com.tcl.commondb.version.model.InternalVersionModel;
import com.tcl.commondb.version.repository.InternalVersionRepository;
import com.tcl.uf.common.base.constant.CommonConstant;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.version.dto.InternalVersionDto;
import com.tcl.uf.version.dto.InternalVersionParams;
import com.tcl.uf.version.service.InternalVersionService;
import com.tcl.uf.version.vo.InternalVersionDetailsVo;
import com.tcl.uf.version.vo.InternalVersionOptionVo;
import com.tcl.uf.version.vo.InternalVersionTabVo;
import com.tcl.uf.version.vo.InternalVersionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

/**
 * @author youyun.xu
 * @ClassName: ${CLASSNAME}
 * @Description: 文章内容管理
 * @date 2020/8/20 16:27
 */

@Service("internalVersionService")
public class InternalVersionServiceImpl implements InternalVersionService {

    @Autowired
    private InternalVersionRepository internalVersionRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        internalVersionRepository.deleteInternalVersion(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(InternalVersionDto versionDto,String username) {
        InternalVersionModel internalVersionModel;
        if(versionDto.getId() == null){
            internalVersionModel = new InternalVersionModel();
            internalVersionModel.setCreator(username);
            internalVersionModel.setCreateTime(new Date());
            internalVersionModel.setUseStatus(CommonConstant.DELETE_FLAG_EFFECTIVE);
        }else{
            internalVersionModel = internalVersionRepository.findByIdEquals(versionDto.getId());
            internalVersionModel.setEditor(username);
        }
        BeanUtils.copyProperties(versionDto,internalVersionModel);
        internalVersionRepository.saveAndFlush(internalVersionModel);
        return internalVersionModel.getId();
    }

    @Override
    public InternalVersionDetailsVo findInternalVersion(Long id) {
        InternalVersionDetailsVo internalVersionDetailsVo = null;
        InternalVersionModel internalVersionModel= internalVersionRepository.findByIdEquals(id);
        if(internalVersionModel != null){
            internalVersionDetailsVo = new InternalVersionDetailsVo();
            BeanUtils.copyProperties(internalVersionModel,internalVersionDetailsVo);
        }
        return internalVersionDetailsVo;
    }

    @Override
    public ListWithPage<InternalVersionVo> findList(Pageable pageable, InternalVersionParams params) {
        Long sumTotal = null;
        List<InternalVersionVo> records = null;
        //构造初始状态排序条件
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
        Page<InternalVersionModel> result = internalVersionRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> createConfigurationRecordQueryCondition(root, criteriaQuery, criteriaBuilder, params), page);
        //数据库实体对象转VO对象
        if (result != null) {
            sumTotal = result.getTotalElements();
            records = configurationRecordMemoryValueToViewObject(result.getContent());
        } else {
            sumTotal = Long.getLong("0");
            records = new ArrayList<>();
        }
        return PageUtils.formatData(records, pageable, sumTotal);
    }

    @Override
    public List<InternalVersionTabVo> tabs() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("tabEcVersion","电商版本号");
        map.put("tabSvVersion","服务版本号");
        map.put("tabLotVersion","IOT版本号");
        map.put("tabSeVersion","场景版本号");
        map.put("tabMyVersion","我的版本号");

        List<InternalVersionTabVo> versions = new ArrayList<InternalVersionTabVo>();
        List<InternalVersionModel> list = internalVersionRepository.findByUseStatus(CommonConstant.DELETE_FLAG_EFFECTIVE);
        for (String fieldName : map.keySet()) {
            InternalVersionTabVo internalVersionTabVo = new InternalVersionTabVo();
            internalVersionTabVo.setTabName(map.get(fieldName));
            internalVersionTabVo.setValue(fieldName);
            internalVersionTabVo.setList(tansform(fieldName,list));
            versions.add(internalVersionTabVo);
        }
        return versions;
    }

    @Override
    public List<InternalVersionOptionVo> option() {
        List<InternalVersionOptionVo> listVo = new ArrayList<InternalVersionOptionVo>();
        List<InternalVersionModel> list = internalVersionRepository.findByUseStatus(CommonConstant.DELETE_FLAG_EFFECTIVE);
        list.parallelStream().forEach(map -> {
            InternalVersionOptionVo versionOptionVo = new InternalVersionOptionVo();
            String version = map.getInternalVersion();
            versionOptionVo.setName(version);
            versionOptionVo.setValue(map.getId());
            listVo.add(versionOptionVo);
        });
        return listVo;
    }

    private List<String> tansform(String fieldName, List<InternalVersionModel> list) {
        List<String> values= new ArrayList<String>();
        for(InternalVersionModel internalVersionModel:list){
            Class clazz= InternalVersionModel.class;
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                String value= (String)field.get(internalVersionModel);
                values.add(value);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return values;
    }

    private Predicate createConfigurationRecordQueryCondition(Root<InternalVersionModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, InternalVersionParams params) {
        List<Predicate> list = new ArrayList<>();
        list.add(cb.equal(root.get("useStatus").as(Integer.class), (byte)1));

        if (!StringUtils.isEmpty(params.getInternalVersion())) {
            list.add(cb.equal(root.get("internalVersion").as(String.class), params.getInternalVersion()));
        }
        if (!StringUtils.isEmpty(params.getTabName()) && !StringUtils.isEmpty(params.getValue())) {
            list.add(cb.equal(root.get(params.getTabName()).as(String.class), params.getValue()));
        }
        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        return query.getRestriction();
    }

    private List<InternalVersionVo> configurationRecordMemoryValueToViewObject(List<InternalVersionModel> records) {
        List<InternalVersionVo> list = new ArrayList<InternalVersionVo>();
        for (InternalVersionModel entity : records) {
            InternalVersionVo viewObject = new InternalVersionVo();
            viewObject.setId(entity.getId());
            viewObject.setInternalVersion(entity.getInternalVersion());
            viewObject.setAppName(entity.getAppName());
            viewObject.setTabEcVersion(entity.getTabEcVersion());
            viewObject.setTabSvVersion(entity.getTabSvVersion());
            viewObject.setTabLotVersion(entity.getTabLotVersion());
            viewObject.setTabSeVersion(entity.getTabSeVersion());
            viewObject.setTabMyVersion(entity.getTabMyVersion());
            viewObject.setAcSdkVersion(entity.getAcSdkVersion());
            viewObject.setTabEcUrl(entity.getTabEcUrl());
            viewObject.setTabSvUrl(entity.getTabSvUrl());
            viewObject.setTabIotUrl(entity.getTabIotUrl());
            viewObject.setTabSeUrl(entity.getTabSeUrl());
            viewObject.setTabMyUrl(entity.getTabMyUrl());
            viewObject.setAcSdkUrl(entity.getAcSdkUrl());
            viewObject.setEditor(entity.getEditor());
            viewObject.setCreator(entity.getCreator());
            viewObject.setOfficialDownloadUrl(entity.getOfficialDownloadUrl());
            viewObject.setCreateTime(TimeUtils.getTimeStampStr(entity.getCreateTime()));
            list.add(viewObject);
        }
        return list;
    }
}
