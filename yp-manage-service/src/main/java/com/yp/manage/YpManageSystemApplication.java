package com.yp.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统入口
 */
@MapperScan({"com.yp.dao"})
@ComponentScan(basePackages = {"com.yp"})
@SpringBootApplication
@ServletComponentScan
public class YpManageSystemApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YpManageSystemApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(YpManageSystemApplication.class, args);
    }

}
