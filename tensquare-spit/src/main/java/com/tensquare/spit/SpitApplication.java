package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import com.tensquare.common.util.SnowFlakeIdGenerator;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class SpitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class,args);
    }

    @Bean
    public SnowFlakeIdGenerator idGenerator(){
        return new SnowFlakeIdGenerator(1,1);
    }
}
