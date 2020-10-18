package com.tcl.uf.content.controller;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.content.service.ArticleContentService;
import com.tcl.uf.content.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController("commonController")
@RequestMapping("/common")
public class CommonController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ArticleContentService articleContentService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping(value = "/article/addBrowseNum")
    public ResponseData<Object> addBrowseNum() {
        ResponseData<Object> responseData;
        try {
            articleContentService.addBrowseNum();
            responseData = new ResponseData<>();
        } catch (Exception e) {
            log.error("增加浏览数失败", e);
            responseData = new ResponseData<>(0, "增加浏览数失败", "failed");
        }
        return responseData;
    }

    /**
     * 清除缓存（手动）
     *
     * @param key
     * @return
     */
    @GetMapping(value = "/clearCache/{key}")
    public ResponseData<Object> clearCache(@PathVariable("key") String key) {
        ResponseData<Object> responseData;
        try {
            Set<String> keys = redisUtils.keys(key);
            for (String k : keys) {
                log.info("删除缓存：{}", k);
                redisUtils.del(k);
            }
            responseData = new ResponseData<>();
        } catch (Exception e) {
            log.error("清除缓存失败", e);
            responseData = new ResponseData<>(0, "清除缓存失败", "failed");
        }
        return responseData;
    }
}
