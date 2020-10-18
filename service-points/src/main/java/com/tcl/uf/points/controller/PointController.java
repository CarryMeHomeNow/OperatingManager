package com.tcl.uf.points.controller;


import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.points.service.PointService;
import com.tcl.uf.points.vo.IntegralDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/point")
public class PointController extends AbstractBaseController {
    @Autowired
    private PointService pointService;

    @RequestMapping("/getPoint/{token}")
    public ResponseData<Integer> getPointsBySSOID(@PathVariable("token") String token) throws JMException {
        TokenAppUserInfo appUserInfo = getAppUserInfo(token);
        Integer point = pointService.getPointBySSOID(token, appUserInfo.getAccountId());
        return success(point);
    }

    /**
     * 积分流推送
     *
     * @param accountId   用户id
     * @param score       积分值
     * @param description 描述
     * @param source 来源
     * @param opType 操作类型 +或-
     * @param sourceType 来源类型
     * @param growValue  成长值
     * @return
     */
    @RequestMapping("/point/pushPointsFlow")
    public ResponseData<String> pushPoints( @RequestParam("token") String token,
                                            @RequestParam("accountId") Long accountId,
                                            @RequestParam("score") Integer score,
                                            @RequestParam("description") String description,
                                            @RequestParam("source") String source,
                                            @RequestParam("opType") String opType,
                                            @RequestParam("sourceType") String sourceType,
                                            @RequestParam("growValue") Integer growValue){
        boolean result = pointService.pushPoints(token,accountId,score,description,source,opType,sourceType,growValue);
        if(!result){
            fail("推送失败");
        }
        return success();
    }

    @RequestMapping("/getPointDetail")
    public List<IntegralDetailVo> getPointDetail(@RequestParam String date,
                                                 @RequestParam(defaultValue = "0") Integer form,
                                                 @RequestParam(defaultValue = "10") Integer size,
                                                 HttpServletRequest request) throws JMException {
//        TokenAppUserInfo appUserInfo = getAppUserInfo(request);
        List<IntegralDetailVo> result = pointService.getPointDetail(/*appUserInfo.getAccountId()*/Long.valueOf("1282580499042140200"), date, form, size, "00"/*appUserInfo.getToken()*/);
        return result;
    }
}
