package com.tensquare.article.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.SnowFlakeIdGenerator;

@SpringBootApplication
public class SpitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class,args);
    }

    @Bean
    public SnowFlakeIdGenerator idGenerator(){
        return new SnowFlakeIdGenerator(1,1);
    }
}
