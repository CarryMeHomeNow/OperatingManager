package com.tcl.uf.version.controller;


import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.version.dto.ExternalStatisticsParams;
import com.tcl.uf.version.service.ExternalStatisticsService;
import com.tcl.uf.version.vo.ExternalStatisticsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(description = "APP应用版本概括主页")
@RestController
@RequestMapping("/external/statistics")
public class ExternalStatisticsController {

    private static final Logger log = LoggerFactory.getLogger(ExternalStatisticsController.class);

    @Autowired
    private ExternalStatisticsService externalStatisticsService;

    @ApiOperation(value = "应用版本统计列表", notes = "应用版本统计列表", httpMethod = "POST")
    @PostMapping(value = "/back/list")
    public ResponseData<ListWithPage<ExternalStatisticsVo>> externalVersionList(HttpServletRequest request, @RequestBody ExternalStatisticsParams params) {
        ListWithPage<ExternalStatisticsVo> list;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = externalStatisticsService.statList(pageable, params);
        } catch (Exception e) {
            log.error("应用版本统计列表查询失败", e);
            return new ResponseData<>(0, "查询失败", "");
        }
        return new ResponseData<>(list);
    }

}
