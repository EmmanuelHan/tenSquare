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
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * 用户全部列表
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Result getUserList() {
        return userService.getUserList();
    }

    /**
     * 登陆
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Result userLogin(@RequestBody User user) {
        return userService.userLogin(user);
    }

    /**
     * 注册用户
     */
    @RequestMapping(value = "/user/register/{code}", method = RequestMethod.POST)
    public Result registerUser(@RequestBody User user,@PathVariable String code) {
        return userService.registerUser(user,code);
    }

    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public Result getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    /**
     * 修改用户
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    public Result editUser(@RequestBody User user,@PathVariable String userId) {
        return userService.editUser(user,userId);
    }

    /**
     * 根据ID删除
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    /**
     * 查询登陆用户信息
     */
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public Result getLoginUserInfo() {
        return userService.getLoginUserInfo();
    }

    /**
     * 修改当前登陆用户信息
     */
    @RequestMapping(value = "/user/saveinfo", method = RequestMethod.PUT)
    public Result editLoginUserInfo(@RequestBody User user) {
        return userService.editLoginUserInfo(user);
    }

    /**
     * 用户分页
     */
    @RequestMapping(value = "/user/search/{pageNo}/{pageSize}", method = RequestMethod.POST)
    public Result getUserListWithPage(@RequestBody User user,@PathVariable Integer pageNo,@PathVariable Integer pageSize) {
        return userService.getUserListWithPage(user,pageNo,pageSize);
    }

    /**
     * 发送手机验证码
     */
    @RequestMapping(value = "/user/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendSms(@PathVariable String mobile) {
        return userService.sendSms(mobile);
    }

    /**
     * 关注某用户
     */
    @RequestMapping(value = "/user/follow/{userId}", method = RequestMethod.PUT)
    public Result followUser(@PathVariable String userId) {
        return userService.followUser(userId);
    }

    /**
     * 删除某用户关注
     */
    @RequestMapping(value = "/user/follow/{userId}", method = RequestMethod.DELETE)
    public Result deleteFollowUser(@PathVariable String userId) {
        return userService.deleteFollowUser(userId);
    }

    /**
     * 查询我的粉丝
     */
    @RequestMapping(value = "/user/follow/myfans", method = RequestMethod.GET)
    public Result getUserFans() {
        return userService.getUserFans();
    }

    /**
     * 查询我的关注
     */
    @RequestMapping(value = "/user/follow/myfollow", method = RequestMethod.GET)
    public Result getUserFollow() {
        return userService.getUserFollow();
    }

    /**
     * 查询我的关注ID列表
     */
    @RequestMapping(value = "/user/follow/myfollowid", method = RequestMethod.GET)
    public Result getuserFollowIdList() {
        return userService.getuserFollowIdList();
    }

 }
