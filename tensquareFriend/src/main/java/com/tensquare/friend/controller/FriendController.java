package com.tensquare.friend.controller;

import com.tensquare.friend.feign.UserFeign;
import com.tensquare.friend.service.IFriendService;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author HanLei
 * @Date 2020-06-19
 */
@Slf4j
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource
    private IFriendService friendService;

    @Resource
    private UserFeign userFeign;

    @PutMapping("/like/{friendid}/{type}")
    public Result addRelationship(@PathVariable String friendId, @PathVariable String type) throws Exception {
        return friendService.addRelationship(friendId, type);
    }

    @GetMapping("/loginInfo")
    public Result getLoginInfo(){
        return userFeign.getLoginUserInfo();
    }

    @RequestMapping("/login")
    public Result login(@RequestParam String username,@RequestParam String password){
        return userFeign.login(username,password);
    }


}
