package com.tcl.uf.advert.configuration;

import com.tcl.uf.advert.filter.AdvertAccessFilter;
import com.tcl.uf.advert.shiro.AdvertRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author linhuimin@tcl.com
 * @ClassName: ShiroConfiguration
 * @Description:
 * @date 2020/8/17 18:36
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //将自己的验证方式加入容器
    @Bean
    public AdvertRealm advertRealm() {
        AdvertRealm advertRealm = new AdvertRealm();
        return advertRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(advertRealm());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("advertAccessFilter", new AdvertAccessFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        Map<String, String> map = new LinkedHashMap<>();
        //登入
        map.put("/user/back/apply", "anon");
        map.put("/user/back/oaUserInfo", "anon");
        map.put("/user/back/unauthorized", "anon");
        map.put("/user/back/onlineUserInfo", "anon");
        map.put("/resource/app/list", "anon");
        map.put("/resource/back/app/List", "anon");
        //登出
        map.put("/user/logout", "logout");
        //对所有用户认证
        map.put("/**", "advertAccessFilter");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/user/back/unauthorized");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/user/back/list");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/back/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
