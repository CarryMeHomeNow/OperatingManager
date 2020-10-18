package com.tcl.uf.tangram.service;

import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @author zhongfk on 2020/8/25.
 * @version 1.0
 */
public interface RightSetService {
    /**
     * 会员权益模板
     * @param paramDTO
     * @return
     */
    String getMemberRightSet(TangramRequestParam paramDTO);

}
