package com.tcl.uf.points.controller;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.points.service.impl.TaskCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 积分任务完成control
 */
@RestController
@RequestMapping("/complete")
public class PointTaskCompleteController extends AbstractBaseController {
    @Autowired
    private TaskCompleteService taskCompleteService;

    @GetMapping("/reach/{type}")
    public ResponseData getPointsBySSOID(@PathVariable("type") Integer type, HttpServletRequest request) throws JMException {
        TokenAppUserInfo appUser = getAppUserInfo(request);
        taskCompleteService.complete(type,appUser);
        return success();
    }
}
