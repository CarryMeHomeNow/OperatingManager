package com.tcl.uf.tangram.controller;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.tangram.dto.ServerResponse;
import com.tcl.uf.tangram.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc : 搜索页面接口
 * @Author yxlong
 * @Date 2020/9/5 12:39 下午
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @GetMapping("/getSearchPageData")
    public ServerResponse<String> getSearchPageData(@RequestParam("reqCode") String reqCode,
                                                    @RequestParam("uiType")String uiType){

        String result = searchService.getSearchPageData(reqCode,uiType);
        return ServerResponse.ok(JSON.parseObject(result));
    }
}
