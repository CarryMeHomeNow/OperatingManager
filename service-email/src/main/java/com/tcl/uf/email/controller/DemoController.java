package com.tcl.uf.email.controller;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.email.dto.DemoDto;
import com.tcl.uf.email.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController extends AbstractBaseController {
    @Autowired
    private DemoService demoService;
    @GetMapping("/find/{id}")
    public ResponseData findById(@PathVariable(name = "id") Long id){
        return success(demoService.findById(id));
    }
    @GetMapping("/save")
    public ResponseData save(){
        DemoDto demoDto = new DemoDto();
        demoDto.setAge(1);
        demoDto.setCity("苏州");
        demoDto.setName("小晓");
        return success(demoService.save(demoDto));
    }
}
