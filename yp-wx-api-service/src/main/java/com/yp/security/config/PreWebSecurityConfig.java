package com.yp.security.config;

import com.yp.security.UserDetailsServiceImpl;
import com.yp.security.filter.PreJwtAuthenticationTokenFilter;
import com.yp.security.handle.PreAccessDeineHandler;
import com.yp.security.handle.PreAuthenticationEntryPointImpl;
import com.yp.security.handle.PreAuthenticationFailureHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Classname WebSecurityConfig
 * @Description Security配置类
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-05-07 09:10
 * @Version 1.0
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PreWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PreAuthenticationFailureHandler preAuthenticationFailureHandler;

    @Autowired
    private PreJwtAuthenticationTokenFilter preJwtAuthenticationTokenFilter;


    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置策略
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 短信登录配置
//                .apply(smsCodeAuthenticationSecurityConfig).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                // 对于登录login 图标 要允许匿名访问
                .antMatchers("/login/**","/openid","/register/**").anonymous()
                .antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js")
                .permitAll()
                .antMatchers("/file/**")
                .permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
        // 添加自定义异常入口
        httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(new PreAuthenticationEntryPointImpl())
                .accessDeniedHandler(new PreAccessDeineHandler());


        // 添加JWT filter 用户名登录
        httpSecurity
                // 添加JWT验证过滤器
                .addFilterBefore(preJwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

