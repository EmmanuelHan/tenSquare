package com.tensquare.qa;

import com.tensquare.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.tensquare.qa.mapper")
public class QaApplication {

    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class,args);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

}
