package com.hu.zhcc.shiro.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，是否忽略RestFul安全检查
 * Created by hulichao on 2018/7/9.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSecurity {

}
