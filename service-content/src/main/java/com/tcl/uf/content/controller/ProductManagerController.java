package com.tcl.uf.content.controller;

import com.tcl.commonservice.service.vo.OfficialMallProductCategoryReponseDataVo;
import com.tcl.commonservice.service.vo.OfficialMallProductReponseDataVo;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.ProcessControlException;
import com.tcl.uf.content.service.ProductManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author youyun.xu
 * @ClassName: ProductManagerController
 * @Description: 商品管理(官网、积分商城)
 * @date 2020/8/11 16:13
 */
@Api(description = "商品管理(官网、积分商城)")
@RestController
@RequestMapping("/product")
public class ProductManagerController {

    @Autowired
    private ProductManagerService productManagerService;

    @RequestMapping(value = "/official/mall/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询官方商城商品列表" , notes="查询官方商城商品列表",httpMethod = "GET")
    public ResponseData<OfficialMallProductReponseDataVo> findList(HttpServletRequest request, HttpServletResponse response,
                                                                   @RequestParam("nowPage") String nowPage,
                                                                   @RequestParam("pageShow") String pageShow,
                                                                   @RequestParam(value="terminalType",required = false) String terminalType,
                                                                   @RequestParam(value="buyState",required = false) String buyState,
                                                                   @RequestParam(value="productType",required = false) String productType,
                                                                   @RequestParam(value="productName",required = false) String productName,
                                                                   @RequestParam(value="productNo",required = false) String productNo,
                                                                   @RequestParam(value="orderBy",required = false) String orderBy,
                                                                   @RequestParam(value="platformUuid",required = false) String platformUuid,
                                                                   @RequestParam(value = "categoryUuid", required = false) String categoryUuid){
        OfficialMallProductReponseDataVo productReponseData;
        try{
            productReponseData = productManagerService.findProductList(nowPage,pageShow,terminalType,buyState,productType,productName,productNo,orderBy,platformUuid,categoryUuid);
        }catch (ProcessControlException e){
            return new ResponseData<OfficialMallProductReponseDataVo>(0,e.getMessage(),"");
        }
        return new ResponseData<OfficialMallProductReponseDataVo>(productReponseData);
    }

    @RequestMapping(value = "/official/mall/category/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询积分商城商品分类列表" , notes="树形，第一次查root节点时不用传参，默认为systemUuid，即root节点",httpMethod = "GET")
    public ResponseData<OfficialMallProductCategoryReponseDataVo> findIntegralMallCategoryList(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="categoryUuid",required = false) String categoryUuid){
        OfficialMallProductCategoryReponseDataVo productReponseData;
        try{
            productReponseData = productManagerService.findIntegralMallCategoryList(categoryUuid);
        }catch (ProcessControlException e){
            return new ResponseData<OfficialMallProductCategoryReponseDataVo>(0,e.getMessage(),"");
        }
        return new ResponseData<OfficialMallProductCategoryReponseDataVo>(productReponseData);
    }
}
