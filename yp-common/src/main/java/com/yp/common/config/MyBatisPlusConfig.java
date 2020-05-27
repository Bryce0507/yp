package com.yp.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MyBatisPlusConfig
 * @Author lihaodong
 * @Date 2019/1/10 18:02
 * @Mail lihaodongmail@163.com
 * @Description Mybatis-Plus配置
 * @Version 1.0
 **/

@EnableTransactionManagement
@Configuration
@MapperScan({"com.yp.**.mapper"})
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
