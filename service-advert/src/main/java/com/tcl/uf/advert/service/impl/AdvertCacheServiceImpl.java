package com.tcl.uf.advert.service.impl;

import com.tcl.commondb.advert.model.AdvertResourceModel;
import com.tcl.commondb.advert.repository.AdvertLocationGroupModelRepository;
import com.tcl.commondb.advert.repository.AdvertLocationModelRepository;
import com.tcl.commondb.advert.repository.AdvertResourceModelRepository;
import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.service.AdvertCacheService;
import com.tcl.uf.advert.utils.RedisUtils;
import com.tcl.uf.common.base.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertCacheServiceImpl
 * @Description: 广告管理平台缓存管理
 * @date 2020/8/21 11:19
 */

@Service("AdvertCacheService")
public class AdvertCacheServiceImpl implements AdvertCacheService {
    private static final Logger _log = LoggerFactory.getLogger(AdvertCacheServiceImpl.class);

    @Autowired
    AdvertLocationGroupModelRepository avertLocationGroupModelRepository;

    @Autowired
    AdvertLocationModelRepository advertLocationModelRepository;

    @Autowired
    AdvertResourceModelRepository advertResourceModelRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void clearLocResourceCache(Long locId, List<Integer> effectiveDays) {
        if (effectiveDays == null || effectiveDays.isEmpty()) {
            return;
        }
        effectiveDays.forEach(item -> redisUtils.hdel(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE + ":" + locId, item.toString()));
    }

    @Override
    public void clearLocResourceCache(Long locId, Integer effectiveDay) {
        redisUtils.hdel(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE + ":" + locId, effectiveDay.toString());
    }

    @Override
    public AdvertResourceModel getLocResourceCache(Long locId, Integer effectiveDay) {
        Object value = redisUtils.hget(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE + ":" + locId, effectiveDay.toString());
        AdvertResourceModel vo = null;
        if (value != null) {
            vo = (AdvertResourceModel) value;
            _log.info("广告资源缓存资源ID：广告位{},日期{},资源ID{},资源标题{}", locId, effectiveDay, vo.getId(), vo.getAdTitle());
        }
        return vo;
    }

    @Override
    public AdvertResourceModel setLocResourceCache(Long locId, Integer effectiveDay, AdvertResourceModel resourceModel) {
        AdvertResourceModel model;
        if(resourceModel != null){
            model = resourceModel;
        }else{
            model = advertResourceModelRepository.findFirstByIsDeletedAndStatusAndAuditStatusAndLocIdAndEffectiveDayOrderByCreateTimeDesc(
                    AdvertConstants.DELETE_FLAG_FALSE, AdvertConstants.AD_RES_STATUS_ONLINE, AdvertConstants.AUDIT_STATUS_PASS, locId, effectiveDay);
            if (model == null) {
                _log.info("广告资源缓存设置为空：广告位{},日期{}", locId, effectiveDay);
                return null;
            }
        }
        Integer today = Integer.parseInt(TimeUtils.formatDate(new Date(), "yyyyMMdd"));
        Integer remainderDay = effectiveDay - today + 1;
        Long expireTime = remainderDay.longValue() * 86400;

        if (expireTime > 0) {
            redisUtils.hset(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE + ":" + locId, effectiveDay.toString(), model, expireTime);
        } else {
            redisUtils.hdel(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE + ":" + locId, effectiveDay.toString());
        }
        _log.info("广告资源缓存设置成功：广告位{},日期{}", locId, effectiveDay);
        return model;
    }

    @Override
    public Map<Object, Object> getLocResourceCacheMap(Long locId) {
        if(locId == null){
            return null;
        }
        return redisUtils.hmget(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE + ":" + locId);
    }

    @Override
    public Integer getResourceSeqNoCache(Long locId, Integer effectiveDay) {
        Object value = redisUtils.get(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE_SEQNO + ":" + locId + ":" + effectiveDay);
        Integer seqNo = null;
        if (value != null) {
            seqNo = (Integer) value;
        }
        return seqNo;
    }

    @Override
    public Integer setResourceSeqNoCache(Long locId, Integer effectiveDay) {
        Integer seqNo = advertResourceModelRepository.findMaxSeqNoByLocIdAndEffectiveDay(locId,effectiveDay);
        redisUtils.set(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE_SEQNO + ":" + locId + ":" + effectiveDay, seqNo);
        return seqNo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer increaseResourceSeqNo(Long locId, Integer effectiveDay, Long resourceId) {
        redisUtils.incr(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE_SEQNO + ":" + locId + ":" + effectiveDay, 1L);
        Object value = redisUtils.get(AdvertConstants.REDIS_NAMESPACE_LOCATION_RESOURCE_SEQNO + ":" + locId + ":" + effectiveDay);
        Integer seqNo = (Integer) value;
        advertResourceModelRepository.setSeqNo(resourceId, seqNo);
        return seqNo;
    }
}
