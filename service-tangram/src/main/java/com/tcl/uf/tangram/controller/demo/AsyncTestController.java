package com.tcl.uf.tangram.controller.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @Desc : Async invoke demo
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/9/5 15:44
 */
@RestController
public class AsyncTestController {

    @Autowired
    private AsyncService AsyncService;

    @RequestMapping("/asyncTest")
    public String test() throws ExecutionException, InterruptedException {
        return AsyncService.test().get();
    }
}
