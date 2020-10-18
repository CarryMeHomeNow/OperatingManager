package com.tcl.uf.member.service;

import com.tcl.uf.member.dto.HardwareDeviceGrowthValueDto;
import com.tcl.uf.member.dto.ServiceGrowthValueDto;

public interface MemberGrowthService {

    String hardwareDevice(HardwareDeviceGrowthValueDto growthValueDto);

    String softwareService(ServiceGrowthValueDto growthValueDto);
}
