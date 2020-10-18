package com.tcl.uf.advert.service;

import com.tcl.commondb.advert.model.AdvertLocationGroupModel;
import com.tcl.uf.advert.vo.LocationGroupListVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AdvertLocationGroupService {

    Map<Long, AdvertLocationGroupModel> getGroupMap(Set<Long> groupIds);

    Map<String, AdvertLocationGroupModel> getGroupTitleMap();

    List<LocationGroupListVo> findList();

}
