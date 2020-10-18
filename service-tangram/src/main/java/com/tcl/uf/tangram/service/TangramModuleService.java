package com.tcl.uf.tangram.service;

import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @Desc : 组件接口
 * @Author Mr.HePeng
 * @Date 2020/8/10 15:43
 */
public interface TangramModuleService<T, M> {

    /**
     * 调用数据接口处理
     *
     * @param moduleConfig 组件配置信息
     * @param param  请求参数
     * @return JSON（String）
     */
    T process(TangramTemplateModuleConfig moduleConfig, TangramRequestParam param);

    /**
     *将组件的数据转换成模板数据，默认是JSON格式（String）
     * @param m 数据接口返回的组件标准数据
     * @return JSON（String）
     */
    T transData(M m);

    /**
     *将组件的数据转换成模板数据，默认是JSON格式（String）
     * @param m 数据接口返回的组件标准数据
     * @param config 模版配置参数
     * @return JSON（String）
     */
    T transData(TangramTemplateModuleConfig config,M m);
}
