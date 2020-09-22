package com.tensquare.spit.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 注意：从网关经过的所有url都进行过滤，情况分为如下两种：
     * 1、带access_token的参数url，过滤器会获取参数到授权中心去鉴权
     * 2、不带access_token的url，过滤器会获取本地‘资源服务’鉴权配置--即如下方法(或注解形式配置)
     * 注意“**”的使用, 使用不好可能导致权限控制失效！！！（如果url前面无单词如/oauth/...，但是匹配路径用** /oauth，就会导致权限控制失效)
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 其他匹配的[剩下的]任何请求都需要授权
//    	ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        http.authorizeRequests()//配置请求级别的安全细节
                //任何请求都必须认证后访问
                .anyRequest().authenticated()
//                .and()
//                .formLogin()
                //跨域请求
                .and()
                .csrf().disable()
                // 进行http Basic认证
                .httpBasic();
    }

}
