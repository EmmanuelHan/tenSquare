package com.tensquare.user.controller;

import com.tensquare.user.entity.User;
import com.tensquare.user.service.IUserService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author HanLei
 * @Date   2020-03-17
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private IUserService  userService;

    /**
     * 增加用户
     */
    @PostMapping("/user")
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * 用户全部列表
     */
    @GetMapping("/user")
    public Result getUserList() {
        return userService.getUserList();
    }

    /**
     * 登陆
     */
    @PostMapping("/user/login")
    public Result userLogin(@RequestBody User user) {
        return userService.userLogin(user);
    }

    /**
     * 注册用户
     */
    @PostMapping("/user/register/{code}")
    public Result registerUser(@RequestBody User user,@PathVariable String code) {
        return userService.registerUser(user,code);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/user/{userId}")
    public Result getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    /**
     * 修改用户
     */
    @PutMapping("/user/{userId}")
    public Result editUser(@RequestBody User user,@PathVariable String userId) {
        return userService.editUser(user,userId);
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping("/user/{userId}")
    public Result deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    /**
     * 查询登陆用户信息
     */
    @GetMapping("/user/info")
    public Result getLoginUserInfo() {
        return userService.getLoginUserInfo();
    }

    /**
     * 修改当前登陆用户信息
     */
    @PutMapping("/user/saveinfo")
    public Result editLoginUserInfo(@RequestBody User user) {
        return userService.editLoginUserInfo(user);
    }

    /**
     * 用户分页
     */
    @PostMapping("/user/search/{pageNo}/{pageSize}")
    public Result getUserListWithPage(@RequestBody User user,@PathVariable Integer pageNo,@PathVariable Integer pageSize) {
        return userService.getUserListWithPage(user,pageNo,pageSize);
    }

    /**
     * 发送手机验证码
     */
    @PostMapping("/user/sendsms/{mobile}")
    public Result sendSms(@PathVariable String mobile) {
        return userService.sendSms(mobile);
    }

    /**
     * 关注某用户
     */
    @PutMapping("/user/follow/{userId}")
    public Result followUser(@PathVariable String userId) {
        return userService.followUser(userId);
    }

    /**
     * 删除某用户关注
     */
    @DeleteMapping("/user/follow/{userId}")
    public Result deleteFollowUser(@PathVariable String userId) {
        return userService.deleteFollowUser(userId);
    }

    /**
     * 查询我的粉丝
     */
    @GetMapping("/user/follow/myfans")
    public Result getUserFans() {
        return userService.getUserFans();
    }

    /**
     * 查询我的关注
     */
    @GetMapping("/user/follow/myfollow")
    public Result getUserFollow() {
        return userService.getUserFollow();
    }

    /**
     * 查询我的关注ID列表
     */
    @GetMapping("/user/follow/myfollowid")
    public Result getuserFollowIdList() {
        return userService.getuserFollowIdList();
    }

    /**
     * 更新好友粉丝数和关注数
     * @param userId
     * @param friendId
     * @param type
     */
    @PutMapping("/user/{userId}/{friendId}/{type}")
    public void updateFansAndFollow(@PathVariable String userId,@PathVariable String friendId,@PathVariable int type){
        userService.updateFansAndFollow(userId,friendId,type);
    }

 }
