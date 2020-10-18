package com.tcl.uf.content.annotation;

import java.lang.annotation.*;

/**
 * @author youyun.xu
 * @ClassName: ArticleOperational
 * @Description: 切面管理文件操作
 * @date 2020/9/3 9:19
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArticleOperational {

    String action() default "";
}
