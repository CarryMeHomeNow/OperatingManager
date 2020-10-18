package com.tcl.uf.advert.service;

import com.tcl.uf.advert.dto.*;
import com.tcl.uf.advert.vo.ResourceAppListVo;
import com.tcl.uf.advert.vo.ResourceDetailVo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertResourceService {

    void resourceAudit(ResourceAuditParams resourceAuditParams, String username);

    ListWithPage<ResourceDetailVo> resourceAuditList(Pageable pageable, ResourceAuditListParams resourceAuditListParams);

    ListWithPage<ResourceDetailVo> resourceStatusList(Pageable pageable, ResourceStatusListParams resourceStatusListParams, String username);

    ListWithPage<ResourceDetailVo> resourceList(Pageable pageable, ResourceListParams resourceListParams);

    void updateStatus(ResourceStatusParams resourceStatusParams, String username);

    List<ResourceAppListVo> findAppList(ResourceAppListParams appListParams);

    Long saveOrUpdate(ResourceDetailDto resourceDetailDto, String username);

    ResourceDetailVo findDetail(Long resourceId);

}
