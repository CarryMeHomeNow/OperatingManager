package com.tcl.uf.tangram.service;

import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 */
public interface MemberPlayService {
    /**
     * 会员玩法
     * @param paramDTO
     * @return
     */
    String getMemberPlay(TangramRequestParam paramDTO);
}
