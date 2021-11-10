package com.tensquare.friend.controller;

import com.tensquare.friend.feign.UserFeign;
import com.tensquare.friend.service.IFriendService;
import com.tensquare.common.entity.Result;
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
    public Result getLoginInfo() throws Exception{
        return userFeign.getLoginUserInfo();
    }

    /**
     * 删除好友
     * @param friendId
     * @return
     */
    @DeleteMapping("/{friendId}")
    public Result deleteFriend(@PathVariable String friendId) throws Exception{
        return friendService.deleteFriend(friendId);
    }


}
