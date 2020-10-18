package com.tcl.uf.member.controller;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.member.service.LinkConfigureService;
import com.tcl.uf.member.vo.LinkConfigureVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: PolicyAgreementController
 * @Description: 政策协议管理控制器(隐私政策、服务协议和账户注册协议)
 * @date 2020/8/26 16:51
 */

@Api(value = "政策协议管理控制器")
@RestController
@RequestMapping("/link/configure")
public class LinkConfigureController {

    private static final Logger log = LoggerFactory.getLogger(LinkConfigureController.class);

    @Autowired
    private LinkConfigureService linkConfigureService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询政策协议管理控制器(隐私政策、服务协议和账户注册协议)配置", notes="查询政策协议管理控制器(隐私政策、服务协议和账户注册协议)配置",httpMethod = "GET")
    public ResponseData<List<LinkConfigureVo>> link(HttpServletRequest request, @RequestParam(value = "type", required = false) String type)
    {
        List<LinkConfigureVo> linkConfigureVo;
        try {
            linkConfigureVo = linkConfigureService.findLinkConfigure(type);
        } catch (Exception e) {
            log.error("查询发生异常",e);
            return new ResponseData<>(0,"查询失败","query failed");
        }
        return new ResponseData<List<LinkConfigureVo>>(linkConfigureVo);
    }
}
