package com.tcl.uf.member.controller;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.member.dto.HardwareDeviceGrowthValueDto;
import com.tcl.uf.member.dto.ServiceGrowthValueDto;
import com.tcl.uf.member.service.MemberGrowthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author youyun.xu
 * @ClassName: MemberGrowthController
 * @Description: 会员成长值管理
 * @date 2020/8/31 9:55
 */
@Api("会员成长值管理")
@RequestMapping("/growth")
@RestController
public class MemberGrowthController {

    @Autowired
    private MemberGrowthService memberGrowthService;

    @ApiOperation(value = "硬件设备关联获取", notes = "硬件关联获取", httpMethod = "POST")
    @PostMapping("/hardware/device/association")
    public ResponseData<Object> hardwareDevice(@RequestBody HardwareDeviceGrowthValueDto growthValueDto) {
        String resultTest = memberGrowthService.hardwareDevice(growthValueDto);
        return new ResponseData<Object>(resultTest);
    }

    @ApiOperation(value = "软件服务及内容购买获取", notes = "软件服务及内容购买获取", httpMethod = "POST")
    @PostMapping("/service/consumption")
    public ResponseData<Object> softwareService(@RequestBody ServiceGrowthValueDto growthValueDto) {
        String resultTest = memberGrowthService.softwareService(growthValueDto);
        return new ResponseData<Object>(resultTest);
    }
}
