package com.tensquare.friend.feign;

import com.tensquare.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("tensquare-user")
public interface UserFeign {

    @GetMapping("/user/info")
    Result getLoginUserInfo();

    @PutMapping("/user/{userId}/{friendId}/{type}")
    public void updateFansAndFollow(@PathVariable String userId,@PathVariable String friendId,@PathVariable int type);


}
