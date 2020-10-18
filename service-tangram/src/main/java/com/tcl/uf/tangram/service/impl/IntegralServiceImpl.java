package com.tcl.uf.tangram.service.impl;

import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.IntegralService;
import com.tcl.uf.tangram.vo.TangramRequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhongfk on 2020/8/25.
 * @version 1.0
 */
@Service
public class IntegralServiceImpl implements IntegralService {
    @Autowired
    private CommonService commonService;

    @Override
    public String getIntegralActivity(TangramRequestParam paramDTO) {
        commonService.findTemplateById(paramDTO.getReqCode());
        return null;
    }
}
