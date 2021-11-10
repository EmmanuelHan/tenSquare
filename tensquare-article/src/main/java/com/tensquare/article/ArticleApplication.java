package com.tensquare.article;

import com.tensquare.common.aspect.PermissionHandle;
import com.tensquare.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author EmmanuelHan
 */
@SpringBootApplication
@EnableEurekaClient
public class ArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class, args);
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
