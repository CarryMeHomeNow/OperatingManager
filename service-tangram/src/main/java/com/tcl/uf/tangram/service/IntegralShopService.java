package com.tcl.uf.tangram.service;

import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @author zhongfk on 2020/8/27.
 * @version 1.0
 */
public interface IntegralShopService {
    /**
     * 积分商城列表
     * @param paramDTO
     * @return
     */
    String getIntegralShop(TangramRequestParam paramDTO);

    /**
     * 我的兑换
     * @param paramDTO
     * @return
     */
    String getOrderList(TangramRequestParam paramDTO);
}
