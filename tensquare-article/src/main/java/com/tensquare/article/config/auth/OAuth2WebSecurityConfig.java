package com.tensquare.article.config.auth;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 任何配置都需要登录认证
                .antMatchers("/oauth/**", "/login/**", "/logout/**").permitAll()
                .anyRequest().authenticated()
                // 登录地址配置以及登录成功默认主页配置
                .and()
                .formLogin()
//                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                // 登出接口权限配置
                .and().logout().permitAll()
                // 跨域请求配置
                .and()
                .csrf().disable()
                // 进行http Basic认证
                .httpBasic();
    }



}
