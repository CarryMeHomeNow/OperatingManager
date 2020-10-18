package com.tcl.uf.advert.service;

import com.tcl.commondb.advert.model.AdvertResourceModel;

import java.util.List;
import java.util.Map;

public interface AdvertCacheService {

    void clearLocResourceCache(Long locId, List<Integer> effectiveDays);

    void clearLocResourceCache(Long locId, Integer effectiveDay);

    AdvertResourceModel getLocResourceCache(Long locId, Integer effectiveDay);

    AdvertResourceModel setLocResourceCache(Long locId, Integer effectiveDay, AdvertResourceModel resourceModel);

    Map<Object, Object> getLocResourceCacheMap(Long locId);

    Integer getResourceSeqNoCache(Long locId, Integer effectiveDay);

    Integer setResourceSeqNoCache(Long locId, Integer effectiveDay);

    Integer increaseResourceSeqNo(Long locId, Integer effectiveDay, Long resourceId);
}
