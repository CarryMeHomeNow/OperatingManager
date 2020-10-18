package com.tcl.uf.tangram.service;

import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 */
public interface IntegralDetailService {
    /**
     * 获取积分明细
     * @param paramDTO
     * @return
     */
    String getIntegralDetail(TangramRequestParam paramDTO);
}
