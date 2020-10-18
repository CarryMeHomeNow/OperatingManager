package com.tcl.uf.points.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.tcl.uf.points.config.TclinterfaceConstant;
import com.tcl.uf.points.service.PointService;
import com.tcl.uf.points.vo.IntegralDetailVo;
import com.tcl.uf.points.vo.param.PointsQueryResp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import com.alibaba.fastjson.JSON;
import com.tcl.uf.points.config.TclinterfaceConstant;
import com.tcl.uf.points.service.PointService;
import com.tcl.uf.points.vo.param.PointsQueryResp;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;

@Service
public class PointServiceImpl implements PointService {
    Log _log = LogFactory.getLog(PointService.class);
    @Value("${tclaccounnt.url}")
    private String url;
    @Override
    public Integer getPointBySSOID(String token,Long accountId) {
        try {
            String result = HttpRequest.get(url + TclinterfaceConstant.point_query_url + accountId).header("TCL-Authorization", token).execute().body();
            if(StringUtils.isEmpty(result) || "null".equals(result)){return 0;}
            PointsQueryResp parse = JSON.parseObject(result, PointsQueryResp.class);
            _log.info("PointServiceImpl getPointBySSOID url:"+url + TclinterfaceConstant.point_query_url + accountId+"   ,result:"+result);
            return parse.getScore();
        }catch (Exception e){
            e.printStackTrace();
            _log.error("PointService getPointBySSOID accointId:"+accountId+"   error:"+e);
        }
        return 0;
    }

    @Override
    public boolean pushPoints(String token,Long accountId, Integer score, String description, String source, String opType,String sourceType,Integer growValue) {
        Map<String,Object> map = new HashMap<>();
        map.put("version","1.0.0");
        map.put("flowId", UUID.randomUUID().toString());
        map.put("accountId", accountId);
        map.put("score", score);
        map.put("date", DateUtil.now());
        map.put("description", description);
        map.put("source", source);
        map.put("opType", opType);
        map.put("scoreType", sourceType);
        map.put("growValue", growValue);
        map.put("operator", "admin");
        String urlStr  = url + TclinterfaceConstant.point_push_url;
        String result = HttpRequest.post(urlStr).header("TCL-Authorization", token).body(JSON.toJSONString(map)).execute().body();
        _log.info("PointService pushPoints param:"+JSON.toJSONString(map)+"   result:"+result);
        if(result.contains("10000")){return true;}
        return false;
    }

    @Override
    public List<IntegralDetailVo> getPointDetail(Long accountId, String date, Integer form, Integer size, String token) {
        List<IntegralDetailVo> resp = new ArrayList<IntegralDetailVo>();
        //+"&form="+form+"&size="+size 暂不传分页
        try {
            String result = HttpRequest.get(url + TclinterfaceConstant.point_detail_url + accountId+"&date="+date)
                    .header("TCL-Authorization", token).execute().body();
            if(StringUtils.isEmpty(result) || "null".equals(result)){return resp;}
            resp = JSON.parseArray(result, IntegralDetailVo.class);
            _log.info("PointServiceImpl getPointDetail url:"+url + TclinterfaceConstant.point_query_url + accountId+"   ,result:"+result);
        }catch (Exception e){
            e.printStackTrace();
            _log.error("PointService getPointDetail accointId:"+accountId+"   error:"+e);
        }

        return resp;
    }

}
