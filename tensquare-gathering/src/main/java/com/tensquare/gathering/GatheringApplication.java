package com.tensquare.gathering;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class GatheringApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatheringApplication.class, args);
	}

}
