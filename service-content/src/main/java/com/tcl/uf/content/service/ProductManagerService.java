package com.tcl.uf.content.service;


import com.tcl.commonservice.service.vo.OfficialMallProductCategoryReponseDataVo;
import com.tcl.commonservice.service.vo.OfficialMallProductReponseDataVo;
import com.tcl.uf.common.base.ex.ProcessControlException;

public interface ProductManagerService {

    OfficialMallProductReponseDataVo findProductList(String nowPage, String pageShow, String terminalType, String buyState, String productType, String productName, String productNo, String orderBy, String platformUuid,String categoryUuid)throws ProcessControlException;

    OfficialMallProductCategoryReponseDataVo findIntegralMallCategoryList(String categoryUuid)throws ProcessControlException;

}
