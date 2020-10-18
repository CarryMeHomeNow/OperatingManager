package com.tcl.commonservice.service;

import com.tcl.commonservice.service.dto.AppArticleLikeParams;
import com.tcl.commonservice.service.dto.AppArticleListParams;
import com.tcl.commonservice.service.dto.AppArticleRandParams;
import com.tcl.commonservice.service.vo.*;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("service-content")
public interface ContentService {

    /**
     * 查询官网优惠券列表信息
     * @param currentPage       当前页
     * @param pageSize          页大小
     * @param couponTypeName    优惠券名称
     * @param collectType       是否公开领取(1是 0否)
     * @param cansend           是否允许转赠(1是 0否)
     * @param startTime         生效时间
     * @param endTime           失效时间
     * @return OfficialMallCouponReponseDataVo
     */
    @RequestMapping("/coupon/official/mall/list")
    ResponseData<OfficialMallCouponReponseDataVo> findCouponList(@RequestParam("currentPage") String currentPage,
                                                                 @RequestParam("pageSize") String pageSize,
                                                                 @RequestParam(value = "couponTypeName", required = false) String couponTypeName,
                                                                 @RequestParam(value = "collectType", required = false) String collectType,
                                                                 @RequestParam(value = "cansend", required = false) String cansend,
                                                                 @RequestParam(value = "startTime", required = false) String startTime,
                                                                 @RequestParam(value = "endTime", required = false) String endTime);
    /**
     * 查询官网商品列表
     * @param nowPage           当前页
     * @param pageShow          页大小
     * @param terminalType      终端类型(01 pc，02手机，03电视)
     * @param buyState          是否可售(1可售，0不可售)
     * @param productType       商品类型
     * @param productName       商品名称
     * @param productNo         商品sku
     * @param orderBy           排序字段(默认按uuid排)
     * @param platformUuid      商城类型(区分查询官网或者内购开单的数据)
     * @return OfficialMallProductReponseDataVo
     */
    @RequestMapping("/product/official/mall/list")
    ResponseData<OfficialMallProductReponseDataVo> findProductList(@RequestParam("nowPage") String nowPage,
                                                                   @RequestParam("pageShow") String pageShow,
                                                                   @RequestParam(value = "terminalType", required = false) String terminalType,
                                                                   @RequestParam(value = "buyState", required = false) String buyState,
                                                                   @RequestParam(value = "productType", required = false) String productType,
                                                                   @RequestParam(value = "productName", required = false) String productName,
                                                                   @RequestParam(value = "productNo", required = false) String productNo,
                                                                   @RequestParam(value = "orderBy", required = false) String orderBy,
                                                                   @RequestParam(value = "platformUuid", required = false) String platformUuid,
                                                                   @RequestParam(value = "categoryUuid", required = false) String categoryUuid);

    /**
     * 发放优惠券,支持批量(添加卡券到用户卡包)
     * @param ssoids           用户SSOID，多个用户以逗号“,”隔开
     * @param couponTypeUuid   优惠券类型UUID
     * @return String
     */
    @RequestMapping("/coupon/official/mall/sendCoupon")
    ResponseData<String> sendCoupon(@RequestParam("ssoids") String ssoids, @RequestParam("couponTypeUuid") String couponTypeUuid);

    /**
     * 查询商品分类信息
     * @param categoryUuid     树形，第一次查root节点时不用传参，默认为systemUuid，即root节点
     * @return String
     */
    @RequestMapping("/product/official/mall/category/list")
    ResponseData<OfficialMallProductCategoryReponseDataVo> findIntegralMallCategoryList(@RequestParam(value = "categoryUuid", required = false) String categoryUuid);

    /**
     * 文章板块列表
     * @return
     */
    @RequestMapping("/app/article/section/list")
    ResponseData<List<ArticleSectionVo>> appSectionList();

    /**
     * 文章列表接口
     * @param params
     * @return
     */
    @PostMapping(value = "/app/article/list")
    ResponseData<ListWithPage<AppArticleListVo>> appArticleList(@RequestBody AppArticleListParams params);

    /**
     * 文章推荐列表接口
     * @param appArticleRandParams
     * @return
     */
    @RequestMapping(value = "/app/article/rand")
    ResponseData<List<AppArticleListVo>> appArticleRand(@RequestBody AppArticleRandParams appArticleRandParams);

    /**
     * 文章详情
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/app/article/{articleId}")
    ResponseData<AppArticleDetailsVo> appArticleDetail(@RequestParam( "articleId") Long articleId);

    /**
     * 文章点赞
     * @param appArticleLikeParams
     * @return
     */
    @RequestMapping(value = "/app/article/like")
    ResponseData<Object> like(@RequestBody AppArticleLikeParams appArticleLikeParams);

    /**
     * 文章取消点赞
     * @param
     * @param appArticleLikeParams
     * @return
     */
    @RequestMapping(value = "/app/article/cancel/like")
    ResponseData<Object> cancelLike(@RequestBody AppArticleLikeParams appArticleLikeParams);

    /**
     * 定时器执行添加浏览数
     * @return
     */
    @RequestMapping(value = "/common/article/addBrowseNum", method = RequestMethod.GET)
    ResponseData<Object> addBrowseNum();
}
