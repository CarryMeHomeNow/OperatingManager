package com.tcl.commonservice.service;

import com.tcl.commonservice.service.dto.ResourceAppListParams;
import com.tcl.commonservice.service.vo.ResourceAppListVo;
import com.tcl.uf.common.base.dto.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhongfk on 2020/8/25.
 * @version 1.0
 */
@FeignClient(value="service-advert")
public interface AdvertService {
    /**
     * APP获取广告位广告素材列表
     * @param appListParams
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/resource/back/app/List", method = RequestMethod.POST)
    ResponseData<List<ResourceAppListVo>> intAppList(@RequestBody ResourceAppListParams appListParams,@RequestParam("accountId") Long accountId);
}
