package com.tcl.commonservice.service;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-activity")
public interface ActivityService {


}
