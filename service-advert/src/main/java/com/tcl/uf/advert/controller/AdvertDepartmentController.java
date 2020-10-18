package com.tcl.uf.advert.controller;

import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.service.AdvertDepartmentService;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.JMException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertDepartmentController
 * @Description:广告业务方管理
 * @date 2020/9/02 14:48
 */
@Api(value = "广告业务方管理")
@RestController
@RequestMapping("/department")
public class AdvertDepartmentController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(AdvertDepartmentController.class);

    @Autowired
    AdvertDepartmentService advertDepartmentService;

    @GetMapping(value = "/back/titleList")
    @ApiOperation(value = "业务方选择列表", notes = "业务方选择列表", httpMethod = "GET")
    public ResponseData<Map<String, Long>> findList(HttpServletRequest request) {
        Map<String, Long> departmentMap;
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.hasRole(AdvertConstants.ADVERT_ROLE_ADMIN)) {
                departmentMap = advertDepartmentService.getDepartmentTitleMap();
            }else{
                departmentMap = advertDepartmentService.getDepartmentTitleMapByType(AdvertConstants.DEPARTMENT_TYPE_BUSINESS);
            }
        } catch (JMException e) {
            _log.error("获取业务方选择列表异常:", e);
            return fail(e.getMessage());
        }
        return success(departmentMap);
    }

}
