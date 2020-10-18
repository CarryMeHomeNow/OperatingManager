package com.tcl.uf.tangram.service;

import java.util.List;
import java.util.Map;

/**
 * @Desc : 产品分类页服务
 * @Author yxlong
 * @Date 2020/8/31 2:04 下午
 */
public interface ProductCategoryService {

    /**
     * 获取产品分类页左侧分类信息
     * @return
     */
    List<Map<String, Object>> getProductCategory();

    /**
     * 获取右侧分类的具体tangram模版数据
     * @param categroyName 分类的名字
     * @param reqCode tangram模版编号
     * @return
     */
    String getCategoryTangramData(String categroyName,String reqCode);

}
