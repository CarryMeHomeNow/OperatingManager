package com.tcl.uf.shop.service;

import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.shop.dto.IntegralShopGoodsDto;
import com.tcl.uf.shop.vo.IntegralShopGoodsFo;
import com.tcl.uf.shop.vo.IntegralShopGoodsVo;
import org.springframework.data.domain.Pageable;

public interface IntegralShopGoodsService {

	void createIntegralShopGoods(IntegralShopGoodsDto paramBody);
	String updateIntegralShopGoods(IntegralShopGoodsDto paramBody);
	IntegralShopGoodsVo findOne(Long id);
	Integer updateStatus(Long[] ids, Integer stt);
	Integer updateOrder(Long id, Integer sort);
	ListWithPage<IntegralShopGoodsFo> findList(String condition, String orderstr, String token,Pageable pageable);
	ListWithPage<IntegralShopGoodsVo> findListbykw(String keyword,Long bid,Integer status,Pageable pageable);

	Integer delgoodsimg(Long id);
}
