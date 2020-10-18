package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import com.tcl.commondb.tangram.repository.TangramTemplateConfigRepository;
import com.tcl.commonservice.service.vo.AppArticleListVo;
import com.tcl.commonservice.service.vo.ResourceAppListVo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.ThirdShopContentService;
import com.tcl.uf.tangram.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhongfk on 2020/8/14.
 * @version 1.0
 */
@Service
public class CommonServiceImpl implements CommonService {


    final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private ThirdShopContentService thirdShopContentService;

    @Autowired
    private TangramTemplateConfigRepository tangramTemplateConfigRepository;

    @Override
    public List<TangramTemplateConfig> queryAllTemplate() {

        List<TangramTemplateConfig> all = tangramTemplateConfigRepository.findAll();

        return all;
    }

    @Override
    public String findTemplateById(String templateCode) {

        //缓存设置 后添
        TangramTemplateConfig one = tangramTemplateConfigRepository.findTangramTemplateConfigByTangramCode(templateCode);

        return one.getTangramTemplate();
    }

    /**
     * 文章列表
     * @param dataMap
     * @return
     */
    @Override
    public Map<String, Object> parseArticleList(Map<String, Object> dataMap) {
        Map parseMap = new HashMap(2);
        if (null == dataMap.get(TemplateEnums.ARTICLE_lIST.getValue())) {
            List<ArticleVO> resultList = new ArrayList<>();
            parseMap.put(TemplateEnums.ARTICLE_lIST, JSON.toJSONString(resultList));
        }
        if (null != dataMap.get(TemplateEnums.ARTICLE_lIST.getValue())) {
            ListWithPage<AppArticleListVo> articleList = (ListWithPage<AppArticleListVo>) dataMap.get(TemplateEnums.ARTICLE_lIST.getValue());
            List<AppArticleListVo> list = articleList.getList();
            List<ArticleVO> resultList = new ArrayList<>();
            for (AppArticleListVo vo : list) {
                ArticleVO articleVO = new ArticleVO();
                articleVO.setType("timeline");
                articleVO.setCornerRadius("10");
                Style style = new Style();
                style.setBgColor("");
                style.setMargin(Arrays.asList("5","5","5","5"));
                articleVO.setStyle(style);
                ImgParams imageParams = new ImgParams();
                imageParams.setImgUrl(vo.getArticleCover());
                if(vo.getLatest()){
                    imageParams.setSmallIcon("small_icon_new");
                }
                imageParams.setImgPlaceHolder("TCLPlaceHolder");
                imageParams.setImgWidth(1);
                imageParams.setImgHeight(1);
                articleVO.setImgParams(imageParams);
                TitleParams titleParams = new TitleParams();
                titleParams.setTextContent(vo.getTitle());
                titleParams.setTextAlignment("left");
                titleParams.setTextColor("#000000");
                titleParams.setFontSize(12);
                articleVO.setTitleParams(titleParams);
                ReadCount readCount = new ReadCount();
                readCount.setTextContent(vo.getBrowseNum());
                readCount.setTextAlignment("left");
                readCount.setTextColor("#000000");
                readCount.setFontSize(12);
                readCount.setLineSpace(4);
                articleVO.setReadCount(readCount);
                if("1".equals(vo.getAdvertisement())){
                    articleVO.setType("ad");
                }
                ActionParams articleAction = new ActionParams();
                articleAction.setActionUrl("url");
                articleAction.setActionType("jump");
                articleAction.setCommodityId("123456");
                articleVO.setActionParams(articleAction);
                resultList.add(articleVO);
            }
            parseMap.put("ArticleVO",JSON.toJSONString(resultList));
        }
        return parseMap;
    }

