package com.tcl.uf.shop.controller;

import com.alibaba.fastjson.JSON;
import com.tcl.commondb.shop.model.IntegralShopOrderModel;
import com.tcl.commondb.shop.repository.IntegralShopOrderRepository;
import com.tcl.commonservice.service.PointsService;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.shop.consts.ShopConstants;
import com.tcl.uf.shop.service.IntegralShopOrderService;
import com.tcl.uf.shop.vo.IntegralShopOrderFo;
import com.tcl.uf.shop.vo.IntegralShopOrderVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/shoporder")
@ApiModel(description = "订单管理")
public class IntegralShopOrderController extends AbstractBaseController {
    private static final Logger logger = LoggerFactory.getLogger(IntegralShopOrderController.class);
    @Autowired
    IntegralShopOrderService integralShopOrderService;
    @Autowired
    IntegralShopOrderRepository integralShopOrderRepository;
    @Autowired
    public HttpServletRequest servletRequest;

    @GetMapping(path = "/back/list")
    @ApiOperation(value = "订单列表信息", notes = "订单列表信息", httpMethod = "GET")
    public ResponseData<ListWithPage<IntegralShopOrderVo>> findlist(HttpServletRequest request,
			@RequestParam(value ="kw",required = false,defaultValue = "") String kw,
			@RequestParam(value ="stt",required = false,defaultValue = "") Integer stt,
			@RequestParam(value ="startday",required = false,defaultValue = "") String startday,
			@RequestParam(value ="endday",required = false,defaultValue = "") String endday) throws JMException {

        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            ListWithPage<IntegralShopOrderVo> datas = integralShopOrderService.findListbykw(kw, stt, startday, endday, pageable);
            return new ResponseData<>(datas);

        } catch (Exception e) {
            logger.error("查询列表失败:{}", e.getMessage());
			throw new JMException("获取列表失败");
        }
    }

    @GetMapping(path = "/list")
    @ApiOperation(value = "商品列表", notes = "前端订单列表", httpMethod = "GET")
    public ResponseData<ListWithPage<IntegralShopOrderFo>> goodslist(HttpServletRequest request,
             @RequestParam(value ="ssoid",required = false,defaultValue = "ssoid") String ssoid,
             @RequestParam(value ="status",required = false,defaultValue = "") Integer status) throws JMException{

        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            ListWithPage<IntegralShopOrderFo> datas = integralShopOrderService.findList(ssoid,status, pageable);
            return new ResponseData<>(datas);
        } catch (Exception e) {
            logger.error("获取订单列表发生异常:{}", e.getMessage());
			throw new JMException("获取列表失败");
        }
    }

    @PostMapping(path = "/back/expresssn")
    @ApiOperation(value = "更新物流单号", notes = "更新物流单号", httpMethod = "POST")
    public ResponseData<Object> updateexpress(HttpServletRequest request,
                                              @RequestParam(value = "expreno", required = true) String expreno,
                                              @RequestParam(value = "id", required = true, defaultValue = "0") Long id) {
        if (id == 0) {
            return new ResponseData<>(0, ShopConstants.ORDER_TIP_NOOREDERID, "");
        }
        Integer ret = integralShopOrderService.expresssn(id, expreno);
        if (ret > 0)
            return new ResponseData<>(1, "更新物流成功", "");
        else
            return new ResponseData<>(0, "更新物流失败", "");
    }

    @GetMapping(path = "/back/detail")
    @ApiOperation(value = "后端订单详情接口", notes = "根据id订单详情", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", dataType = "Long")})
    public ResponseData<IntegralShopOrderVo> findDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value ="id",required = false,defaultValue = "1") Long id) throws JMException {
        IntegralShopOrderVo integralShopOrderVo;
        try {
            integralShopOrderVo = integralShopOrderService.findOne(id);
            if (integralShopOrderVo == null) {
				throw new JMException("查无数据");
            }
        } catch (Exception e) {
            logger.error("查询详情失败:{}", e.getMessage());
			throw new JMException("查询详情失败");
        }
        return new ResponseData<>(integralShopOrderVo);
    }

    @GetMapping(path = "/detail")
    @ApiOperation(value = "前端订单详情接口", notes = "根据id订单详情", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", dataType = "Long")})
    public ResponseData<IntegralShopOrderFo> getDetail(HttpServletRequest request, HttpServletResponse response,
           @RequestParam(value ="orderid",required = false,defaultValue = "0") Long orderid) throws JMException  {
        if (orderid == 0) {
            return new ResponseData<>(0, ShopConstants.ORDER_TIP_NOOREDERID, "");
        }
        IntegralShopOrderFo shopOrderFo;
        try {
            shopOrderFo = integralShopOrderService.getOrderdetail(orderid);
            if (shopOrderFo == null) {
				throw new JMException("查无数据");
            }
        } catch (Exception e) {
            logger.error("查询详情失败:{}", e.getMessage());
			throw new JMException("查询详情失败");
        }
        return new ResponseData<>(shopOrderFo);
    }

    @GetMapping(path = "/updatestt")
    @ApiOperation(value = "前端更新订单状态", notes = "更新状态", httpMethod = "GET")
    public ResponseData<Object> updatestatus(HttpServletRequest request,
         @RequestParam(value = "stt", required = true) Integer stt,
         @RequestParam(value = "orderid", required = true,defaultValue = "0") Long orderid) throws JMException {

        if (orderid == 0) {
			throw new JMException(ShopConstants.ORDER_TIP_NOOREDERID);
        }
        IntegralShopOrderModel cardlistModel = new IntegralShopOrderModel();
        if (stt.equals(ShopConstants.ORDER_STATUS_SUCCESSED) || stt.equals(ShopConstants.ORDER_STATUS_CLOSED)) {
            cardlistModel = integralShopOrderService.updateStatus(orderid, stt);
        }
        String retstr = null;
        try {
            if (cardlistModel.getStatus().equals(ShopConstants.ORDER_STATUS_SUCCESSED)) {
                retstr = "用户确认收货";
            }
            if (cardlistModel.getStatus().equals(ShopConstants.ORDER_STATUS_CLOSED)) {
                retstr = "用户取消，交易关闭";
            }
        } catch (NullPointerException e) {
			throw new JMException("未找到修改数据对象");
        }
        return new ResponseData<>(1, retstr, "");
    }

    @PostMapping(path = "/exchange")
    @ApiOperation(value = "保存订单", notes = "积分兑换商品订单", httpMethod = "POST")
    public ResponseData<Object> saveOrder(HttpServletRequest request, HttpServletResponse response,
		   @RequestParam(value ="cf",required = true,defaultValue = "1") Integer cf,
		   @RequestParam(value ="goodsid",required = true,defaultValue = "0") Long goodsid,
		   @RequestParam(value ="total",required = true,defaultValue = "0") Integer total,
		   @RequestParam(value ="addressid",required = true,defaultValue = "0") Long addressid,
		   @RequestParam(value ="address",required = true,defaultValue = "") String address,
		   @RequestParam(value ="linkman",required = true,defaultValue = "") String linkman,
		   @RequestParam(value ="linkmobile",required = true,defaultValue = "") String linkmobile) throws JMException{

        if (addressid == 0) {
			throw new JMException("用户收件地址ID不能为空");
        }
        if (StringUtils.isEmpty(address)) {
			throw new JMException("收件地址信息不能为空");
        }
        if (StringUtils.isEmpty(linkman)) {
			throw new JMException("联系人不能为空");
        }
        if (StringUtils.isEmpty(linkmobile)) {
			throw new JMException("联系电话不能为空");
        }
        if (goodsid == 0) {
			throw new JMException("商品ID不能为空");
        }
        if (total == 0) {
			throw new JMException("商品兑换数据量不能为空");
        }

        String retstr = "兑换成功";
        try {
            TokenAppUserInfo info = getAppUserInfo(request);
            String token = info.getToken();

            if (info == null) {
                return new ResponseData<>(0, "用户信息为空", "failed");
            }
            Long ssoid = info.getAccountId();
            if (ssoid == null) {
                return new ResponseData<>(0, "用户SSOID为空", "failed");
            }
            Integer ret = integralShopOrderService.saveOrder(cf, goodsid, total, addressid, address, linkman, linkmobile, ssoid, token);

            if (ret != 1) {
                retstr = "兑换失败";
            }
            return new ResponseData(retstr);

        } catch (Exception e) {
            logger.error("兑换商品订单发生异常:{}", e.getMessage());
			throw new JMException("兑换异常");
        }

    }

    //超过15个自然日,自动确认收货
    @GetMapping(path = "/chkfinish")
    @ApiOperation(value = "自动检测关闭订单完成", notes = "自动检测关闭订单完成", httpMethod = "POST")
    public Integer chkFinish(HttpServletRequest request) {
        Integer ret = integralShopOrderService.finishOrder();
        return ret;
    }

    @GetMapping(path = "/IntegralList")
    @ApiOperation(value = "商品列表", notes = "订单列表-Tangram", httpMethod = "GET")
    public ListWithPage<IntegralShopOrderFo> getShopOrderlist(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                                             @RequestParam(value = "size", required = true, defaultValue = "10") Integer size,
                                                             @RequestParam(value = "ssoid", required = false, defaultValue = "ssoid") String ssoid,
                                                             @RequestParam(value = "status", required = false, defaultValue = "") Integer status,
                                                             @RequestParam(value = "cf", required = false, defaultValue = "1") Integer cf) {

        try {
            servletRequest.setAttribute("page", page);
            servletRequest.setAttribute("size", size);
            Pageable pageable = PageUtils.getPageFromRequestParam(servletRequest);
            ListWithPage<IntegralShopOrderFo> datas = integralShopOrderService.findList(ssoid, status, pageable);
            return datas;
        } catch (Exception e) {
            logger.error("获取订单列表发生异常:{}", e.getMessage());
            return new ListWithPage<IntegralShopOrderFo>();
        }
    }
}
