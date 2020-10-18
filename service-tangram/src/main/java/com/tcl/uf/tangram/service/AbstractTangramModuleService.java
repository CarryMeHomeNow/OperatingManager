package com.tcl.uf.tangram.service;

import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.uf.tangram.vo.TangramRequestParam;

/**
 * @Desc : 组件处理接口，自定义的组件需实现该接口
 * @Author Mr.HePeng
 * @Date 2020/8/8 17:15
 */
public abstract class AbstractTangramModuleService<T, M> implements TangramModuleService<T, M> {

    /**
     * @param config
     * @param param
     * @return
     */
    @Override
    public T process(TangramTemplateModuleConfig config, TangramRequestParam param) {
        TangramDataService dataService = getDataServiceInstance(config);
        M m = (M) dataService.transData(config, dataService.queryData(config, param));
        return transData(m);
    }

    /**
     * 获取数据接口实例
     *
     * @param config 组件配置信息
     * @return 返回数据处理接口实例
     */
    public abstract TangramDataService getDataServiceInstance(TangramTemplateModuleConfig config);

}