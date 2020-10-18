package com.tcl.uf.tangram.controller.demo;

import com.tcl.uf.tangram.service.ThirdShopContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @Desc :
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/9/5 15:36
 */
@Service
@EnableAsync
public class AsyncService {

    @Autowired
    private ThirdShopContentService thirdShopContentService;

    public Future<String> test() {
        return new AsyncResult<>(thirdShopContentService.getIndexPageInfo("APP"));
    }
}
