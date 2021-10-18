package com.tensquare.user.config.auth;

import com.tensquare.user.service.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private IUserService userService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //全注解配置的开端，表示开始说明需要的权限
                //需要的权限分两部分，第一部分是拦截的路径，第二部分是访问该路径所需要的权限
                .authorizeRequests()
                //所有路径都拦截  通行所有
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

    }


    /**
     * @功能描述: 自定义认证管理器配置
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年7月26日
     * @历史版本： V1.0
     * @参数说明：
     * @返 回  值：
     */
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    /**
     * @功能描述: 受保护资源访问策略配置
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年7月26日
     * @历史版本： V1.0
     * @参数说明：
     * @返 回  值：
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 资源访问安全策略
////        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
//        http
//                .authorizeRequests()
//                // 任何配置都需要登录认证
//                .antMatchers("/oauth/**")
//                	.permitAll()
//                .anyRequest()
//                .authenticated()
//                // 登录地址配置以及登录成功默认主页配置
//                .and()
//                .formLogin()
////                .loginPage("/login")
//                .defaultSuccessUrl("/home")
//                .permitAll()
//                // 登出接口权限配置
//                .and()
//                . logout().permitAll()
//                // 跨域请求配置
//                .and()
//                .csrf().disable()
//                // 进行http Basic认证
//                .httpBasic();
//
//
////        http.requestMatchers().anyRequest()
////                .and()
////                .authorizeRequests()
////                .antMatchers("/oauth/**").permitAll();
//
////        http.authorizeRequests()
////                .antMatchers("/**").permitAll();
//    }

    /**
     * 认证管理提供者列表配置
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //数据库认证提供者配置
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        // 设置userDetailsService
//        provider.setUserDetailsService(userService);
//        // 禁止隐藏用户未找到异常
//        provider.setHideUserNotFoundExceptions(false);
//        // 使用BCrypt进行密码的hash
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        auth.authenticationProvider(provider);
//    }

    /**
     * 认证失败地址配置
     */
//    @Bean
//    public AuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
//        return new SimpleUrlAuthenticationFailureHandler("/error");
//    }

}
