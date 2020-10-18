package com.tcl.uf.version.service;

import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.version.dto.InternalVersionDto;
import com.tcl.uf.version.dto.InternalVersionParams;
import com.tcl.uf.version.vo.InternalVersionDetailsVo;
import com.tcl.uf.version.vo.InternalVersionOptionVo;
import com.tcl.uf.version.vo.InternalVersionTabVo;
import com.tcl.uf.version.vo.InternalVersionVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InternalVersionService {

    void delete(List<Long> ids);

    Long saveOrUpdate(InternalVersionDto versionDto,String username);

    InternalVersionDetailsVo findInternalVersion(Long id);

    ListWithPage<InternalVersionVo> findList(Pageable pageable,InternalVersionParams params);

    List<InternalVersionTabVo> tabs();

    List<InternalVersionOptionVo> option();

}
