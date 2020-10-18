package com.tcl.commonservice.service;
import com.tcl.commonservice.service.vo.IntegralShopGoodsFo;
import com.tcl.commonservice.service.vo.IntegralShopOrderFo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-shop")
public interface ShopService {

    /**
     * 积分商城商品列表
     *
     * @param
     * @param condition
     * @param orderstr
     * @param cf
     * @return
     */
    @RequestMapping("/shopgoods/list")
    public ListWithPage<IntegralShopGoodsFo> getGoodslist(@RequestParam(value = "page") Integer page,
                                                          @RequestParam(value = "size") Integer size,
                                                          @RequestParam(value = "condition") String condition,
                                                          @RequestParam(value = "orderstr") String orderstr,
                                                          @RequestParam(value = "cf") Integer cf);

    /**
     * 订单列表
     *
     * @param page
     * @param size
     * @param ssoid
     * @param status
     * @param cf
     * @return
     */
    @RequestMapping(path = "/shoporder/IntegralList")
    ListWithPage<IntegralShopOrderFo> getShopOrderlist(@RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size,
                                                      @RequestParam("ssoid") String ssoid,
                                                      @RequestParam("status") Integer status,
                                                      @RequestParam("cf") Integer cf);

    /**
     * 检查订单自动完成
     *
     * @return
     */
    @RequestMapping("/shoporder/chkfinish")
    Integer finishOrder();
}
