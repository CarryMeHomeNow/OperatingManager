package com.tcl.uf.tangram.service;

/**
 * @Desc :
 * @Author yxlong
 * @Date 2020/9/5 1:45 下午
 */
public interface SearchService {
    /**
     * 获取搜索页面配置数据
     * @param reqCode
     * @return
     */
    String getSearchPageData(String reqCode,String uiType);
    /**
     * 搜索没有数据时页面模板数据
     * @param reqCode
     * @return
     */
    String getNoResultData(String reqCode);
}
