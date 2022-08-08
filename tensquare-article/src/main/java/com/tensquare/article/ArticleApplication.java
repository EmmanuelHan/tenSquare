package com.tensquare.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author EmmanuelHan
 */
@EnableScheduling
@SpringBootApplication
@EnableEurekaClient
public class ArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class, args);
	}

//	@Bean
//	public JwtUtil jwtUtil(){
//		return new JwtUtil();
//	}
//
//	@Bean
//	public PermissionHandle permissionHandle(){
//		return new PermissionHandle();
//	}

}
