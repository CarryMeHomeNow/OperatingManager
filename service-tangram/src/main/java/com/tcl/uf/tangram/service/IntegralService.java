package com.tcl.uf.tangram.service;

import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @author zhongfk on 2020/8/25.
 * @version 1.0
 */
public interface IntegralService {
    /**
     * 积分活动
     * @param paramDTO
     * @return
     */
    String getIntegralActivity(TangramRequestParam paramDTO);
}
