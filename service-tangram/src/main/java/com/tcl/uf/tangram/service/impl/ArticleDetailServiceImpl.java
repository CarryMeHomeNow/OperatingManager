package com.tcl.uf.tangram.service.impl;

import com.tcl.commonservice.service.ContentService;
import com.tcl.commonservice.service.dto.AppArticleRandParams;
import com.tcl.commonservice.service.vo.AppArticleDetailsVo;
import com.tcl.commonservice.service.vo.AppArticleListVo;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.service.ArticleDetailService;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongfk on 2020/8/22.
 * @version 1.0
 */
@Service
public class ArticleDetailServiceImpl implements ArticleDetailService {
    @Autowired
    public CommonService commonService;
    @Autowired
    public ContentService contentService;
    @Override
    public String articleDetail(String reqCode,Long articleId,Long sectionId) {
        String template = commonService.findTemplateById(reqCode);
        //文章详情
        ResponseData<AppArticleDetailsVo> appArticleData = contentService.appArticleDetail(articleId);
        AppArticleDetailsVo article = null == appArticleData ? null : appArticleData.getData();

        //推荐列表
        AppArticleRandParams randParams = new AppArticleRandParams();
        randParams.setSectionId(sectionId);
        randParams.setArticleId(articleId);
        ResponseData<List<AppArticleListVo>> listResponseData = contentService.appArticleRand(randParams);
        List<AppArticleListVo> randArticle = null == listResponseData.getData() ? null : listResponseData.getData();

        Map<String,Object> dataMap = new HashMap<>(16);
        dataMap.put(TemplateEnums.ARTICLE_CONTENT.getValue(),article);
        dataMap.put(TemplateEnums.ARTICLE_RAND.getValue(),randArticle);

        return parseData(template,dataMap);
    }

    public String parseData(String template,Map<String,Object> dataMap){
        Map<String,Object> parseMap = new HashMap<>(6);
        //文章内容
        AppArticleDetailsVo article = (AppArticleDetailsVo) dataMap.get(TemplateEnums.ARTICLE_CONTENT.getValue());
        parseMap.put(TemplateEnums.ARTICLE_RAAND_lIST.getValue(),article);
        //推荐列表
        Map<String, Object> map = commonService.parseArticleList(dataMap);
        parseMap.putAll(map);
        return TangramTemplateUtil.replaceModuleValue(template,parseMap);
    }
}
