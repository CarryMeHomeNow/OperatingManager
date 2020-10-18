package com.tcl.uf.gateway.cache.impl;

import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Desc : invoke tangram server
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/31 16:27
 */
@Service
public class TangramServerHandler {

    @Autowired
    private RestTemplate restTemplate;

    public String invokeTangramServer(TangramTemplateConfig config) {
        // TODO
        return "123";
    }
}
