package com.tcl.uf.member.controller;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.member.service.AfterSaleService;
import com.tcl.uf.member.vo.AfterSaleConfigureVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: AfterSaleController
 * @Description: 售后工具管理模块
 * @date 2020/8/18 16:02
 */
@RestController
@Api(value  = "售后工具管理模块")
@RequestMapping(value = "/after/sale")
public class AfterSaleController {

    @Autowired
    private AfterSaleService afterSaleService;

    @ApiOperation(value = "售后工具配置列表信息",notes = "售后工具配置列表信息",httpMethod = "GET")
    @GetMapping("/configure/list")
    public ResponseData<List<AfterSaleConfigureVo>> findAfterSaleConfigureList() {
        List<AfterSaleConfigureVo> list = null;
        try{
            list = afterSaleService.findAfterSaleConfigureList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseData<List<AfterSaleConfigureVo>>(list);
    }

}
