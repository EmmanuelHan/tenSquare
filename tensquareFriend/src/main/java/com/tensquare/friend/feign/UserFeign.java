package com.tensquare.friend.feign;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tensquear-user")
public interface UserFeign {

    @GetMapping("/user/info")
    Result getLoginUserInfo();
}
