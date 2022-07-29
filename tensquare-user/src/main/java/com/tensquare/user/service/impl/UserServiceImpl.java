package com.tensquare.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.common.entity.Result;
import com.tensquare.common.system.Constants;
import com.tensquare.common.util.JwtUtil;
import com.tensquare.common.util.StringUtil;
import com.tensquare.common.util.Type;
import com.tensquare.user.entity.Role;
import com.tensquare.user.entity.User;
import com.tensquare.user.entity.UserResultEnum;
import com.tensquare.user.mapper.RoleMapper;
import com.tensquare.user.mapper.UserMapper;
import com.tensquare.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date 2020-03-17
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", username);
        wrapper.eq("state", "1");
        User user = getOne(wrapper);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("找不到该用户[" + username + "]");
        }
        // 设置用户权限
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("管理员");
        role.setPermission("ROLE_ADMIN");
        roles.add(role);


//        List<Role> roles = roleMapper.selectPermissionByUserId(user.getId());
        user.setAuthorities(roles);

//        // 标识位设置
//        boolean enabled = true;            // 可用性 :true:可用 false:不可用
//        boolean accountNonExpired = true;    // 过期性 :true:没过期 false:过期
//        boolean credentialsNonExpired = true;                                // 有效性 :true:凭证有效 false:凭证无效
//        boolean accountNonLocked = true;    // 锁定性 :true:未锁定 false:已锁定
//
//        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);

        return user;
    }

    /**
     * 判断是否已存在该手机号
     *
     * @param mobile
     * @return true-存在 false-不存在
     */
    private boolean isExist(String mobile) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        wrapper.eq("state", Type.STATE_NORMAL);
        long count = this.count(wrapper);
        return count > 0;
    }

    /**
     * 根据电话查询用户
     *
     * @param mobile
     * @return
     */
