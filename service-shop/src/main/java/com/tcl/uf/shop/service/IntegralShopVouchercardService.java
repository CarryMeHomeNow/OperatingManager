package com.tcl.uf.shop.service;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.shop.model.IntegralShopVouchercardModel;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.shop.vo.IntegralShopVouchercardVo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface IntegralShopVouchercardService {
	IntegralShopVouchercardVo findOne(Long id);
	IntegralShopVouchercardModel updaterow(Long id, Integer stt);
	ListWithPage<IntegralShopVouchercardVo> findList(String gno,Pageable pageable);
	ResponseData<Integer> uploadVouchercard(MultipartFile file, String pno);
	JSONObject getcardstatis(String pno);


}
