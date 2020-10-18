package com.tcl.commonservice.service;

import com.tcl.uf.common.base.dto.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-mail")
public interface MailService {

    @RequestMapping("/demo/find/{id}")
    ResponseData findById(@PathVariable(name = "id") Long id);
}
