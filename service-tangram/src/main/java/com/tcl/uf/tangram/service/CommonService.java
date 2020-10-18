package com.tcl.uf.tangram.service;

import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import com.tcl.commonservice.service.vo.AppArticleListVo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfk on 2020/8/14.
 * @version 1.0
 */
public interface CommonService {
    /**
     * 查询单个模板
     * @param templateId
     * @return
     */
    String findTemplateById(String templateId);

    /**
     * 查询所有模板
     * @return <TangramTemplateConfig>
     */
    List<TangramTemplateConfig> queryAllTemplate();

    /**
     * 文章列表公用组装
     * @return
     */
    Map<String,Object> parseArticleList(Map<String, Object> dataMap);

    /**
     * 广告位数据组装
     * @param dataMap
     * @return
     */
    Map<String, Object> turnAdvert(Map<String, Object> dataMap);

    /**
     * 文章列表
     * @param articleList
     * @return
     */
   String turnArticle(ListWithPage<AppArticleListVo> articleList);

    /**
     * 处理商城接口数据
     * @param pageType
     * @param uiType
     * @return
     */
   String getPageManageByPageType(String pageType,String uiType);

    /**
     * 获取热门搜索接口
     * @param type 1 关键词 2 热门搜索
     * @return
     */
   String getHotSearchKeyword(String type);

}
