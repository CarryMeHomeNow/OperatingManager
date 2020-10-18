package com.tcl.uf.tangram.service;

import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @author zhongfk on 2020/8/27.
 * @version 1.0
 */
public interface PointTaskService {
    /**
     * 积分任务
     * @param paramDTO
     * @return
     */
    String getPointTask(TangramRequestParam paramDTO);

    /**
     * 积分详情
     * @param paramDTO
     * @return
     */
    String getTaskDetail(TangramRequestParam paramDTO);
}
