package com.tcl.uf.tangram.service;

import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @author yxlong
 * @version 1.0
 */
public interface HomeService {
    /**
     * 获取商品首页数据
     * @param reqCode 请求tangram模版
     * @return 首页tangram模版数据json
     */
    String queryHomeData(String reqCode,String uiType);

    /**
     * 获取商品模版里面组件的items数据，分页功能需要
     * @param goodsgroupUuid 商品分类uuid
     * @param pageNo 当前页
     * @param pageSize 页大小
     * @return tangram商品组件的
     */
    String queryProductModulesContents(String goodsgroupUuid,
                                     String pageNo,String pageSize);

    /**
     * 按推荐商品分组名获取对应的商品列表
     * @return 返回商品列表tangram模版数据
     */
    String queryGroupProductContents(String reqCode,String goodsgroupUuid,
                                       String pageNo,String pageSize);
}
