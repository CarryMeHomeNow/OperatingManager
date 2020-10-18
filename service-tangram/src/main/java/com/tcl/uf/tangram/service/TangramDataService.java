package com.tcl.uf.tangram.service;

import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @Desc : Tangram数据处理接口
 * @Author Mr.HePeng
 * @Date 2020/8/10 14:54
 */
public interface TangramDataService<R, M> {

    /**
     * 查询第三方数据接口（API、CACHE、DB）
     *
     * @param config 组件配置信息
     * @param param  请求参数
     * @return 对方返回的数据结构
     */
    R queryData(TangramTemplateModuleConfig config, TangramRequestParam param);

    /**
     * 将地方调用返回的数据结构转成组件的数据结构
     *
     * @param r 对方返回的数据结构
     * @return 组件需要的数据结构
     */
    M transData(TangramTemplateModuleConfig config, R r);
}
