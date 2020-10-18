package com.tcl.uf.advert.service.impl;

import com.tcl.commondb.advert.model.AdvertDepartmentModel;
import com.tcl.commondb.advert.repository.AdvertDepartmentModelRepository;
import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.service.AdvertDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertDepartmentServiceImpl
 * @Description:广告管理平台业务部门管理
 * @date 2020/8/17 19:09
 */
@Service("AdvertDepartmentService")
public class AdvertDepartmentServiceImpl implements AdvertDepartmentService {

    @Autowired
    AdvertDepartmentModelRepository advertDepartmentModelRepository;

    @Override
    public Map<Long, AdvertDepartmentModel> getDepartmentMap(Set<Long> departmentIds) {
        List<AdvertDepartmentModel> departmentList = advertDepartmentModelRepository.findAllByIdIn(departmentIds);
        return departmentList.stream().collect(Collectors.toMap(AdvertDepartmentModel::getId, advertDepartmentModel -> advertDepartmentModel));
    }

    @Override
    public Map<String, Long> getDepartmentTitleMap() {
        List<AdvertDepartmentModel> departmentList = advertDepartmentModelRepository.findAllByIsDeleted(AdvertConstants.DELETE_FLAG_FALSE);
        return departmentList.stream().collect(Collectors.toMap(AdvertDepartmentModel::getDepartmentName, AdvertDepartmentModel::getId));
    }

    @Override
    public Map<String, Long> getDepartmentTitleMapByType(Integer type) {
        List<AdvertDepartmentModel> departmentList = advertDepartmentModelRepository.findAllByIsDeletedAndType(AdvertConstants.DELETE_FLAG_FALSE, type);
        return departmentList.stream().collect(Collectors.toMap(AdvertDepartmentModel::getDepartmentName, AdvertDepartmentModel::getId));
    }
}
