package com.tensquare.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class BaseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }

    @Value("${project.name:tensquare-base}")
    private String projectName;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(projectName);
    }
}
