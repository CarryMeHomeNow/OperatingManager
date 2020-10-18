package com.tcl.uf.points.service;

import com.tcl.uf.points.vo.IntegralDetailVo;

import javax.ws.rs.HEAD;
import java.util.List;

public interface PointService {

    public Integer getPointBySSOID(String token,Long accountId);

    /**
     *
     * @param token 用户token
     * @param accountId ssoid
     * @param score 积分
     * @param description 描述
     * @param source 来源
     * @param opType 操作 + -
     * @param sourceType 来源类型
     * @param growValue 成长值
     * @return
     */
    public boolean pushPoints(String token,Long accountId,Integer score,
                              String description,String source,
                                      String opType,String sourceType,Integer growValue);

    /**
     * 查询个人积分明细
     * @param accountId
     * @return
     */
    List<IntegralDetailVo> getPointDetail(Long accountId,String date,Integer form,Integer size,String token);
}
