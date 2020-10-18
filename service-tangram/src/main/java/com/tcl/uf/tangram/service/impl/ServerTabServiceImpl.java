package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.tangram.repository.TangramTemplateConfigRepository;
import com.tcl.commonservice.service.AdvertService;
import com.tcl.commonservice.service.ContentService;
import com.tcl.commonservice.service.MemberService;
import com.tcl.commonservice.service.PointsService;
import com.tcl.commonservice.service.dto.AppArticleListParams;
import com.tcl.commonservice.service.vo.*;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.http.HttpClientUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.ServerTabService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import com.tcl.uf.tangram.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author zhongfk on 2020/8/19.
 * @version 1.0
 * @Desc 服务tab页
 */
@Service
public class ServerTabServiceImpl implements ServerTabService {
    Log log = LogFactory.getLog(ServerTabService.class);
    @Autowired
    public ContentService contentService;
    @Autowired
    public MemberService memberService;
    @Autowired
    public TangramTemplateConfigRepository tangramTemplateConfigRepository;
    @Autowired
    public CommonService commonService;
    @Autowired
    public AdvertService advertService;
    @Autowired
    public PointsService pointsService;

    public static final String poingURL = "https://account-dev.tcljd.com/point/point/findPointsFlow?accountId=";

    @Override
    public String queryAll(Integer page, Integer size, String reqCode, TokenAppUserInfo appUserInfo) {
        String tangramTemplate = commonService.findTemplateById(reqCode);
        //智能售后
        ResponseData<List<AfterSaleConfigureVo>> afterSaleConfigureList = memberService.findAfterSaleConfigureList();
        List<AfterSaleConfigureVo> afterSaleList = null == afterSaleConfigureList ? null : afterSaleConfigureList.getData();
        //会员等级
        MemberVo memberVo = memberService.queryMemberLevel();

        //文章板块列表（智能推荐分类）
        ResponseData<List<ArticleSectionVo>> articleSel = contentService.appSectionList();
        List<ArticleSectionVo> tabContents = null == articleSel ? null : articleSel.getData();
        if(tabContents == null){
            throw new JMException(1005,"查询文章板块失败");
        }
        //文章列表 默认选取板块一文章
        AppArticleListParams appArticleListParams = new AppArticleListParams();
        appArticleListParams.setSectionId(tabContents.get(0).getSectionId());
        appArticleListParams.setPage(page);
        appArticleListParams.setSize(size);
        ResponseData<ListWithPage<AppArticleListVo>> listWithPageResponseData = contentService.appArticleList(appArticleListParams);
        ListWithPage<AppArticleListVo> articleList = null == listWithPageResponseData ? null : listWithPageResponseData.getData();

        Map<String, Object> dataMap = new HashMap<>(16);
        //总积分
        try {
            String message = HttpClientUtils.sendGet(poingURL + "1282580499042140200");
            JSONObject object = JSON.parseObject(message);
            Integer score = (Integer) object.get("score");
            //积分
            dataMap.put("Integral", score);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //售后
        dataMap.put("afterSales", afterSaleList);
        //智能分类
        dataMap.put("tabContents", tabContents);
        //文章列表
        dataMap.put(TemplateEnums.ARTICLE_lIST.getValue(), articleList);
        dataMap.put(TemplateEnums.MEMBER_LEVEL.getValue(), memberVo);

        return parseDate(tangramTemplate, dataMap, appUserInfo);
    }

    /**
     * 查询板块下的文章列表
     * @param sectionId
     * @return
     */
    @Override
    public String queryArticleBySectionId(Integer page,Integer size,Long sectionId) {
        Map<String, Object> dataMap = new HashMap<>();
        //文章列表 默认选取板块一文章
        AppArticleListParams appArticleListParams = new AppArticleListParams();
        appArticleListParams.setSectionId(sectionId);
        appArticleListParams.setPage(page);
        appArticleListParams.setSize(size);
        ResponseData<ListWithPage<AppArticleListVo>> listWithPageResponseData = contentService.appArticleList(appArticleListParams);
        ListWithPage<AppArticleListVo> articleList = null == listWithPageResponseData ? null : listWithPageResponseData.getData();
        //文章列表
        return commonService.turnArticle(articleList);
    }

    public String parseDate(String tangramTemplate, Map<String, Object> dataMap, TokenAppUserInfo appUserInfo) {
        HashMap<String, Object> parseMap = new HashMap<>(16);
        //券包 兑换 活动..固定
        ActionParams actionParams = new ActionParams();
        actionParams.setActionType("jump");
        actionParams.setActionUrl("https://");
        parseMap.put("券包跳转", JSONObject.toJSONString(actionParams));

        ActionParams exchangeParams = new ActionParams();
        exchangeParams.setActionType("jump");
        exchangeParams.setActionUrl("https://");
        parseMap.put("兑换跳转", JSONObject.toJSONString(exchangeParams));

        ActionParams activeParams = new ActionParams();
        activeParams.setActionType("jump");
        activeParams.setActionUrl("https://");
        activeParams.setKeyWords("关键词");
        parseMap.put("活动跳转", JSONObject.toJSONString(activeParams));

        ActionParams taskParams = new ActionParams();
        taskParams.setActionType("jump");
        taskParams.setActionUrl("https://");
        taskParams.setCategoryId("https://");
        taskParams.setCategoryName("https://");
        parseMap.put("任务跳转", JSONObject.toJSONString(taskParams));

        ActionParams detailParams = new ActionParams();
        detailParams.setActionType("jump");
        detailParams.setActionUrl("https://");
        taskParams.setCategoryId("https://");
        taskParams.setCategoryName("https://www.baidu.com");
        parseMap.put("明细跳转", JSONObject.toJSONString(detailParams));

        if (dataMap.containsKey("tabContents")) {
            List<ArticleSectionVo> tabContents = (List<ArticleSectionVo>) dataMap.get("tabContents");
            List<TabContents> contents = new ArrayList<>();
            if (null == tabContents) {
                parseMap.put("文章板块列表", JSON.toJSONString(contents));
            } else {
                for (ArticleSectionVo vo : tabContents) {
                    TabContents tabContent = new TabContents();
                    tabContent.setTitleContent(vo.getSectionName());
                    tabContent.setSubtitleContent(vo.getSubTitle());
                    tabContent.setSelectionId(vo.getSectionId());
                    ActionParams params = new ActionParams();
                    params.setActionType("update");
                    params.setActionUrl("server/articleList?page=0&size=10?sectionId="+vo.getSectionId());
                    tabContent.setActionParams(params);
                    contents.add(tabContent);
                }
                parseMap.put("文章板块列表", JSON.toJSONString(contents));
            }
        }
        //文章列表
        Map<String, Object> articleListMap = commonService.parseArticleList(dataMap);
        parseMap.putAll(articleListMap);
        Map<String, Object> integral = parseIntegral(dataMap, appUserInfo);
        parseMap.putAll(integral);
        Map<String, Object> afterSale = parseAfterSale(dataMap);
        parseMap.putAll(afterSale);
        return TangramTemplateUtil.replaceModuleValue(tangramTemplate, parseMap);
    }

    /**
     * 会员等级 与签到天数
     * @param dataMap
     * @return
     */
    public Map<String, Object> parseIntegral(Map<String, Object> dataMap, TokenAppUserInfo appUserInfo) {
        //未登录 返回登录按钮
        if (appUserInfo == null) {
            return turnLogin();
        }
        ResponseData<List<SignInVo>> listResponseData = pointsService.querySignRecord();
        List<SignInVo> data = listResponseData == null ? null : listResponseData.getData();
        MemberVo memberVo = (MemberVo) dataMap.get(TemplateEnums.MEMBER_LEVEL.getValue());
        Map<String, Object> parseMap = new HashMap<>(16);
        IntegralVO integralVO = new IntegralVO();
        integralVO.setType("memberHead");
        VipTextParams vipTextParams = new VipTextParams();
        vipTextParams.setTextContent(memberVo == null ? "会员" : memberVo.getLevelName());
        vipTextParams.setVipLevel(memberVo == null ? "666" : memberVo.getLevel());
        integralVO.setVipTextParams(vipTextParams);
        VipPointParams vipPointParams = new VipPointParams();
        vipPointParams.setTextContent((Integer) dataMap.get("Integral"));
        integralVO.setVipPointParams(vipPointParams);
        SignParams signParams = new SignParams();
        signParams.setCurrentDay(Integer.valueOf(data.size()));
        signParams.setCurrenPoint(Integer.valueOf(data.size()) * 2);
        signParams.setTodaySign(true);
        integralVO.setSignParams(signParams);
        RulesParams rulesParams = new RulesParams();
        ActionParams params1 = new ActionParams();
        params1.setActionType("jump");
        params1.setActionUrl("http：//");
        rulesParams.setActionParams(params1);
        ActionParams params2 = new ActionParams();
        params2.setActionType("jump");
        params2.setActionUrl("http：//");
        integralVO.setActionParams(params2);
        parseMap.put("IntegralVO", JSON.toJSONString(integralVO));
        return parseMap;
    }

    /**
     * 登录按钮
     * @return
     */
    private Map<String, Object> turnLogin() {
        Map<String, Object> parseMap = new HashMap<>(16);
        LoginVO loginVO = new LoginVO();
        loginVO.setType("serviceLogin");
        parseMap.put("IntegralVO", JSON.toJSONString(loginVO));
        return parseMap;
    }


    public Map<String, Object> parseAfterSale(Map<String, Object> dataMap) {
        Map<String, Object> parseMap = new HashMap<>(1);
        if (dataMap.containsKey("afterSales")) {
            if (null == dataMap.get("afterSales")) {
                List<AfterSales> resultList = new ArrayList<>();
                parseMap.put("售后服务", JSON.toJSONString(resultList));
            } else {
                List<AfterSaleConfigureVo> afterSaleList = (List<AfterSaleConfigureVo>) dataMap.get("afterSales");
                StringBuilder sb = new StringBuilder();
                for (AfterSaleConfigureVo vo : afterSaleList) {
                    AfterSales afterSales = new AfterSales();
                    afterSales.setType("imageText");
                    Style style = new Style();
                    style.setMargin(Arrays.asList("0","0","10","0"));
                    ImgParams imageParams = new ImgParams();
                    imageParams.setImgUrl(vo.getImageUrl());
                    imageParams.setImgPlaceHolder("TCLPlaceHolder");
                    imageParams.setImgWidth(1);
                    imageParams.setImgHeight(1);
                    imageParams.setImgEdgeInsets(Arrays.asList(10,30,20,30));
                    afterSales.setImgParams(imageParams);
                    TextParams textParams = new TextParams();
                    textParams.setTextContent(vo.getTitle());
                    textParams.setTextPosition("bottom");
                    textParams.setTextAlignment("center");
                    textParams.setTextColor("#666666");
                    textParams.setFontSize(12);
                    textParams.setLineSpace(4);
                    afterSales.setTextParams(textParams);
                    ActionParams actionParams1 = new ActionParams();
                    actionParams1.setActionType("jump");
                    actionParams1.setActionUrl("https://");
                    afterSales.setActionParams(actionParams1);
                    sb.append(JSON.toJSONString(afterSales)+",");
                }
                parseMap.put("售后服务", sb.substring(0,sb.length()-1));
            }
        }
        return parseMap;
    }
}
