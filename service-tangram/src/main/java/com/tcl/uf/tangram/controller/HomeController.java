package com.tcl.uf.tangram.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.uf.tangram.dto.ServerResponse;
import com.tcl.uf.tangram.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yxlong
 * @version 1.0
 * 商城首页
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    public HomeService homeService;

    @GetMapping("/queryHomeData")
    public ServerResponse queryHomeData(@RequestParam("reqCode") String reqCode,
                                                @RequestParam("uiType")String uiType){
        String resultStr = homeService.queryHomeData(reqCode,uiType);
        return ServerResponse.ok(JSON.parseObject(resultStr));
    }

    /**
     * 点击分类tab返回商品tangram数据
     * @param reqCode
     * @param goodsgroupUuid
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/queryProductByGroup")
    public ServerResponse<String> queryProductByGroup(@RequestParam("reqCode") String reqCode,
                                                        @RequestParam("goodsgroupUuid")String goodsgroupUuid,
                                                        @RequestParam(value = "pageNo",defaultValue = "1")String pageNo,
                                                        @RequestParam(value = "pageSize",defaultValue = "10") String pageSize ){
        String result = homeService.queryGroupProductContents(reqCode,goodsgroupUuid, pageNo,pageSize);
        return ServerResponse.ok(JSON.parseObject(result));

    }

    /**
     * 分页返回商品数据
     * @param reqCode
     * @param goodsgroupUuid
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/queryProductByPage")
    public ServerResponse<String> queryProductByPage(@RequestParam("reqCode") String reqCode,
                                                      @RequestParam("goodsgroupUuid")String goodsgroupUuid,
                                                      @RequestParam(value = "pageNo",defaultValue = "1")String pageNo,
                                                      @RequestParam(value = "pageSize",defaultValue = "10") String pageSize ){
        //String result = homeService.queryProductModulesContents(goodsgroupUuid, pageNo,pageSize);
        //组装成items的json格式
        //System.out.println(result);
        Map<String, Object> resMap = new HashMap<>(16);
        String result = homeService.queryGroupProductContents("1012",goodsgroupUuid,pageNo,pageSize);
        JSONObject obj = JSONObject.parseObject(result);
        JSONArray items = obj.getJSONArray("cards").getJSONObject(0).getJSONArray("items");
        resMap.put("items",items);
        return ServerResponse.ok(resMap);
    }

}
