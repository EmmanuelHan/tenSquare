package com.tensquare.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.common.entity.Result;
import com.tensquare.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 代码生成器
 * service 接口
 *
 * @Author HanLei
 * @Date 2020-03-17
 */
public interface IUserService extends IService<User>, UserDetailsService {



    /**
     * 分页查询
     *
     * @param user
     * @param page
     * @param limit
     * @return
     */
    Result findByParam(User user, Integer page, Integer limit);

    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    Result addUser(User user);

    /**
     * 获取用户列表
     *
     * @return
     */
    Result getUserList();

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    Result userLogin(User user);

    /**
     * 注册用户
     *
     * @param user
     * @param code
     * @return
     */
    Result registerUser(User user, String code);

    /**
     * 根据用户查询
     *
     * @param userId
     * @return
     */
    Result getUserById(String userId);

    /**
     * 修改用户
     *
     * @param user
     * @param userId
     * @return
     */
    Result editUser(User user, String userId);

    /**
     * 根据用户ID删除
     *
     * @param userId
     * @return
     */
    Result deleteUser(String userId);

    /**
     * 修改当前登陆用户信息
     *
     * @param user
     * @return
     */
    Result editLoginUserInfo(User user);

    /**
     * 用户分页
     *
     * @param user
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result getUserListWithPage(User user, Integer pageNo, Integer pageSize);

    /**
     * 发送手机验证码
     *
     * @param mobile
     * @return
     */
    Result sendSms(String mobile);

    /**
     * 关注某用户
     *
     * @param userId
     * @return
     */
    Result followUser(String userId);

    /**
     * 删除某用户关注
     *
     * @param userId
     * @return
     */
    Result deleteFollowUser(String userId);

    /**
     * 查询我的粉丝
     *
     * @return
     */
    Result getUserFans();

    /**
     * 查询我的关注
     *
     * @return
     */
    Result getUserFollow();


    /**
     * 查询我的关注ID列表
     *
     * @return
     */
    Result getuserFollowIdList();

    /**
     * 根据电话查询用户
     *
     * @param mobile
     * @return
     */
//    User getUserByMobile(String mobile);

    /**
     * 修改用户的粉丝数和关注数
     * @param userId
     * @param friendId
     * @param type
     */
    void updateFansAndFollow(String userId, String friendId, int type);
}
