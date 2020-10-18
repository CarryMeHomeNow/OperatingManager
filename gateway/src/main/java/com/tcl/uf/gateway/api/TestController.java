package com.tcl.uf.gateway.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc :
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/9/3 16:44
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "SUCCESS";
    }
}
