package com.tcl.uf.advert.service;

import com.tcl.commondb.advert.model.AdvertLocationModel;
import com.tcl.uf.advert.dto.*;
import com.tcl.uf.advert.vo.LocationDetailVo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

public interface AdvertLocationService {

    Long saveOrUpdate(LocationDto locationDto, String username);

    LocationDetailVo findDetail(Long locId);

    ListWithPage<LocationDetailVo> findList(Pageable pageable, LocationListParams locationListParams);

    void updateStatus(LocationStatusParams locationStatusParams, String username);

    Map<Long, AdvertLocationModel> getLocationMap(Set<Long> locIds);

}
