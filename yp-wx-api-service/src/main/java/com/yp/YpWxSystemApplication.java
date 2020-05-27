package com.yp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 系统入口
 */
@MapperScan({"com.yp.dao"})
@SpringBootApplication
@ServletComponentScan
public class YpWxSystemApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YpWxSystemApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(YpWxSystemApplication.class, args);
    }

}
