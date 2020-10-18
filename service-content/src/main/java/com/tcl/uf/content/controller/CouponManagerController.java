package com.tcl.uf.content.controller;

import com.tcl.commonservice.service.vo.OfficialMallCouponReponseDataVo;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.ProcessControlException;
import com.tcl.uf.content.service.CouponManagerService;
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
 * @Description: 优惠券管理(官网、积分商城)
 * @date 2020/8/11 16:13
 */
@Api(description = "优惠券管理(官网)")
@RestController
@RequestMapping("/coupon")
public class CouponManagerController {

    @Autowired
    private CouponManagerService couponManagerService;

    @RequestMapping(value = "/official/mall/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询官方商城商品列表" , notes="查询官方商城商品列表",httpMethod = "GET")
    public ResponseData<OfficialMallCouponReponseDataVo> findList(HttpServletRequest request, HttpServletResponse response,
                                                                  @RequestParam("currentPage") String currentPage,
                                                                  @RequestParam("pageSize") String pageSize,
                                                                  @RequestParam(value="couponTypeName",required = false) String couponTypeName,
                                                                  @RequestParam(value="collectType",required = false) String collectType,
                                                                  @RequestParam(value="cansend",required = false) String cansend,
                                                                  @RequestParam(value="startTime",required = false) String startTime,
                                                                  @RequestParam(value="endTime",required = false) String endTime) {
        OfficialMallCouponReponseDataVo officialMallReponseVo = null;
        try{
            officialMallReponseVo = couponManagerService.findCouponList(currentPage,pageSize,couponTypeName,collectType,cansend,startTime,endTime);
        }catch (ProcessControlException e){
            return new ResponseData<OfficialMallCouponReponseDataVo>(0,e.getMessage(),"");
        }
        return new ResponseData<OfficialMallCouponReponseDataVo>(officialMallReponseVo);
    }

    @RequestMapping(value = "/official/mall/sendCoupon", method = RequestMethod.GET)
    @ApiOperation(value = "后台优惠券发放接口" , notes="后台优惠券发放接口",httpMethod = "GET")
    public ResponseData<String> sendCoupon(HttpServletRequest request, HttpServletResponse response,
                                                                  @RequestParam("ssoids") String ssoids,
                                                                  @RequestParam("couponTypeUuid") String couponTypeUuid) {
        String transId = null;
        try{
            transId = couponManagerService.sendCoupon(ssoids,couponTypeUuid);
        }catch (ProcessControlException e){
            return new ResponseData<String>(0,e.getMessage(),"");
        }
        return new ResponseData<String>(transId);
    }
}
