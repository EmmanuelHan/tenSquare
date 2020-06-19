package com.tensquare.friend.controller;

import com.alibaba.fastjson.JSON;
import com.tensquare.friend.feign.UserFeign;
import com.tensquare.friend.service.IFriendService;
import com.tensquare.friend.entity.Friend;
import entity.Result;
import entity.ResultEnum;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

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

    public Result addFriend(@PathVariable String friendId, @PathVariable String type) throws Exception {
        return friendService.addFriend(friendId, type);
    }


}
