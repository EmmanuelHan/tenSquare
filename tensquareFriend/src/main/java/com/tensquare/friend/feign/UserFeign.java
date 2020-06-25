package com.tensquare.friend.feign;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient("tensquare-user")
public interface UserFeign {

    @GetMapping("/user/info")
    Result getLoginUserInfo();

    @RequestMapping("/login")
    Result login(@RequestParam String username,@RequestParam String password);
}
