package com.tcl.uf.advert.service;

import com.tcl.commondb.advert.model.AdvertDepartmentModel;

import java.util.Map;
import java.util.Set;

public interface AdvertDepartmentService {

    Map<Long, AdvertDepartmentModel> getDepartmentMap(Set<Long> departmentIds);

    Map<String, Long> getDepartmentTitleMap();

    Map<String, Long> getDepartmentTitleMapByType(Integer type);

}
