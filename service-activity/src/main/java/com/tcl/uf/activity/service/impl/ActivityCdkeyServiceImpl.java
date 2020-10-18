package com.tcl.uf.activity.service.impl;

import com.tcl.commondb.activity.model.ActivityCdkeyDetailModel;
import com.tcl.commondb.activity.model.ActivityCdkeyModel;
import com.tcl.commondb.activity.repository.ActivityCdKeyDetailModelRepository;
import com.tcl.commondb.activity.repository.ActivityCdKeyModelRepository;
import com.tcl.uf.activity.dto.ActivityCdkeyListDto;
import com.tcl.uf.activity.service.ActivityCdkeyService;
import com.tcl.uf.activity.vo.ActivityCdkeySaveVo;
import com.tcl.uf.activity.vo.ActivityCdkeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.HEAD;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ActivityCdkeyServiceImpl implements ActivityCdkeyService {

    @Autowired
    private ActivityCdKeyModelRepository activityCdKeyModelRepository;
    @Autowired
    private ActivityCdKeyDetailModelRepository activityCdKeyDetailModelRepository;

    @Override
    public Map<String,Object> findAll(ActivityCdkeyListDto activityCdkeyListDto, Integer page,Integer size) {
        HashMap<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PageRequest pageable = PageRequest.of(page, size);

        List<ActivityCdkeyVo> voList = new ArrayList<>();

        Date endTime = activityCdkeyListDto.getEndTime();
        Integer cdType = activityCdkeyListDto.getCdType();
        Date startTime = activityCdkeyListDto.getStartTime();
        String name = activityCdkeyListDto.getName();
        Page<ActivityCdkeyModel> cdkeyModels = activityCdKeyModelRepository.findAll(new Specification<ActivityCdkeyModel>() {
            @Override
            public Predicate toPredicate(Root<ActivityCdkeyModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //条件1 标题名模糊搜索
                if(!StringUtils.isEmpty(name)){
//                    list.add(cb.like(root.get("name").as(String.class),name));
                    list.add(cb.like(root.get("name"), "%" + name + "%"));
                }
                // 时间范围内搜索
                if(!StringUtils.isEmpty(startTime)){
                    list.add(cb.between(root.get("start_time"),startTime,endTime));

                }
                //条件3  兑换类型搜索
                if(cdType != null){
                    list.add(cb.equal(root.get("cd_type").as(Integer.class),cdType));
                }
                query.where(cb.and(list.toArray(new Predicate[list.size()])));

                return query.getRestriction();
            }
        }, pageable);

        for (ActivityCdkeyModel activityCdKeyModel : cdkeyModels) {
                ActivityCdkeyVo activityCdkeyVo = new ActivityCdkeyVo();
                activityCdkeyVo.setId(activityCdKeyModel.getId());
                activityCdkeyVo.setName(activityCdKeyModel.getName());
                activityCdkeyVo.setCdType(activityCdKeyModel.getCdType());
//                activityCdkeyVo.setGrantStutas(activityCdKeyModel.getGrantStatus());
                activityCdkeyVo.setTotal(activityCdKeyModel.getTotal());
                activityCdkeyVo.setStatus(activityCdKeyModel.getStatus());
                activityCdkeyVo.setCreateTime(activityCdKeyModel.getCreateTime());
                activityCdkeyVo.setEndTime(activityCdKeyModel.getEndTime());

                activityCdkeyVo.setStartTime(activityCdKeyModel.getStartTime());
//                activityCdkeyVo.setCdPoints(activityCdKeyModel.getCdPoints());
//                activityCdkeyVo.setCoupon(activityCdKeyModel.getCoupon());
//                activityCdkeyVo.setProductName(activityCdKeyModel.getProductName());
//                activityCdkeyVo.setProductUrl(activityCdKeyModel.getProductUrl());
//                activityCdkeyVo.setCdPoints(activityCdKeyModel.getCdPoints());
                voList.add(activityCdkeyVo);

            map.put("totalNum",cdkeyModels.getTotalElements());
            map.put("rows",voList);
        }
        return map;
    }


    @Override
    public boolean insertCdKey(ActivityCdkeySaveVo activityCdkeySaveVo) {
        Date date = new Date();
        ActivityCdkeyModel activityCdkeyModel = new ActivityCdkeyModel();
        activityCdkeyModel.setName(activityCdkeySaveVo.getName());
        activityCdkeyModel.setCdType(activityCdkeySaveVo.getCdType());
        activityCdkeyModel.setCreateTime(date);
        activityCdkeyModel.setUpdateTime(date);
        //新建默认 2生效   isdetele 1 否
        activityCdkeyModel.setStatus(2);
        activityCdkeyModel.setIsDelete((byte) 1);
        activityCdkeyModel.setStartTime(activityCdkeySaveVo.getStartTime());
        activityCdkeyModel.setEndTime(activityCdkeySaveVo.getEndTime());
        activityCdkeyModel.setTotal(activityCdkeySaveVo.getTotal());
//        activityCdkeyModel.setProductUrl(activityCdkeySaveVo.getProductUrl());
//        activityCdkeyModel.setCoupon(activityCdkeySaveVo.getCoupon());
//        activityCdkeyModel.setGrantStatus(activityCdkeySaveVo.getGrantStatus());
//        activityCdkeyModel.setProductName(activityCdkeySaveVo.getProductName());
//        activityCdkeyModel.setCdPoints(activityCdkeySaveVo.getCdPoints());

         activityCdKeyModelRepository.saveAndFlush(activityCdkeyModel);

        return true;
    }

    @Override
    public boolean updateCdKey(ActivityCdkeySaveVo activityCdkeySaveVo) {
        Date date = new Date();
        ActivityCdkeyModel activityCdkeyModel = new ActivityCdkeyModel();

        activityCdkeyModel.setName(activityCdkeySaveVo.getName());
        activityCdkeyModel.setCdType(activityCdkeySaveVo.getCdType());
        activityCdkeyModel.setCreateTime(activityCdkeySaveVo.getCreateTime());
        activityCdkeyModel.setUpdateTime(date);
        //默认 2生效   isdetele 1 否
        activityCdkeyModel.setStatus(2);
        activityCdkeyModel.setIsDelete((byte) 1);
        activityCdkeyModel.setStartTime(activityCdkeyModel.getStartTime());
        activityCdkeyModel.setEndTime(activityCdkeySaveVo.getEndTime());
        activityCdkeyModel.setTotal(activityCdkeySaveVo.getTotal());
//        activityCdkeyModel.setProductUrl(activityCdkeySaveVo.getProductUrl());
//        activityCdkeyModel.setCoupon(activityCdkeySaveVo.getCoupon());
//        activityCdkeyModel.setGrantStatus(activityCdkeySaveVo.getGrantStatus());
//        activityCdkeyModel.setProductName(activityCdkeySaveVo.getProductName());
//        activityCdkeyModel.setCdPoints(activityCdkeySaveVo.getCdPoints());
        activityCdkeyModel.setId(activityCdkeySaveVo.getId());


        activityCdKeyModelRepository.saveAndFlush(activityCdkeyModel);

        return true;
    }


    @Override
    public boolean deleteCdKey(Long id) {
        //软删除
//        activityCdKeyModelRepository.deleteById(id);
        //数据库删除

//        ActivityCdkeyModel cdkeyModel = activityCdKeyModelRepository.findOne(id);
//    if (cdkeyModel !=null){

    activityCdKeyModelRepository.deleteById(id);
    return true;

    }

    @Override
    public boolean updateCdKeyStatus(Long id, Integer status) {

            activityCdKeyModelRepository.updateStatus(id,status);
            return true;

    }

    @Override
    public Page<ActivityCdkeyModel> exportCdKey(Pageable pageable) {
        return null;
    }


    public List<ActivityCdkeyModel> export(  Long[] ids) {
        List<ActivityCdkeyModel> list = new ArrayList<>();
        for (Long id : ids) {

//            ActivityCdkeyModel model = activityCdKeyModelRepository.findOne(id);
//            list.add(model);
        }
        return list;
    }


    @Override
    public boolean exchangeCdKey(String cdk) throws Exception {
        Date date = new Date();
        long time = date.getTime();
        //先查询该卡券信息，是否过期
        ActivityCdkeyDetailModel detailModel = activityCdKeyDetailModelRepository.findByCode(cdk);

        //查询卡券的是否已经使用
        if (detailModel.getUseStatus() ==1){
            //卡券已使用
            throw new Exception("卡券已使用");
        }
        //先查询该卡券信息，是否过期
//        if (time < cdkeyModel.getEndTime().getTime()){
            //过期
            throw new Exception("卡券已过期");
//        }
        //可以进行兑换，选择兑换的类型，改变兑换券状态 失效 1
//        activityCdKeyModelRepository.updateStatus(cdkeyModel.getId(),1);

//        return true;
    }

}
