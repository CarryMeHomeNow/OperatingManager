package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.tcl.commonservice.service.MemberService;
import com.tcl.commonservice.service.dto.MemberRightsSet;
import com.tcl.commonservice.service.vo.MemberVo;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.ServerMemberService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import com.tcl.uf.tangram.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongfk on 2020/8/22.
 * @version 1.0
 */
@Service
public class ServerMemberServiceImpl implements ServerMemberService {
    @Autowired
    public CommonService commonService;
    @Autowired
    public MemberService memberService;

    @Override
    public String getMemberLevel(TangramRequestParam paramDTO,HttpServletRequest request) {
        String template = commonService.findTemplateById(paramDTO.getReqCode());
        //会员等级
        MemberVo memberVo = memberService.queryMemberLevel();

        //查询所有会员权益
        ResponseData<List<MemberRightsSet>> memberSet = memberService.findAll();
        List<MemberRightsSet> rightsSets = null == memberSet ? null : memberSet.getData();

        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put(TemplateEnums.MEMBER_LEVEL.getValue(), memberVo);
        dataMap.put(TemplateEnums.MEMBER_RIGHTSET.getValue(), rightsSets);

        return parseData(template, dataMap);
    }

    public String parseData(String template, Map<String, Object> dataMap) {
        Map<String, Object> parseMap = new HashMap<>(16);
        MemberVo memberVo = (MemberVo) dataMap.get(TemplateEnums.MEMBER_LEVEL.getValue());
        List<MemberRightsSet> rightsSets = (List<MemberRightsSet>) dataMap.get(TemplateEnums.MEMBER_RIGHTSET.getValue());
        Map<String, Object> level = parseMemberLevel(memberVo);
        Map<String, Object> rightSet = parseRightSet(rightsSets);
        if(null != level){
            parseMap.putAll(level);
        }
        if(null != rightSet){
            parseMap.putAll(rightSet);
        }
        return TangramTemplateUtil.replaceModuleValue(template, parseMap);
    }


    /**
     * 会员等级数据组装
     * @param memberVo
     * @return
     */
    public Map<String, Object> parseMemberLevel(MemberVo memberVo) {
        Map<String, Object> parseMap = new HashMap<>(1);
        if (null != memberVo) {
            MemberLevelVO memberLevelVO = new MemberLevelVO();
            VipTextParams vipTextParams = new VipTextParams();
            vipTextParams.setTextContent("2");
            vipTextParams.setVipLevel(memberVo.getLevel());
            memberLevelVO.setVipTextParams(vipTextParams);
            GrowthParams growthParams = new GrowthParams();
            growthParams.setCurrentValue(memberVo.getGrowValue());
            growthParams.setRemainValue(memberVo.getNextValueDifference());
            memberLevelVO.setGrowthParams(growthParams);
            memberLevelVO.setType("gradeHead");
            parseMap.put(TemplateEnums.MEMBER_LEVEL.getValue(), JSON.toJSONString(memberLevelVO));
        } else {
            //默认返回会员等级
            MemberLevelVO memberLevelVO = new MemberLevelVO();
            VipTextParams vipTextParams = new VipTextParams();
            vipTextParams.setTextContent("黑金会员");
            vipTextParams.setVipLevel("V4");
            memberLevelVO.setVipTextParams(vipTextParams);
            GrowthParams growthParams = new GrowthParams();
            growthParams.setCurrentValue("1000");
            growthParams.setRemainValue("300");
            memberLevelVO.setGrowthParams(growthParams);
            memberLevelVO.setType("gradeHead");
            parseMap.put(TemplateEnums.MEMBER_LEVEL.getValue(), JSON.toJSONString(memberLevelVO));
        }
        return parseMap;
    }

    /**
     * 会员权益数据组装
     *
     * @param rightsSets
     */
    public Map<String, Object> parseRightSet(List<MemberRightsSet> rightsSets) {
        Map<String, Object> parseMap = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        if (null != rightsSets) {
            for (int i = 1; i < rightsSets.size(); i++) {
                MemberRightSetVO memberRightSetVO = new MemberRightSetVO();
                Style style = new Style();
                style.setBgColor("#ffffff");
                style.setMargin(Arrays.asList("5","5","5","5"));
                memberRightSetVO.setStyle(style);
                ImgParams imgParams = new ImgParams();
                memberRightSetVO.setImgParams(imgParams);
                TitleParams titleParams = new TitleParams();
                titleParams.setTextContent(rightsSets.get(i).getName());
                titleParams.setTextAlignment("left");
                titleParams.setTextColor("#000000");
                titleParams.setFontSize(15);
                memberRightSetVO.setTitleParams(titleParams);
                SubtitleParams subtitleParams = new SubtitleParams();
                subtitleParams.setTextContent(rightsSets.get(i).getSn());
                subtitleParams.setTextAlignment(rightsSets.get(i).getSn());
                subtitleParams.setTextColor(rightsSets.get(i).getSn());
                subtitleParams.setFontSize(13);
                memberRightSetVO.setSubtitleParams(subtitleParams);
                ActionParams actionParams = new ActionParams();
                actionParams.setActionType("jump");
                actionParams.setActionUrl("url");
                actionParams.setEquityId(String.valueOf(rightsSets.get(i).getId()));
                sb.append(JSON.toJSONString(memberRightSetVO)+",");
            }
            parseMap.put(TemplateEnums.MEMBER_RIGHTSET.getValue(),sb.substring(0,sb.length()-1));
        } else {
            parseMap.put(TemplateEnums.MEMBER_RIGHTSET.getValue(),"{\n" + " \"errCode\":\"会员权益查询出错\"\n" + "}");
        }

        return parseMap;
    }


}
