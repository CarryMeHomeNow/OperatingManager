package com.tcl.uf.tangram.service.impl;

import com.tcl.commonservice.service.MemberService;
import com.tcl.commonservice.service.vo.IntegralRuleConfigureVo;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.MemberPlayService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import com.tcl.uf.tangram.vo.TangramRequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 */
@Service
public class MemberPlayServiceImpl implements MemberPlayService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private MemberService memberService;

    @Override
    public String getMemberPlay(TangramRequestParam paramDTO) {
        String template = commonService.findTemplateById(paramDTO.getReqCode());
        //会员玩法
        ResponseData<IntegralRuleConfigureVo> integralRule = memberService.getIntegralRule();
        IntegralRuleConfigureVo ruleData = integralRule == null ? null : integralRule.getData();

        return parseData(template, ruleData);
    }

    private String parseData(String template, IntegralRuleConfigureVo ruleData) {
        Map<String, Object> parseMap = new HashMap<>();
        parseMap.put(TemplateEnums.MEMBER_RULES.getValue(), null);
        return TangramTemplateUtil.replaceModuleValue(template, parseMap);
    }
}
