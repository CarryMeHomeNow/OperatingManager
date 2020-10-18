package com.tcl.uf.version.service;


import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.version.dto.ExternalStatisticsParams;
import com.tcl.uf.version.vo.ExternalStatisticsVo;
import org.springframework.data.domain.Pageable;

public interface ExternalStatisticsService {

    ListWithPage<ExternalStatisticsVo> statList(Pageable pageable, ExternalStatisticsParams params);


}
