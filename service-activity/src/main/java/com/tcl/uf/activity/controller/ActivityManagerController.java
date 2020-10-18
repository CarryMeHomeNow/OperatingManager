package com.tcl.uf.activity.controller;

import com.tcl.uf.activity.dto.ActivityManagerConfigureDto;
import com.tcl.uf.activity.dto.ActivityManagerConfigureParams;
import com.tcl.uf.activity.service.ActivityManagerService;
import com.tcl.uf.activity.vo.ActivityManagerConfigureDetailsVo;
import com.tcl.uf.activity.vo.ActivityManagerConfigureVo;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author youyun.xu
 * @ClassName: ActivityManagerController
 * @Description: 活动列表管理
 * @date 2020/8/17 15:54
 */
@Api(description = "后台活动列表管理")
@RestController
@RequestMapping("/activity")
public class ActivityManagerController {

    Log _log = LogFactory.getLog(ActivityManagerController.class);

    @Autowired
    private ActivityManagerService activityManagerService;

    @RequestMapping("/back/find/configure/list")
    @ApiOperation(value = "查询配置活动列表", notes = "查询配置活动列表", httpMethod = "POST")
    public ResponseData<ListWithPage<ActivityManagerConfigureVo>> findList(HttpServletRequest request, HttpServletResponse response, @RequestBody ActivityManagerConfigureParams params) {
        ListWithPage<ActivityManagerConfigureVo> list = null;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = activityManagerService.advancedSearchWithCondition(params, pageable, 10217l);
        } catch (Exception e) {
            _log.error("查询抽奖列表失败", e);
        }
        return new ResponseData<ListWithPage<ActivityManagerConfigureVo>>(list);
    }

    @RequestMapping(value = "/back/configure/saveOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改活动配置", notes = "新增或修改活动配置", httpMethod = "POST")
    public ResponseData<Object> saveOrUpdate(@RequestBody ActivityManagerConfigureDto articleSectionDto){
        try{
            activityManagerService.saveOrUpdate(articleSectionDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseData<Object>("操作成功");
    }

    @RequestMapping(value = "/back/configure/{activityConfigureId}", method = RequestMethod.GET)
    @ApiOperation(value = "查看活动配置详情", notes = "查看活动配置详情", httpMethod = "GET")
    public ResponseData<ActivityManagerConfigureDetailsVo> findArticle(@PathVariable(name = "activityConfigureId") String activityConfigureId) {
        ActivityManagerConfigureDetailsVo activityManagerConfigureDetailsVo = null;
        try {
            activityManagerConfigureDetailsVo = activityManagerService.findActivityConfigureDetails(activityConfigureId);
        } catch (Exception e) {
            _log.info("文章内容发生异常:", e);
        }
        return new ResponseData<ActivityManagerConfigureDetailsVo>(activityManagerConfigureDetailsVo);
    }

    @RequestMapping(value = "/back/configure/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除活动配置(单个或批量)", notes = "删除活动配置(单个或批量)", httpMethod = "POST")
    public ResponseData<Object> delete(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = "activityConfigureId") String activityConfigureId) {
        try {
            activityManagerService.delete(activityConfigureId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData<Object>("删除成功");
    }
}
