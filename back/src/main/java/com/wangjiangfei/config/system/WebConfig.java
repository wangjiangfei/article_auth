package com.wangjiangfei.config.system;

import com.wangjiangfei.config.interceptor.AuthenticationInterceptor;
import com.wangjiangfei.config.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = {"com.wangjiangfei"})
//@EnableScheduling
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(permissionInterceptor())
                .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @RequiresPermission 注解 决定是否需要验证权限
        super.addInterceptors(registry);
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }
}
