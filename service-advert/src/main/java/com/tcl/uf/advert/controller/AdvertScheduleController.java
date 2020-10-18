package com.tcl.uf.advert.controller;

import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.dto.ScheduleListParams;
import com.tcl.uf.advert.dto.ScheduleResourceListParams;
import com.tcl.uf.advert.service.AdvertScheduleService;
import com.tcl.uf.advert.service.AdvertUserService;
import com.tcl.uf.advert.vo.ImportScheduleVo;
import com.tcl.uf.advert.vo.ResourceDetailVo;
import com.tcl.uf.advert.vo.ScheduleDayListVo;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertScheduleController
 * @Description:广告排期管理
 * @date 2020/8/24 20:48
 */
@Api(value = "广告排期管理")
@RestController
@RequestMapping("/schedule")
public class AdvertScheduleController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(AdvertScheduleController.class);

    @Autowired
    AdvertScheduleService advertScheduleService;

    @Autowired
    AdvertUserService advertUserService;

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @PostMapping(value = "/back/importFile")
    @ApiOperation(value = "导入排期表" , notes="导入排期表",httpMethod = "POST")
    public ResponseData<ImportScheduleVo> importFile(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("month") Integer month) {
        ImportScheduleVo importScheduleVo;
        try {
            TokenData userInfo = getManageUserInfo(request);
            importScheduleVo = advertScheduleService.importSchedule(file, month);
            if (!importScheduleVo.getSuccessList().isEmpty()) {
                Integer successNum = advertScheduleService.batchSaveSchedule(importScheduleVo.getSuccessList(), userInfo.getUsername());
                importScheduleVo.setSuccessNum(successNum);
            }
        } catch (JMException e) {
            _log.error("上传广告位排期异常", e);
            return fail(e.getMessage());
        }
        return success(importScheduleVo);
    }

    @PostMapping(value = "/back/collect")
    @ApiOperation(value = "查看排期汇总表" , notes="查看排期汇总表",httpMethod = "POST")
    public ResponseData<List<ScheduleDayListVo>> findList(@RequestBody ScheduleListParams scheduleListParams) {
        List<ScheduleDayListVo> scheduleVoList;
        try {
            scheduleVoList = advertScheduleService.findList(scheduleListParams);
        } catch (JMException e) {
            _log.error("查看排期汇总表异常", e);
            return fail(e.getMessage());
        }
        return success(scheduleVoList);
    }

    @GetMapping(value = "/back/locationDateList")
    @ApiOperation(value = "获取当前广告位可投放日期" , notes="获取当前广告位可投放日期",httpMethod = "GET")
    public ResponseData<Set<String>> findLocationDateList(HttpServletRequest request, @RequestParam("groupId") Long groupId, @RequestParam("frameId") Integer frameId) {
        Set<String> dateSet;
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.hasRole(AdvertConstants.ADVERT_ROLE_ADMIN)) {
                dateSet = advertScheduleService.findLocationDateList(null,groupId,frameId);
            }else{
                TokenData manageUserInfo = getManageUserInfo(request);
                dateSet = advertScheduleService.findLocationDateList(manageUserInfo.getUsername(),groupId,frameId);
            }
        } catch (JMException e) {
            _log.error("获取当前广告位可投放日期异常", e);
            return fail(e.getMessage());
        }
        return success(dateSet);
    }

    @PostMapping(value = "/back/resourceList")
    @ApiOperation(value = "排期需投放资源列表", notes = "排期需投放资源列表", httpMethod = "POST")
    public ResponseData<ListWithPage<ResourceDetailVo>> resourceList(HttpServletRequest request, @RequestBody ScheduleResourceListParams scheduleResourceListParams) {
        ListWithPage<ResourceDetailVo> resultList;
        try {
            TokenData userInfo = getManageUserInfo(request);
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            resultList = advertScheduleService.resourceList(pageable, scheduleResourceListParams, userInfo.getUsername());
        } catch (JMException e) {
            _log.error("排期需投放资源列表发生异常:", e);
            return fail(e.getMessage());
        }
        return success(resultList);
    }


}
