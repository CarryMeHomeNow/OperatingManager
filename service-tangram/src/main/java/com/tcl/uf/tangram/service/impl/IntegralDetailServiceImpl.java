package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commonservice.service.MemberService;
import com.tcl.commonservice.service.PointsService;
import com.tcl.commonservice.service.vo.IntegralDetailVo;
import com.tcl.commonservice.service.vo.MemberVo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.http.HttpClientUtils;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.IntegralDetailService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import com.tcl.uf.tangram.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tcl.uf.tangram.service.impl.ServerTabServiceImpl.poingURL;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 */
@Service
public class IntegralDetailServiceImpl implements IntegralDetailService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private PointsService pointsService;
    @Autowired
    private MemberService memberService;

    @Override
    public String getIntegralDetail(TangramRequestParam paramDTO) {

        String template = commonService.findTemplateById(paramDTO.getReqCode());
        List<IntegralDetailVo> pointDetail = pointsService.getPointDetail(paramDTO.getDate(), paramDTO.getPage(), paramDTO.getSize());
        if (pointDetail == null) {
            throw new JMException("当前未产生积分交易");
        }
        String date = pointDetail.get(0).getDate();
        BigDecimal sum = new BigDecimal(0);
        SimpleDateFormat smYearMonth = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat smDay = new SimpleDateFormat("MM");
        SimpleDateFormat smTime = new SimpleDateFormat("HH:mm");
        for (IntegralDetailVo vo : pointDetail) {
            vo.setYearMonth(smYearMonth.format(TimeUtils.parseDate(date,"yyyy-MM")));
            vo.setDay(smDay.format(TimeUtils.parseDate(date,"MM"))+ "/"+TimeUtils.getWeekOfDate(TimeUtils.conversionDate(date)));
            vo.setTime(smTime.format(TimeUtils.parseDate(date,"yyyy-MM-dd HH:mm:ss")));
            sum = "+".equals(vo.getOpType()) ? sum.add(BigDecimal.valueOf(vo.getScore())) : sum.subtract(BigDecimal.valueOf(vo.getScore()));
            vo.setTotal(sum);
        }
        String yearMonth = pointDetail.get(0).getYearMonth();
        //按日
        Map<String, List<IntegralDetailVo>> collect = pointDetail.stream().collect(Collectors.groupingBy(IntegralDetailVo::getDay));

        return parseDate(template, paramDTO, collect, yearMonth, sum);
    }

    private String parseDate(String template, TangramRequestParam paramDTO, Map<String, List<IntegralDetailVo>> collect, String yearMonth, BigDecimal sum) {
        Map<String, Object> parseMap = new HashMap<>();
        //会员总积分、vip等级
        Map<String, Object> vipMap = turnVipMap(paramDTO);
        parseMap.putAll(vipMap);
        //跳转参数 turnAction
        Map<String, Object> actionMap = turnActionMap();
        List<PointDetailVO> list = new ArrayList<PointDetailVO>();
        PointDetailVO detail = new PointDetailVO();
        detail.setType("pointsDetailList");
        detail.setTitleContent(yearMonth);
        SumTextParams sumTextParams = new SumTextParams();
        sumTextParams.setTextContent("合计：");
        sumTextParams.setTextColor("#000000");
        sumTextParams.setFontSize(15);
        detail.setSumTextParams(sumTextParams);
        SumPointparams sumPointparams = new SumPointparams();
        sumPointparams.setTextContent(String.valueOf(sum));
        sumPointparams.setTextColor("#000000");
        sumPointparams.setFontSize(15);
        detail.setSumPointparams(sumPointparams);
        List<Items> items = new ArrayList<>();
        //日流水
        List<ItemsDetail> detailList = new ArrayList<>();
        for (Map.Entry<String, List<IntegralDetailVo>> entry : collect.entrySet()) {
            List<IntegralDetailVo> value = entry.getValue();
            Items im = new Items();
            for (IntegralDetailVo vo : value) {
                im.setDay(vo.getDay());
                ItemsDetail imDetail = new ItemsDetail();
                imDetail.setTypeValues(vo.getScoreType());
                imDetail.setDescription(vo.getDescription());
                imDetail.setTime(vo.getTime());
                PointsParams pointsParams = new PointsParams();
                pointsParams.setTextContent(vo.getOpType() + vo.getScore());
                pointsParams.setTextColor("#FF0000");
                pointsParams.setFontSize(15);
                imDetail.setPointsParams(pointsParams);
                detailList.add(imDetail);
                im.setItemsDetails(detailList);
            }
            items.add(im);
            detail.setItems(items);
        }
        list.add(detail);
        parseMap.put(TemplateEnums.POINT_DETAIL.getValue(), JSON.toJSONString(list));

        return TangramTemplateUtil.replaceModuleValue(template, parseMap);
    }

    /**
     * 跳转参数
     * @return
     */
    private Map<String, Object> turnActionMap() {
        Map<String, Object> actionMap = new HashMap<>();
        ActionParams all = new ActionParams();
        all.setActionUrl("update");
        all.setActionUrl("http://www");
        actionMap.put(TemplateEnums.POINT_ALL_ACTION.getValue(),all);

        ActionParams receive = new ActionParams();
        receive.setActionUrl("update");
        receive.setActionUrl("http://www");
        actionMap.put(TemplateEnums.POINT_RECEVICE_ACTION.getValue(),receive);

        ActionParams pay = new ActionParams();
        pay.setActionUrl("update");
        pay.setActionUrl("http://www");
        actionMap.put(TemplateEnums.POINT_PAY_ACTION.getValue(),pay);
        return actionMap;
    }

    /**
     * 会员等级与总积分
     *
     * @param paramDTO
     * @return
     */
    private Map<String, Object> turnVipMap(TangramRequestParam paramDTO) {
        Map<String, Object> vipMap = new HashMap<>();
        PointAndLevel pointAndLevel = new PointAndLevel();
        pointAndLevel.setType("pointsDetailHead");
        PointsParams pointsParams = new PointsParams();
        pointsParams.setTextColor("#000000");
        pointsParams.setFontSize(18);
        pointsParams.setLineSpace(10);
        MemberVo memberVo = memberService.queryMemberLevel();
        pointAndLevel.setVipGrade(memberVo.getLevel());
        //总积分
        try {
            String message = HttpClientUtils.sendGet(poingURL + "1282580499042140200");
            JSONObject object = JSON.parseObject(message);
            Integer score = (Integer) object.get("score");
            pointsParams.setTextContent(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pointAndLevel.setPointsParams(pointsParams);
        vipMap.put(TemplateEnums.POINT_INTEGRALANDlV.getValue(), JSON.toJSONString(pointAndLevel));
        return vipMap;
    }

}
