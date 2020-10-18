package com.tcl.uf.tangram.controller;

import com.alibaba.fastjson.JSON;
import com.tcl.commonservice.service.ContentService;
import com.tcl.commonservice.service.MemberService;
import com.tcl.commonservice.service.ShopService;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.tangram.dto.ServerResponse;
import com.tcl.uf.tangram.service.*;
import com.tcl.uf.tangram.vo.TangramRequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @author zhongfk on 2020/8/19.
 * @version 1.0
 */
@Controller
@RequestMapping("/server")
public class ServerTabController extends AbstractBaseController {
    private Logger log = LoggerFactory.getLogger(ServerTabController.class);
    @Autowired
    public ContentService contentService;
    @Autowired
    public ServerTabService serverTabService;
    @Autowired
    public ArticleDetailService articleDetailService;
    @Autowired
    public MemberService memberService;
    @Autowired
    public ServerMemberService serverMemberService;
    @Autowired
    public RightSetService rightSetService;
    @Autowired
    public IntegralService integralService;
    @Autowired
    public PointTaskService pointTaskService;
    @Autowired
    public IntegralShopService integralShopService;
    @Autowired
    public ShopService shopService;
    @Autowired
    public CouponsService couponsService;
    @Autowired
    public IntegralDetailService integralDetailService;
    @Autowired
    public MemberPlayService memberPlayService;
    @Autowired
    public HttpServletRequest request;

    /**
     * 服务首页
     * @param
     * @return
     * @ps needLogin false
     */
    @GetMapping("/index")
    @ResponseBody
    public ServerResponse queryServerTab(@RequestParam @NotNull(message = "page不能为空") Integer page,
                                         @RequestParam @NotNull(message = "size不能为空") Integer size,
                                         @RequestParam @NotNull(message = "reqCode不能为空") String reqCode
    ) {
        TokenAppUserInfo appUserInfo = getAppUserInfo(request);
        return ServerResponse.ok(JSON.parse(serverTabService.queryAll(page, size, reqCode, appUserInfo)));
    }

    /**
     * 根据文章板块查询文章列表
     * @param
     * @return
     * @ps needLogin false
     */
    @GetMapping("/articleList")
    @ResponseBody
    public ServerResponse queryArticleBySectionId(@RequestParam @NotNull(message = "page不能为空") Integer page,
                                                  @RequestParam @NotNull(message = "size不能为空") Integer size,
                                                  @RequestParam @NotNull(message = "sectionId不能为空") Long sectionId
    ) {
        return ServerResponse.ok(JSON.parse(serverTabService.queryArticleBySectionId(page,size,sectionId)));
    }

    /**
     * 文章详情
     * @param
     * @return
     * @ps needLogin false
     */
    @GetMapping("/article/detail")
    @ResponseBody
    public ServerResponse articleDetail(@RequestParam String reqCode,@RequestParam Long articleId,@RequestParam Long sectionId) {
        return ServerResponse.ok(JSON.parse(articleDetailService.articleDetail(reqCode,articleId,sectionId)));
    }

    /**
     * 会员等级
     * @param request
     * @param paramDTO
     * @return
     */
    @PostMapping("/member/level")
    @ResponseBody
    public String getMemberLevel(HttpServletRequest request, @RequestBody TangramRequestParam paramDTO) {
        TokenAppUserInfo appUserInfo = getAppUserInfo(request);
        return serverMemberService.getMemberLevel(paramDTO, request);
    }

    /**
     * 会员权益
     * @param paramDTO
     * @return
     */
    @PostMapping("/member/rightSet")
    @ResponseBody
    public String getMemberRightSet(@RequestBody TangramRequestParam paramDTO) {
        return rightSetService.getMemberRightSet(paramDTO);
    }

    /**
     * 积分活动
     * @param paramDTO
     * @return
     */
    @PostMapping("/integral")
    @ResponseBody
    public String getIntegralActivity(@RequestBody TangramRequestParam paramDTO) {
        return integralService.getIntegralActivity(paramDTO);
    }

    /**
     * 积分任务
     * @param paramDTO
     * @return
     */
    @PostMapping("/pointTask")
    @ResponseBody
    public String getPointTask(@RequestBody TangramRequestParam paramDTO) {
        return pointTaskService.getPointTask(paramDTO);
    }

    /**
     * 积分详情
     * @param paramDTO
     * @return
     */
    @PostMapping("/taskDetail")
    @ResponseBody
    public String getTaskDetail(@RequestBody TangramRequestParam paramDTO) {
        return pointTaskService.getTaskDetail(paramDTO);
    }

    /**
     * 积分商城
     * @param paramDTO
     * @return
     */
    @PostMapping("/integralShop")
    @ResponseBody
    public String getIntegralShop(@RequestBody TangramRequestParam paramDTO) {
        return integralShopService.getIntegralShop(paramDTO);
    }

    /**
     * 我的兑换
     * @param paramDTO
     * @return
     */
    @PostMapping("/order")
    @ResponseBody
    public String getOrderList(@RequestBody TangramRequestParam paramDTO) {
        return integralShopService.getOrderList(paramDTO);
    }

    /**
     * 我的券包
     * @param paramDTO
     * @return
     */
    @PostMapping("/coupons")
    @ResponseBody
    public String getCoupons(@RequestBody TangramRequestParam paramDTO) {
        return couponsService.getCoupons(paramDTO);
    }

    /**
     * 积分明细
     * @param paramDTO
     * @return
     */
    @PostMapping("/integralDetail")
    @ResponseBody
    public String getIntegralDetail(@RequestBody TangramRequestParam paramDTO) {
        TokenAppUserInfo appUserInfo = getAppUserInfo(request);
        paramDTO.setAccountId(appUserInfo.getAccountId());
        return integralDetailService.getIntegralDetail(paramDTO);
    }

    /**
     * 会员玩法
     *
     * @param paramDTO
     * @return
     */
    @PostMapping("/memberPlay")
    @ResponseBody
    public String getMemberPlay(@RequestBody TangramRequestParam paramDTO) {
        TokenAppUserInfo appUserInfo = getAppUserInfo(request);
        paramDTO.setAccountId(/*appUserInfo.getAccountId()*/Long.valueOf("1282580499042140200"));
        return memberPlayService.getMemberPlay(paramDTO);
    }

}
