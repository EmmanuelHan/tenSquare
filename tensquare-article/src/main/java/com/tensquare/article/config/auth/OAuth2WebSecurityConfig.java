package com.tensquare.article.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//            http.logout().logoutSuccessUrl("http://localhost:8080/logout");
//            http.authorizeRequests().anyRequest().authenticated();
//            http.csrf().disable();
//    }

    /**
     * 安全拦截机制（最重要）
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //所有/r/**的请求必须认证通过
                .antMatchers("/channel/**").authenticated()
                //除了/r/**，其它的请求可以访问
                .anyRequest().permitAll();
    }



}