//    @Override
//    public User getUserByMobile(String mobile) throws UsernameNotFoundException {
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("mobile", mobile);
////        wrapper.eq("state", Type.STATE_NORMAL);
//        return getOne(wrapper);
//    }

    /**
     * 分页查询
     *
     * @param user page  limit
     * @return jsonResponse
     */
    @Override
    public Result findByParam(User user, Integer page, Integer limit) {

        if (page == null) {
            page = StringUtil.START_PAGE;
        }
        if (limit == null) {
            limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        IPage<User> userPage = new Page<>(page, limit);
        //查询构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(!ObjectUtils.isEmpty(user.getId()),"id", user.getId());
        wrapper.eq(!ObjectUtils.isEmpty(user.getMobile()),"mobile", user.getMobile());
        wrapper.eq(!ObjectUtils.isEmpty(user.getPassword()),"password", user.getPassword());
        wrapper.eq(!ObjectUtils.isEmpty(user.getNickName()),"nick_name", user.getNickName());
        wrapper.eq(!ObjectUtils.isEmpty(user.getSex()),"sex", user.getSex());
        wrapper.eq(!ObjectUtils.isEmpty(user.getBirthday()),"birthday", user.getBirthday());
        wrapper.eq(!ObjectUtils.isEmpty(user.getAvatar()),"avatar", user.getAvatar());
        wrapper.eq(!ObjectUtils.isEmpty(user.getEmail()),"email", user.getEmail());
        wrapper.eq(!ObjectUtils.isEmpty(user.getRegDate()),"reg_date", user.getRegDate());
        wrapper.eq(!ObjectUtils.isEmpty(user.getUpdateDate()),"update_date", user.getUpdateDate());
        wrapper.eq(!ObjectUtils.isEmpty(user.getLastDate()),"last_date", user.getLastDate());
        wrapper.eq(!ObjectUtils.isEmpty(user.getOnline()),"online", user.getOnline());
        wrapper.eq(!ObjectUtils.isEmpty(user.getInterest()),"interest", user.getInterest());
        wrapper.eq(!ObjectUtils.isEmpty(user.getPersonality()),"personality", user.getPersonality());
        wrapper.eq(!ObjectUtils.isEmpty(user.getFansCount()),"fans_count", user.getFansCount());
        wrapper.eq(!ObjectUtils.isEmpty(user.getFollowCount()),"follow_count", user.getFollowCount());
        IPage<User> userIPage = page(userPage, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", userPage.getTotal());
        data.put("pageNo", userPage.getCurrent());
        data.put("list", userIPage.getRecords());
        return new Result(data);
    }


    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    @Override
    public Result addUser(User user) {
        if (isExist(user.getMobile())) {
            return new Result(UserResultEnum.USER_SAME_NAME);
        }
        if (!ObjectUtils.isEmpty(user.getSex())) {
            user.setSex(Type.SEX_NONE);
        }
        user.setRegDate(new Date());
        user.setUpdateDate(new Date());
        user.setOnline(0L);
        user.setFansCount(0);
        user.setFollowCount(0);
        user.setPassword(encoder.encode(user.getPassword()));
        save(user);
        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @Override
    public Result getUserList() {
        List<User> list = list();
        return new Result(list);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public Result userLogin(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", user.getMobile());
        wrapper.eq("state", Type.STATE_NORMAL);
        User one = getOne(wrapper);
        if (!ObjectUtils.isEmpty(one) && encoder.matches(user.getPassword(), one.getPassword())) {
            String token = jwtUtil.createJwt(user.getId(), user.getMobile(), Constants.ROLE_USER);
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put(Constants.NAME_ROLE, Constants.ROLE_USER);
            return new Result(data);
        }
        return new Result(UserResultEnum.ACCESS_WRONG);
    }

    /**
     * 注册用户
     *
     * @param user
     * @param code
     * @return
     */
    @Override
    public Result registerUser(User user, String code) {

        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 根据用户查询
     *
     * @param userId
     * @return
     */
    @Override
    public Result getUserById(String userId) {
        User user = getById(userId);
        return new Result(user);
    }

    /**
     * 修改用户
     *
     * @param user
     * @param userId
     * @return
     */
    @Override
    public Result editUser(User user, String userId) {
        if (isExist(user.getMobile())) {
            return new Result(UserResultEnum.USER_SAME_NAME);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUpdateDate(new Date());
        updateById(user);
        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 根据用户ID删除
     *
     * @param userId
     * @return
     */
    @Override
    public Result deleteUser(String userId) {
        String role = (String) request.getAttribute(Constants.NAME_ROLE);
        if (ObjectUtils.isEmpty(role) && !role.equals(Constants.ROLE_ADMIN)) {
            return new Result(UserResultEnum.NO_ACCESS);
        }
        removeById(userId);
        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 修改当前登陆用户信息
     *
     * @param user
     * @return
     */
    @Override
    public Result editLoginUserInfo(User user) {
        if (isExist(user.getMobile())) {
            return new Result(UserResultEnum.USER_SAME_NAME);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUpdateDate(new Date());
        updateById(user);
        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 用户分页
     *
     * @param user
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Result getUserListWithPage(User user, Integer pageNo, Integer pageSize) {
        //开启分页
        IPage<User> userPage = new Page<>(pageNo, pageSize);
        //查询构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(!ObjectUtils.isEmpty(user.getMobile()), "mobile", user.getMobile());
        wrapper.like(!ObjectUtils.isEmpty(user.getNickName()), "nick_name", user.getNickName());
        wrapper.eq(!ObjectUtils.isEmpty(user.getSex()), "sex", user.getSex());
        wrapper.eq(!ObjectUtils.isEmpty(user.getBirthday()), "birthday", user.getBirthday());
        wrapper.like(!ObjectUtils.isEmpty(user.getEmail()), "email", user.getEmail());
        wrapper.eq(!ObjectUtils.isEmpty(user.getOnline()), "online", user.getOnline());
        wrapper.like(!ObjectUtils.isEmpty(user.getInterest()), "interest", user.getInterest());
        IPage<User> userIPage = page(userPage, wrapper);
        return new Result(userIPage);
    }

    /**
     * 发送手机验证码
     *
     * @param mobile
     * @return
     */
    @Override
    public Result sendSms(String mobile) {

        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 关注某用户
     *
     * @param userId
     * @return
     */
    @Override
    public Result followUser(String userId) {

        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 删除某用户关注
     *
     * @param userId
     * @return
     */
    @Override
    public Result deleteFollowUser(String userId) {

        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 查询我的粉丝
     *
     * @return
     */
    @Override
    public Result getUserFans() {

        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 查询我的关注
     *
     * @return
     */
    @Override
    public Result getUserFollow() {

        return new Result(UserResultEnum.SUCCESS);
    }


    /**
     * 查询我的关注ID列表
     *
     * @return
     */
    @Override
    public Result getuserFollowIdList() {

        return new Result(UserResultEnum.SUCCESS);
    }

    /**
     * 修改用户的粉丝数和关注数
     *
     * @param userId
     * @param friendId
     * @param type
     */
    @Override
    @Transactional
    public void updateFansAndFollow(String userId, String friendId, int type) {
        userMapper.updateFansCount(friendId, type);
        userMapper.updateFollowCount(userId, type);
    }
}
