package com.tensquare.article.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("tensquare-user")
public interface UserClient {


}
