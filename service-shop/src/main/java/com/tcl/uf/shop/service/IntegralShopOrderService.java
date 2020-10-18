package com.tcl.uf.shop.service;
import com.tcl.commondb.shop.model.IntegralShopOrderModel;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.shop.dto.IntegralShopOrderDto;
import com.tcl.uf.shop.vo.IntegralShopOrderVo;
import com.tcl.uf.shop.vo.IntegralShopOrderFo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntegralShopOrderService {

	void createIntegralShopOrder(IntegralShopOrderDto paramBody);
	String updateIntegralShopOrder(IntegralShopOrderDto paramBody);
    IntegralShopOrderVo findOne(Long id);
	IntegralShopOrderFo getOrderdetail(Long id);
	IntegralShopOrderModel updateStatus(Long id, Integer stt);
    Integer expresssn(Long id,String exno);

	ListWithPage<IntegralShopOrderVo> findListbykw(String keyword, Integer status, String startDay, String endDay, Pageable pageable);
	ListWithPage<IntegralShopOrderFo> findList(String ssoid, Integer status, Pageable pageable);

	Integer saveOrder(Integer cf, Long goodsid, Integer total, Long addressid, String address, String linkman, String linkmobile,Long ssoid, String token);
	Integer finishOrder();
}
