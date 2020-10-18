package com.tcl.uf.version.service;

import com.tcl.commondb.version.model.PublishPlatformModel;
import com.tcl.uf.common.base.ex.ProcessControlException;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.version.dto.ExternalListParams;
import com.tcl.uf.version.dto.ExternalOnlineParams;
import com.tcl.uf.version.dto.ExternalUpdateParams;
import com.tcl.uf.version.vo.ExternalDetailVo;
import com.tcl.uf.version.vo.ExternalOnlineVo;
import com.tcl.uf.version.vo.NewVersionVo;
import com.tcl.uf.version.vo.PlatformDetailVo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Author thj.
 * @Date Created in 2020/8/19 16:43
 * @description 应用渠道版本管理服务类
 */
public interface ExternalVersionService {

    void saveOrUpdate(ExternalUpdateParams params) throws ProcessControlException;

    ListWithPage<ExternalDetailVo> externalList(Pageable pageable, ExternalListParams params);

    void delete(List<Long> ids);

    ExternalDetailVo externalDetail(Long externalId);

    ListWithPage<ExternalOnlineVo> onlineList(Pageable pageable);

    NewVersionVo findNewVersionByPlatformId(String platformId);

    ExternalDetailVo onlineDetail(ExternalOnlineParams params);

    List<PlatformDetailVo> platformList();

    Map<String, PublishPlatformModel> queryPlatformMap();
}
