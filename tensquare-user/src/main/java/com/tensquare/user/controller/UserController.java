package com.tensquare.user.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
import com.tensquare.common.util.StringUtil;
import com.tensquare.user.entity.User;
import com.tensquare.user.entity.UserResultEnum;
import com.tensquare.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @Author HanLei
 * @Date 2020-03-17
 */
@Slf4j
@RestController("user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 增加用户
     */
    @PostMapping
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * 用户全部列表
     */
    @GetMapping
    public Result getUserList() {
        return userService.getUserList();
    }

    /**
     * 登陆
     */
    @PostMapping("/login")
    public Result userLogin(@RequestBody User user) {
        return userService.userLogin(user);
    }

    /**
     * 注册用户
     */
    @PostMapping("/register/{code}")
    public Result registerUser(@RequestBody User user, @PathVariable String code) {
        return userService.registerUser(user, code);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{userId}")
    public Result getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    /**
     * 修改用户
     */
    @PutMapping("/{userId}")
    public Result editUser(@RequestBody User user, @PathVariable String userId) {
        return userService.editUser(user, userId);
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    /**
     * 修改当前登陆用户信息
     */
    @PutMapping("/saveInfo")
    public Result editLoginUserInfo(@RequestBody User user) {
        return userService.editLoginUserInfo(user);
    }

    /**
     * 用户分页
     */
    @PostMapping("/search/{pageNo}/{pageSize}")
    public Result getUserListWithPage(@RequestBody User user, @PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        if (ObjectUtils.isEmpty(pageNo)) {
            pageNo = StringUtil.START_PAGE;
        }
        if (ObjectUtils.isEmpty(pageSize)) {
            pageSize = StringUtil.PAGE_SIZE;
        }
        return userService.getUserListWithPage(user, pageNo, pageSize);
    }

    /**
     * 发送手机验证码
     */
    @PostMapping("/sendsms/{mobile}")
    public Result sendSms(@PathVariable String mobile) {
        return userService.sendSms(mobile);
    }

    /**
     * 关注某用户
     */
    @PutMapping("/follow/{userId}")
    public Result followUser(@PathVariable String userId) {
        return userService.followUser(userId);
    }

    /**
     * 删除某用户关注
     */
    @DeleteMapping("/follow/{userId}")
    public Result deleteFollowUser(@PathVariable String userId) {
        return userService.deleteFollowUser(userId);
    }

    /**
     * 查询我的粉丝
     */
    @GetMapping("/follow/myfans")
    public Result getUserFans() {
        return userService.getUserFans();
    }

    /**
     * 查询我的关注
     */
    @GetMapping("/follow/myfollow")
    public Result getUserFollow() {
        return userService.getUserFollow();
    }

    /**
     * 查询我的关注ID列表
     */
    @GetMapping("/follow/myfollowid")
    public Result getuserFollowIdList() {
        return userService.getuserFollowIdList();
    }

    /**
     * 更新好友粉丝数和关注数
     *
     * @param userId
     * @param friendId
     * @param type
     */
    @PutMapping("/{userId}/{friendId}/{type}")
    public void updateFansAndFollow(@PathVariable String userId, @PathVariable String friendId, @PathVariable int type) {
        userService.updateFansAndFollow(userId, friendId, type);
    }

    // oauth2注解
    @GetMapping("/hi")
    public String hi(@RequestParam("name") String name) {
        return "A系统：hi," + name;
    }

    @GetMapping("/oauth/principal")
    public Principal info(Principal principal) {
        return principal;
    }

    @GetMapping("/me")
    public Authentication me(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/home")
    public Result home() {
        return new Result(ResultEnum.SUCCESS);
    }

    @GetMapping("/error")
    public Result error(){
        log.info("验证失败，调用接口");
        return new Result(UserResultEnum.ERROR);
    }

}
