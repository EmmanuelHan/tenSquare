package com.tensquare.qa.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        //注册拦截器，要声明拦截器对象和拦截的请求

        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
        .excludePathPatterns("/**/login");
    }
}
