package com.tensquare.user.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tensquare.user.security.MyCustomUserService;
import entity.Result;
import entity.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//全局
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyCustomUserService userService;

    @Resource
    private BCryptPasswordEncoder encoder;


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //下面这两行配置表示在内存中配置了两个用户
//        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
////                .inMemoryAuthentication()
////                .withUser("admin").roles("admin").password("$2a$10$fvadSjCctxdtZV86pCsDLeSH37jHrcOHEi4bFsk5dSTWYk.oecKSq")
////                .and()
////                .withUser("user").roles("user").password("$2a$10$q195/UObnjCia7aNZrmU1uachgdZcyfLcfp3oYOAA/YQAwByluNX6");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())
                .httpBasic()
                //未登录时，进行json格式的提示，很喜欢这种写法，不用单独写一个又一个的类
                .authenticationEntryPoint((request, response, authException) -> {
                    writeToWeb(response, new Result(ResultEnum.NOT_LOGIN));
                })
                .and()
                .authorizeRequests()
                .anyRequest().authenticated() //必须授权才能范围
                .and()
                .formLogin() //使用自带的登录
                .permitAll()
                //登录失败，返回json
                .failureHandler((request, response, ex) -> {
                    System.out.println(ex);
                    if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                        writeToWeb(response, new Result(ResultEnum.ACCOUNT_ERROR));
                    } else if (ex instanceof DisabledException) {
                        writeToWeb(response, new Result(ResultEnum.ACCOUNT_FORBIDDEN));
                    } else if (ex instanceof CredentialsExpiredException) {
                        writeToWeb(response, new Result(ResultEnum.ACCOUNT_PASSWOED_OVERDUE));
                    } else if (ex instanceof LockedException) {
                        writeToWeb(response, new Result(ResultEnum.ACCESS_LOCK));
                    } else {
                        writeToWeb(response, new Result(ResultEnum.LOGIN_ERROR));
                    }
                })
                //登录成功，返回json
                .successHandler((request, response, authentication) -> {
                    writeToWeb(response, new Result(ResultEnum.SUCCESS));
                })
                .and()
                .exceptionHandling()
                //没有权限，返回json
                .accessDeniedHandler((request, response, ex) -> {
                    writeToWeb(response, new Result(ResultEnum.ACCESS_DENIED));
                })
                .and()
                .logout()
                //退出成功，返回json
                .logoutSuccessHandler((request, response, authentication) -> {
                    writeToWeb(response, new Result(ResultEnum.LOGOUT));
                })
                .permitAll();
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(userService);
        System.out.println(encoder);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui", "/configuration/security");
    }

    public static void writeToWeb(HttpServletResponse response, Result result) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }

}
