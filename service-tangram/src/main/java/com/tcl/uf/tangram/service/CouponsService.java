package com.tcl.uf.tangram.service;

import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @author zhongfk on 2020/8/29.
 * @version 1.0
 */
public interface CouponsService {
    /**
     * 获取我的优惠券
     * @param paramDTO
     * @return
     */
    String getCoupons(TangramRequestParam paramDTO);
}
