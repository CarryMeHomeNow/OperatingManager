package com.tcl.uf.tangram.controller;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.tangram.dto.ServerResponse;
import com.tcl.uf.tangram.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Desc :
 * @Author yxlong
 * @Date 2020/8/26 4:30 下午
 */
@RestController
@RequestMapping("/product")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(method = RequestMethod.GET,value = "/queryProductGroup")
    public ServerResponse<List<Map<String, Object>>> queryProductGroup(){
        List<Map<String, Object>> resMap = productCategoryService.getProductCategory();
        return ServerResponse.ok(resMap);
    }

    @GetMapping(value = "/queryProductGroupDetails")
    public ServerResponse queryGroupDetails(@RequestParam("reqCode")String reqCode,
                                                    @RequestParam("categoryName")String categoryName){

        String resultStr = productCategoryService.getCategoryTangramData(categoryName,reqCode);
        return ServerResponse.ok(JSON.parseObject(resultStr));
    }

}
