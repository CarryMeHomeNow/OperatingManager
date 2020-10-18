package com.tcl.uf.common.base.util.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Desc : SpringBean工具类，Spring容器启动后使用
 * @Author Mr.HePeng
 * @Date 2020/8/10 15:25
 */
@Service
public class SpringBeanUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String className) {
        return applicationContext.getBean(className);
    }

    public <T> T getBean(Class<T> cls) {
        return applicationContext.getBean(cls);
    }
}
