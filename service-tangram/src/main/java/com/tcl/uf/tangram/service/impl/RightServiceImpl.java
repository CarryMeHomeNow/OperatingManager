package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.tcl.commonservice.service.MemberService;
import com.tcl.commonservice.service.dto.MemberRightsSet;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.RightSetService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import com.tcl.uf.tangram.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhongfk on 2020/8/25.
 * @version 1.0
 */
@Service
public class RightServiceImpl implements RightSetService {
    @Autowired
    public CommonService commonService;
    @Autowired
    public MemberService memberService;

    @Override
    public String getMemberRightSet(TangramRequestParam paramDTO) {
        String template = commonService.findTemplateById(paramDTO.getReqCode());
        //会员权益
        ResponseData<List<MemberRightsSet>> listResponseData = memberService.findAll();
        List<MemberRightsSet> rightsSets = null == listResponseData ? null : listResponseData.getData();

        //权益详情
        MemberRightsSet rightsSetDetail = memberService.findRightSet(paramDTO.getRightSetId());
        Map<String,Object>  dataMap = new HashMap<>(2);
        dataMap.put(TemplateEnums.MEMBER_RIGHTSET.getValue(),rightsSets);
        dataMap.put(TemplateEnums.MEMBER_RIGHTSET_DETAIL.getValue(),rightsSetDetail);

        return parseData(template,dataMap);
    }

    /**
     * 数据解析
     * @param template
     * @param dataMap
     * @return
     */
    private String parseData(String template, Map<String,Object> dataMap) {
        Map<String,Object> parseMap = new HashMap<>(4);
        MemberRightsSet rightsSetDetail = (MemberRightsSet) dataMap.get(TemplateEnums.MEMBER_RIGHTSET_DETAIL.getValue());
        if(null != dataMap.get(TemplateEnums.MEMBER_RIGHTSET.getValue())){
            List<MemberRightsSet> rightsSets = (List<MemberRightsSet>) dataMap.get(TemplateEnums.MEMBER_RIGHTSET.getValue());
            List<MemberRightSetVO> list = new ArrayList<>();
            for (MemberRightsSet vo : rightsSets) {
                MemberRightSetVO rightSetVO = new MemberRightSetVO();
                rightSetVO.setType("imageText");
                ImgParams imgParams = new ImgParams();
                imgParams.setImgPlaceHolder("vip-equity-icon1");
                imgParams.setImgWidth(60);
                imgParams.setImgHeight(60);
                rightSetVO.setImgEdgeInsets(Arrays.asList("0","10","25","10"));
                TextParams textParams = new TextParams();
                textParams.setTextContent(vo.getName());
                textParams.setTextPosition("bottom");
                textParams.setTextAlignment("center");
                textParams.setTextColor("#000000");
                textParams.setFontSize(12);
                textParams.setFontSize(4);
                rightSetVO.setTextParams(textParams);
                ActionParams actionParams = new ActionParams();
                actionParams.setActionType("jump");
                actionParams.setActionUrl("https://www.baidu.com");
                rightSetVO.setActionParams(actionParams);
                list.add(rightSetVO);
            }
            parseMap.put(TemplateEnums.MEMBER_RIGHTSET.getValue(),JSON.toJSONString(list));
        }else{
            parseMap.put(TemplateEnums.MEMBER_RIGHTSET.getValue(),"{\n" + " \"errCode\":\"会员权益查询出错\"\n" + "}");
        }
        Map<String, Object> detailMap = packageRightSetDetail(rightsSetDetail);
        if(null != detailMap){
            parseMap.putAll(detailMap);
        }
        return TangramTemplateUtil.replaceModuleValue(template,parseMap);
    }

    /**
     * 权益信息数据组装
     * @param rightsSetDetail
     * @return
     */
    private Map<String, Object> packageRightSetDetail(MemberRightsSet rightsSetDetail) {
        Map<String, Object> parseMap = new HashMap<>(4);
        parseMap.put(TemplateEnums.MEMBER_RIGHTSET_CONDITION.getValue(),rightsSetDetail.getGetCondition());
        parseMap.put(TemplateEnums.MEMBER_RIGHTSET_DESC.getValue(),rightsSetDetail.getDescription());
        parseMap.put(TemplateEnums.MEMBER_RIGHTSET_URL.getValue(),rightsSetDetail.getCoverUrl());
        parseMap.put(TemplateEnums.MEMBER_RIGHTSET_JUMPURL.getValue(),rightsSetDetail.getJumpUrl());
        return parseMap;
    }
}
