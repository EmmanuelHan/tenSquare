package com.tensquare.user;

import com.tensquare.common.aspect.PermissionHandle;
import com.tensquare.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Slf4j//日志
@SpringBootApplication//启动类
@EnableEurekaClient//EurekaClient
@EnableDiscoveryClient//Feign 远程调用
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

    @Bean
    public PermissionHandle permissionHandle(){
        return new PermissionHandle();
    }

}
