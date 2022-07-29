package com.tensquare.user.config.auth;

import com.tensquare.user.service.IUserService;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.annotation.Resource;

@Configuration
@EnableOAuth2Sso
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private IUserService userService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**","/css/**","/iamage/**");
    }

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

    /**
     * 认证管理提供者列表配置
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //数据库认证提供者配置
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(userService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        auth.authenticationProvider(provider);
    }

    /**
     * 认证失败地址配置
     */
    @Bean
    public AuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/error");
    }

}
