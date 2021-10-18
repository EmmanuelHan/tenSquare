//package com.tensquare.base.config.auth;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//
///**
// * @文件名称: WebSecurityConfig.java
// * @功能描述: WebSecurity安全配置
// * @版权信息： www.easystudy.com
// * @技术交流： 961179337(QQ群)
// * @编写作者： lixx2048@163.com
// * @联系方式： 941415509(QQ)
// * @开发日期： 2020年7月26日
// * @备注信息： 任何需要身份验证的请求都将被重定向到授权服务器
// */
//@Configuration
//@EnableOAuth2Sso
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     * @功能描述: 受保护资源访问策略配置
//     * @编写作者： lixx2048@163.com
//     * @开发日期： 2020年7月26日
//     * @历史版本： V1.0
//     * @参数说明：
//     * @返  回  值：
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 资源访问安全策略
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
//        registry
//                .anyRequest()
//                .authenticated()
//                // 跨域请求配置
//                .and()
//                .csrf().disable();
//    }
//
//    /**
//     * @功能描述: 静态资源忽略放行配置
//     * @编写作者： lixx2048@163.com
//     * @开发日期： 2020年7月26日
//     * @历史版本： V1.0
//     * @参数说明：
//     * @返  回  值：
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 放行静态资源，否则添加oauth2情况下无法显示
//        web.ignoring().antMatchers("/favor.ico",
//                "/favicon.ico",
//                "/v2/api-docs",
//                "/swagger-resources/configuration/ui",
//                "/swagger-resources",
//                "/swagger-resources/configuration/security",
//                "/swagger-ui.html",
//                "/css/**",
//                "/js/**",
//                "/images/**",
//                "/webjars/**",
//                "**/favicon.ico",
//                "/index");
//    }
//
//}
