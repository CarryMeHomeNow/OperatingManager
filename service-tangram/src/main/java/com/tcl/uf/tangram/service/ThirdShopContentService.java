package com.tcl.uf.tangram.service;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Desc : 第三方商城服务接口
 * @Author yxlong
 * @Date 2020/8/26 11:21 上午
 */
@FeignClient(url = "https://testwap.tclo2o.cn",name = "shop-service")
public interface ThirdShopContentService {

    /**
     * 第三方获取商城首页数据接口
     * @param uiType
     * @return
     */
    @RequestMapping(value = "/rest/v2/front/shopContent/getIndexPageInfo",
            method = RequestMethod.POST,headers = {"platform=platform_tcl_shop","storeUuid=tclplus","t-id=TCL","terminalType=02",
            "Content-Type=application/json;charset=UTF-8","Accept-Encoding=gzip, deflate, br"})
    String getIndexPageInfo(@RequestParam String uiType);

    /**
     * 第三方获取商品分组的具体商品数据接口
     * @param cateGroupUuid
     * @param nowPage
     * @param pageShow
     * @param sortBy
     * @param sortType
     * @return
     */
    @RequestMapping(value = "rest/v2/product/category/groups/searchProducts",
            method = RequestMethod.POST,headers = {"platform=platform_tcl_shop",
            "storeUuid=tclplus","t-id=TCL","terminalType=02","Content-Type: application/x-www-form-urlencoded"})
    String searchProductsByGroupId(@RequestParam("cateGroupUuid") String cateGroupUuid,
                                   @RequestParam("nowPage") String nowPage,@RequestParam("pageShow") String pageShow,
                                   @RequestParam("sortBy") String sortBy,@RequestParam("sortType") String sortType);

    /**
     * 第三方商城接口，获取产品页商品分类信息
     * @param uiType 终端类型
     * @param pageType 页面类型 产品页：productGroup_page，
     * @return
     */
    @RequestMapping(value = "/rest/v2/front/shopContent/getPageManageByPageType",
            method = RequestMethod.POST,headers = {"platform=platform_tcl_shop",
            "storeUuid=tclplus","t-id=TCL","terminalType=02","Content-Type: application/x-www-form-urlencoded"})
    String getProductCategoryDetails(@RequestParam("uiType") String uiType,@RequestParam("pageType")String pageType);

    /**
     * 第三方商城接口，根据页面类型获取页面配置数据
     * @param pageType 页面类型 具体类型参考 PageTypeConstant类
     * @param uiType 终端页面类型 miniProgram（小程序端）、APP（APP端）、WAP（wap端）
     * @return 返回接口数据
     */
    @RequestMapping(value = "/rest/v2/front/shopContent/getPageManageByPageType",
            method = RequestMethod.POST,headers = {"platform=platform_tcl_shop",
            "storeUuid=tclplus","t-id=TCL","terminalType=02"})
    String getPageManageByPageType(@RequestParam("pageType") String pageType,
                                   @RequestParam("uiType")String uiType);

    @RequestMapping(value = "/rest/v2/front/product/queryKeyWord",
            method = RequestMethod.GET,headers = {"platform=platform_tcl_shop",
            "storeUuid=tclplus","t-id=TCL","terminalType=02"})
    String getHotSearchKeyword(@RequestParam("type") String type);


}
