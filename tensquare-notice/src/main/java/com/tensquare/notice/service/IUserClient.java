package com.tensquare.notice.service;

import com.tensquare.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("tensquare-user")
public interface IUserClient {

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    @GetMapping("user/{userId}")
    Result selectById(@PathVariable("userId") String userId);
}
