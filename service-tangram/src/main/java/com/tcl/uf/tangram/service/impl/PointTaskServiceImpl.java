package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.tcl.commonservice.service.PointsService;
import com.tcl.commonservice.service.dto.PointTask;
import com.tcl.commonservice.service.vo.PointTaskDetailVo;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.PointTaskService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import com.tcl.uf.tangram.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhongfk on 2020/8/27.
 * @version 1.0
 */
@Service
public class PointTaskServiceImpl implements PointTaskService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private PointsService pointsService;

    @Override
    public String getPointTask(TangramRequestParam paramDTO) {
        String template = commonService.findTemplateById(paramDTO.getReqCode());
        //积分列表
        ResponseData<Map<String, Object>> serviceAll = pointsService.findAll(paramDTO.getPage(), paramDTO.getSize());
        Map<String, Object> pointTask = null == serviceAll ? null : serviceAll.getData();
        if (CollectionUtils.isEmpty(pointTask)) {
            throw new JMException("查询积分列表失败");
        }
        List<PointTask> taskList = (List<PointTask>) pointTask.get("rows");
        //未删除文章 1 新手 2 日常
        Map<Integer, List<PointTask>> collect = taskList.stream().filter(s -> "1".equals(s.getIsDelete())).collect(Collectors.groupingBy(PointTask::getType));

        Map<String, Object> parseData = parseData(template, collect);

        return TangramTemplateUtil.replaceModuleValue(template, parseData);
    }

    /**
     * 积分详情
     * @param paramDTO
     * @return
     */
    @Override
    public String getTaskDetail(TangramRequestParam paramDTO) {
        String template = commonService.findTemplateById(paramDTO.getReqCode());
        //积分详情
        ResponseData<PointTaskDetailVo> detail = pointsService.findDetail(paramDTO.getBusinessId());
        PointTaskDetailVo detailData = detail.getData();
        Map<String, Object> map = parseDetail(detailData);
        return TangramTemplateUtil.replaceModuleValue(template,map);
    }

    private Map<String,Object> parseDetail(PointTaskDetailVo detail) {
        Map<String, Object> dataMap = new HashMap<>();
        ActionParams actionParams = new ActionParams();
        actionParams.setActionType("jump");
        actionParams.setActionUrl("http://");
        dataMap.put(TemplateEnums.HEAD_IMGURL.getValue(),detail.getUrl());
        dataMap.put(TemplateEnums.HEAD_TITLE.getValue(),detail.getTitle());
        dataMap.put(TemplateEnums.HEAD_CONTENT.getValue(),detail.getContent());
        dataMap.put(TemplateEnums.HEAD_DESCCOM.getValue(),"完成度：0/3");
        dataMap.put(TemplateEnums.HEAD_ACTIONPARAMS.getValue(),JSON.toJSONString(actionParams));
        dataMap.put(TemplateEnums.CENTER_CONTENT.getValue(),detail.getContent());
        dataMap.put(TemplateEnums.CENTER_CONTENT1.getValue(),detail.getContent());
        dataMap.put(TemplateEnums.CENTER_IMGURL.getValue(),detail.getUrl());
        dataMap.put(TemplateEnums.FOOTER_ACTIONPARAMS.getValue(),JSON.toJSONString(actionParams));
        return dataMap;
    }

    public Map<String, Object> parseData(String template, Map<Integer, List<PointTask>> collect) {
        Map<String, Object> parseMap = new HashMap<>(16);
        Map<String, Object> newTask = turnTaskVO(collect.get("1"),1);
        Map<String, Object> dailyTask = turnTaskVO(collect.get("2"),2);
        if (null != newTask) {
            parseMap.putAll(newTask);
        }
        if (null != dailyTask) {
            parseMap.putAll(dailyTask);
        }
        return parseMap;
    }

    public Map<String, Object> turnTaskVO(List<PointTask> taskList,Integer type) {
        String newTask = "1";
        Map<String, Object> parseMap = new HashMap<>(16);
        List<TaskVO> list = new ArrayList<>();
        for (PointTask vo : taskList) {
            TaskVO taskVO = new TaskVO();
            taskVO.setType("task");
            Style style = new Style();
            style.setBgColor("#ffffff");
            ImgParams imgParams = new ImgParams();
            imgParams.setImgUrl(vo.getUrl());
            imgParams.setImgPlaceHolder("TCLPlaceHolder");
            imgParams.setImgWidth(80);
            imgParams.setImgHeight(80);
            taskVO.setImgParams(imgParams);
            TitleParams titleParams = new TitleParams();
            titleParams.setTextContent(vo.getTitle());
            titleParams.setTextAlignment("left");
            titleParams.setTextColor("#000000");
            titleParams.setFontSize(12);
            titleParams.setLineSpace(4);
            taskVO.setTitleParams(titleParams);
            PointValues pointValues = new PointValues();
            pointValues.setTextContent(vo.getContent());
            pointValues.setTextAlignment("left");
            pointValues.setFontSize(12);
            pointValues.setLineSpace(4);
            taskVO.setPointValues(pointValues);
            ButtonParams buttonParams = new ButtonParams();
            //完成度 完成false 隐藏
            buttonParams.setShow(true);
            ActionParams butAction = new ActionParams();
            butAction.setActionType("jump");
            butAction.setActionUrl("/service/taskdetail");
            buttonParams.setActionParams(butAction);
            ButtonDescParams buttonDescParams = new ButtonDescParams();
            buttonDescParams.setTextContent("完成度 0/4");
            buttonDescParams.setTextAlignment("center");
            buttonDescParams.setTextConlor("#000000");
            buttonDescParams.setFontSize(12);
            buttonDescParams.setLineSpace(4);
            ActionParams actionParams = new ActionParams();
            actionParams.setActionType("jump");
            actionParams.setActionUrl("http");
            list.add(taskVO);
        }
        if(newTask.equals(type)){
            parseMap.put(TemplateEnums.POINT_NEWTASK.getValue(),JSON.toJSONString(list));
        }else{
            parseMap.put(TemplateEnums.POINT_DAILYTASK.getValue(),JSON.toJSONString(list));
        }
        return parseMap;
    }
}
