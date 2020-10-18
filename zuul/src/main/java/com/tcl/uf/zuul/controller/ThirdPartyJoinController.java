package com.tcl.uf.zuul.controller;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.zuul.model.ThirdPartyJoinVo;
import com.tcl.uf.zuul.repository.ThirdPartyJoinVoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: RefreshController
 * @Description: 更新或者增加网关的路由使用
 * @date 2018/1/30 上午5:32
 */

@RestController
@RequestMapping("/thdpj")
public class ThirdPartyJoinController {

    private static final Logger log = LoggerFactory.getLogger(ThirdPartyJoinController.class);

    @Autowired
    ThirdPartyJoinVoRepository thirdPartyJoinVoRepository;

    /**
     * 第三方接入配置页面
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("/list")
    public void listHtml(HttpServletResponse response) throws IOException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("template/thirdpartyjoin.html");
        ServletOutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

        response.setHeader("Content-type", "text/html;charset=utf-8");
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }


    /**
     * 获取路由配置规则
     */
    @RequestMapping("/get")
    public List<ThirdPartyJoinVo> getList() {

        List<ThirdPartyJoinVo> all = thirdPartyJoinVoRepository.findAll();

        log.info(JSON.toJSONString(all));
        return all;
    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public ResponseData updateRouter(@RequestBody List<ThirdPartyJoinVo> body) {
//        List<ThirdPartyJoinVo> list = new ArrayList<>();
        for (ThirdPartyJoinVo item : body) {
            if (StringUtils.isEmpty(item.getAppid()) || StringUtils.isEmpty(item.getAppSecret())) {
                return new ResponseData(2, "设置不合法", "");
            }
            // upgrade (hepeng)
            thirdPartyJoinVoRepository.save(item);
//            list.add(item);
        }

        thirdPartyJoinVoRepository.deleteAll();

//        thirdPartyJoinVoRepository.save(list);

        thirdPartyJoinVoRepository.flush();

        return new ResponseData();
    }
    
    /**
     * 获取路由配置规则
     */
    @RequestMapping("/genAppid")
    public Map<String,String> genAppid() {

        Map<String, String> map = new HashMap<>();
        String appid = this.getRandomFileName();
        map.put("appid", appid);
        map.put("appSecret", UUID.randomUUID().toString());

        return map;
    }

    
    public String getRandomFileName() {


        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;

        return str + rannum;
    }
    
}
