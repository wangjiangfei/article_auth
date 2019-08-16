package com.wangjiangfei.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在需要登录验证的Controller的方法上使用此注解
 *
 * ElementType.METHOD 表示该自定义注解可以用在方法上
 * RetentionPolicy.RUNTIME 表示该注解在代码运行时起作用
 *
 * @author wangjiangfei
 * @version 1.0
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
}

