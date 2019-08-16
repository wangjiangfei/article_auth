package com.wangjiangfei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author wangjiangfei
 * @date 2019/5/29 9:20
 * @description SpringBoot启动类
 */
@SpringBootApplication
@MapperScan("com.wangjiangfei.dao")
public class MyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

}
