package com.tcl.uf.member.annotation;

import java.lang.annotation.*;

/**
 * @author youyun.xu
 * @ClassName: InitGrowth
 * @Description: 成长值实例化注解
 * @date 2020/9/3 9:19
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InitGrowth {

}
