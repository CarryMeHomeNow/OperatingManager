package com.tcl.uf.version.controller;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.version.dto.ScoreAndRemarkDto;
import com.tcl.uf.version.service.ScoreAndRemarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "用户评分与反馈")
@RestController
@RequestMapping("remark")
public class ScoreAndRemarkController {

    @Autowired
    private ScoreAndRemarkService scoreAndRemarkService;

    @ApiOperation(value = "app用户评分与反馈",notes = "app用户评分与反馈",httpMethod = "POST")
    @PostMapping("/app")
    public ResponseData addScoreAndRemark(@RequestBody ScoreAndRemarkDto scoreAndRemarkDto, HttpServletRequest httpServletRequest) {
        String scoreUrl = scoreAndRemarkService.addScoreAndRemark(scoreAndRemarkDto, httpServletRequest);
        return new ResponseData(scoreUrl);
    }
}