    /**
     * 广告组装
     * @param dataMap
     * @return
     */
    @Override
    public Map<String, Object> turnAdvert(Map<String, Object> dataMap) {
        Map<String, Object> parseMap = new HashMap<>();
        List<ResourceAppListVo>  list = (List<ResourceAppListVo> ) dataMap.get(TemplateEnums.COMMON_ADVERT.getValue());
        List<AdvertiseVO> advertiseVOS = new ArrayList<AdvertiseVO>();
        for (ResourceAppListVo vo : list) {
            AdvertiseVO advertiseVO = new AdvertiseVO();
            advertiseVO.setType("image");
            advertiseVO.setImgUrl(vo.getAdPicUrl());
            advertiseVO.setImgPlaceHolder("TCLPlaceHolder");
            advertiseVO.setImgWidth(524);
            advertiseVO.setImgWidth(300);
            ActionParams actionParams = new ActionParams();
            actionParams.setActionUrl(vo.getAdLinkUrl());
            actionParams.setActionType("jump");
            advertiseVOS.add(advertiseVO);
        }
        parseMap.put(TemplateEnums.COMMON_ADVERT.getValue(),JSON.toJSONString(advertiseVOS));
        return parseMap;
    }

    /**
     * 文章列表
     * @return
     */
    @Override
    public String turnArticle(ListWithPage<AppArticleListVo> articleList) {
        Cards cards = new Cards();
        List<AppArticleListVo> list = articleList.getList();
        List<Cards> resultList = new ArrayList<>();
        for (AppArticleListVo vo : list) {
            Cards card = new Cards();
            card.setType("timeline");
            card.setCornerRadius("10");
            Style style = new Style();
            style.setBgColor("");
            style.setMargin(Arrays.asList("5", "5", "5", "5"));
            card.setStyle(style);
            ImgParams imageParams = new ImgParams();
            imageParams.setImgUrl(vo.getArticleCover());
            if (vo.getLatest()) {
                imageParams.setSmallIcon("small_icon_new");
            }
            imageParams.setImgPlaceHolder("TCLPlaceHolder");
            imageParams.setImgWidth(1);
            imageParams.setImgHeight(1);
            card.setImgParams(imageParams);
            TitleParams titleParams = new TitleParams();
            titleParams.setTextContent(vo.getTitle());
            titleParams.setTextAlignment("left");
            titleParams.setTextColor("#000000");
            titleParams.setFontSize(12);
            card.setTitleParams(titleParams);
            ReadCount readContent = new ReadCount();
            readContent.setTextContent(vo.getBrowseNum());
            readContent.setTextAlignment("left");
            readContent.setTextColor("#000000");
            readContent.setFontSize(12);
            readContent.setLineSpace(4);
            card.setReadCount(readContent);
            List<Tips> tips = new ArrayList<>();
            if ("1".equals(vo.getAdvertisement())) {
                card.setTips("ad");
            }
            ActionParams articleAction = new ActionParams();
            articleAction.setActionUrl("url");
            articleAction.setActionType("jump");
            articleAction.setCommodityId("123456");
            card.setActionParams(articleAction);
            resultList.add(card);
        }
        cards.setCards(resultList);
        return JSON.toJSONString(cards);
    }

    @Override
    public String getPageManageByPageType(String pageType, String uiType) {
        String resStr = "";
        try{
            //解析接口响应数据，获取data
            String respData = thirdShopContentService.getPageManageByPageType(pageType, uiType);
            JSONObject resJson = JSONObject.parseObject(respData);
            if("200".equals(resJson.getString("code"))){
                resStr = resJson.getJSONObject("data").getString("content");
            }else{
                logger.error("调用商城接口获取页面{}配置数据错误，错误码{},错误信息{}",pageType,resJson.getString("code"),
                        resJson.getString("message"));
            }
        }catch (Exception e){
            logger.error("调用商城接口获取页面配置数据异常{}",e.getMessage());
        }
        return resStr;
    }

    @Override
    public String getHotSearchKeyword(String type) {
        String resStr = "";
        try{
            //解析接口响应数据，获取data
            String respData = thirdShopContentService.getHotSearchKeyword(type);
            JSONObject resJson = JSONObject.parseObject(respData);
            if("200".equals(resJson.getString("code"))){
                resStr = resJson.getJSONObject("data").getString("content");
            }else{
                logger.error("调用商城接口获取热门搜索错误，错误码{},错误信息{}",resJson.getString("code"),
                        resJson.getString("message"));
            }
        }catch (Exception e){
            logger.error("调用商城接口获取热门搜索错误{}",e.getMessage());
        }
        return resStr;
    }
}