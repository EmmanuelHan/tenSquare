package com.tensquare.user.controller;

import com.tensquare.user.entity.User;
import com.tensquare.user.service.IUserService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import util.StringUtil;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @Author HanLei
 * @Date 2020-03-17
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 增加用户
     */
    @PostMapping()
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * 用户全部列表
     */
    @GetMapping()
    public Result getUserList() {
        return userService.getUserList();
    }

//    /**
//     * 登陆
//     */
//    @PostMapping("/login")
//    public Result userLogin(@RequestBody User user) {
//        return userService.userLogin(user);
//    }

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
    @PutMapping("/saveinfo")
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

    /**
     * @RequiresUser:subject.isRemembered()结果为true,subject.isAuthenticated()
     * @RequiresAuthentication:同于方法subject.isAuthenticated() 结果为true时
     * @RequiresGuest:与@RequiresUser完全相反。
     * @RequiresRoles("xx");有xx角色才可以访问方法
     * @RequiresPermissions({"file:read", "write:aFile.txt"} ):同时含有file:read和write:aFile.txt的权限才能执行方法
     */
    @GetMapping("/hi")
    public String hi(@RequestParam("name") String name) {
        return "A系统：hi," + name;
    }

    /**
     * @功能描述: 获取用户认证信息（已登录用户）
     * @技术交流： 961179337(QQ群)
     * @编写作者： lixx2048@163.com
     * @联系方式： 941415509(QQ)
     * @开发日期： 2020年7月31日
     */
    @GetMapping("/oauth/principal")
    public Principal info(Principal principal) {
        return principal;
    }

//    @GetMapping("/me")
//    public Authentication me(Authentication authentication) {
//        return authentication;
//    }

    @GetMapping("/home")
    public Result home() {


        return new Result(ResultEnum.SUCCESS);
    }

//    @GetMapping("/error")
//    public Result error(){
//        log.info("验证失败，调用接口");
//        return new Result(ResultEnum.ERROR);
//    }

}
