package com.tensquare.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.SnowFlakeIdGenerator;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class RecruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class,args);
    }

}
