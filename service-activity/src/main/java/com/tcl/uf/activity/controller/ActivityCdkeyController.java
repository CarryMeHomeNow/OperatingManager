package com.tcl.uf.activity.controller;

import com.tcl.commondb.activity.model.ActivityCdkeyModel;
import com.tcl.uf.activity.dto.ActivityCdkeyListDto;
import com.tcl.uf.activity.service.ActivityCdkeyService;
import com.tcl.uf.activity.vo.ActivityCdkeySaveVo;
import com.tcl.uf.common.base.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/marketing/cdkey")
public class ActivityCdkeyController {
    @Autowired
    private ActivityCdkeyService activityCdkeyService;

    //兑换码列表搜索
    @RequestMapping("/list")
    public ResponseData<Object> findcdKey(@RequestParam("page") Integer page,@RequestParam("size") Integer size,@RequestBody ActivityCdkeyListDto activityCdkeyListDto) {

        Map<String, Object> allCdKey = activityCdkeyService.findAll(activityCdkeyListDto, page, size);
        return new ResponseData<>(allCdKey);
    }

    @RequestMapping("/del/{id}")
    public ResponseData<Object> delCdkey(@PathVariable("id") Long id) {
        boolean result = activityCdkeyService.deleteCdKey(id);
        return new ResponseData<>(result);
    }

    //新增
    @RequestMapping("/save")
    public ResponseData<Object> insertCdKey(@RequestBody ActivityCdkeySaveVo activityCdkeySaveVo) {


        boolean b = activityCdkeyService.insertCdKey(activityCdkeySaveVo);
        return new ResponseData<>(b);
    }

    //修改
    @RequestMapping("/update")
    public ResponseData<Object> updateCdKey(@RequestBody ActivityCdkeySaveVo activityCdkeySaveVo) {
        boolean b = activityCdkeyService.updateCdKey(activityCdkeySaveVo);
        return new ResponseData<>(b);
    }

    //失效生效
    @RequestMapping("/updateStatus")
    public ResponseData<Object> updateStatus(@RequestParam("id") Long id,@RequestParam("status") Integer status) {

        boolean result = activityCdkeyService.updateCdKeyStatus(id,status);

        return new ResponseData<>(result);
    }

    @RequestMapping("/export")
    public ResponseData export(@PathVariable("id") Long id) {
        int page =0;
        int size =10;
        PageRequest pageable = PageRequest.of(page,size);
        Page<ActivityCdkeyModel> models = activityCdkeyService.exportCdKey(pageable);
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("id","序号");
        fieldMap.put("name","活动名称");
        fieldMap.put("cdType","兑换类型");
        fieldMap.put("startTime","");
        fieldMap.put("endTime","开始时间");
        fieldMap.put("total","发放总数");
        fieldMap.put("productName","商品名称");
        fieldMap.put("productUrl","商品跳转地址");
        fieldMap.put("ccdPoints","可兑换积分");
        fieldMap.put("grant","已兑换数");
        fieldMap.put("status","兑换码状态");
        fieldMap.put("isDelete","是否删除");
        fieldMap.put("createTime","创建时间");
        fieldMap.put("updateTime","更新时间");
        try {
            //调用导出数据的方法
//            ExcelUtil.listToExcel(models.getList(), fieldMap, "隐患排查清单", 65535,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseData<>();
    }
    //app接口兑换
    @RequestMapping("/exchange")
    public ResponseData<Object> exchangeCdKey(@RequestParam("cdk") String cdk) throws Exception {
        boolean b = activityCdkeyService.exchangeCdKey(cdk);
        return new ResponseData<> (b);
    }
}
