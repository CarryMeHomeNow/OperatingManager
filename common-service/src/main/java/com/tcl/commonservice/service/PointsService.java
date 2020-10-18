package com.tcl.commonservice.service;

import com.tcl.commonservice.service.vo.IntegralDetailVo;
import com.tcl.commonservice.service.vo.PointTaskDetailVo;
import com.tcl.commonservice.service.vo.SignInVo;
import com.tcl.uf.common.base.dto.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("service-points")
public interface PointsService {
    /**
     * 查询用户积分
     *
     * @param token 统一登录平台的token
     * @return 积分值
     */
    @RequestMapping("/point/getPoint/{token}")
    public ResponseData<Integer> getPointsBySSOID(@PathVariable(value="token") String token);

    /**
     * 积分流推送
     * @param accountId 用户id
     * @param score 积分值 不加传0
     * @param description 描述
     * @param source 来源
     * @param opType 操作类型 +或-
     * @param sourceType 来源类型
     * @param token
     * @param growValue 成长值 不加传0
     * @return 成功 code = 1  失败 code = -1 msg：失败原因
     */
    @RequestMapping("/point/point/pushPointsFlow")
    public ResponseData<String> pushPoints( @RequestParam("token") String token,
                                            @RequestParam("accountId") Long accountId,
                                            @RequestParam("score") Integer score,
                                            @RequestParam("description") String description,
                                            @RequestParam("source") String source,
                                            @RequestParam("opType") String opType,
                                            @RequestParam("sourceType") String sourceType,
                                            @RequestParam("growValue") Integer growValue);

    /**
     * 任务列表
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/task/list")
    ResponseData<Map<String, Object>> findAll(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size);

    /**
     * 任务详情
     * @param id
     * @return
     */
    @RequestMapping("/task/app/v1/task/detail/{id}")
    ResponseData<PointTaskDetailVo> findDetail(@PathVariable(value = "id") Long id);

    /**
     * 优惠券列表
     * @param map
     * @return
     */
    @RequestMapping("/list")
    ResponseData list(@RequestBody Map<String, Integer> map);

    /**
     * 获取积分明细
     *
     * @param date      2020-09-11 支持年月  年月日
     * @param form
     * @param size
     * @return
     */
    @RequestMapping("/point/getPointDetail")
    List<IntegralDetailVo> getPointDetail(@RequestParam("date") String date,
                                          @RequestParam(value = "form",defaultValue = "0") Integer form,
                                          @RequestParam(value = "size",defaultValue = "10") Integer size);

    /**
     * 签到天数
     * @return
     */
    @RequestMapping(value = "/signIn/app/querySignRecord")
    ResponseData<List<SignInVo>> querySignRecord();
}
