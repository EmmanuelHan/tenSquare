package com.tensquare.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.user.entity.User;
import com.tensquare.user.mapper.UserMapper;
import com.tensquare.user.security.MyUserDetails;
import com.tensquare.user.service.IUserService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import util.StringUtil;
import util.Type;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date   2020-03-17
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder encoder;

    /**
     * 判断是否已存在该手机号
     * @param mobile
     * @return true-存在 false-不存在
     */
    private boolean isExist(String mobile){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        wrapper.eq("state", Type.STATE_NORMAL);
        int count = this.count(wrapper);
        return count > 0;
    }

    /**
     * 根据电话查询用户
     * @param mobile
     * @return
     */
    @Override
    public User getUserByMobile(String mobile) throws UsernameNotFoundException{
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
//        wrapper.eq("state", Type.STATE_NORMAL);
        return getOne(wrapper);
    }

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
        Page userPage = new Page(page, limit);
        //查询构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (user.getId() != null && !"".equals(user.getId())) {
            wrapper.eq("id", user.getId());
        }
        if (user.getMobile() != null && !"".equals(user.getMobile())) {
            wrapper.eq("mobile", user.getMobile());
        }
        if (user.getPassword() != null && !"".equals(user.getPassword())) {
            wrapper.eq("password", user.getPassword());
        }
        if (user.getNickName() != null && !"".equals(user.getNickName())) {
            wrapper.eq("nick_name", user.getNickName());
        }
        if (user.getSex() != null && !"".equals(user.getSex())) {
            wrapper.eq("sex", user.getSex());
        }
        if (user.getBirthday() != null && !"".equals(user.getBirthday())) {
            wrapper.eq("birthday", user.getBirthday());
        }
        if (user.getAvatar() != null && !"".equals(user.getAvatar())) {
            wrapper.eq("avatar", user.getAvatar());
        }
        if (user.getEmail() != null && !"".equals(user.getEmail())) {
            wrapper.eq("email", user.getEmail());
        }
        if (user.getRegDate() != null && !"".equals(user.getRegDate())) {
            wrapper.eq("reg_date", user.getRegDate());
        }
        if (user.getUpdateDate() != null && !"".equals(user.getUpdateDate())) {
            wrapper.eq("update_date", user.getUpdateDate());
        }
        if (user.getLastDate() != null && !"".equals(user.getLastDate())) {
            wrapper.eq("last_date", user.getLastDate());
        }
        if (user.getOnline() != null && !"".equals(user.getOnline())) {
            wrapper.eq("online", user.getOnline());
        }
        if (user.getInterest() != null && !"".equals(user.getInterest())) {
            wrapper.eq("interest", user.getInterest());
        }
        if (user.getPersonality() != null && !"".equals(user.getPersonality())) {
            wrapper.eq("personality", user.getPersonality());
        }
        if (user.getFansCount() != null && !"".equals(user.getFansCount())) {
            wrapper.eq("fans_count", user.getFansCount());
        }
        if (user.getFollowCount() != null && !"".equals(user.getFollowCount())) {
            wrapper.eq("follow_count", user.getFollowCount());
        }
        IPage<User> userIPage = userMapper.selectPage(userPage, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", userPage.getTotal());
        data.put("pageNo", userPage.getCurrent());
        data.put("list", userIPage.getRecords());
        return new Result(data);
    }


    /**
     * 增加用户
     * @param user
     * @return
     */
    @Override
    public Result addUser(User user){
        if(isExist(user.getMobile())){
            return new Result(ResultEnum.USER_SAME_NAME);
        }
        if(!ObjectUtils.isEmpty(user.getSex())){
            user.setSex(Type.SEX_NONE);
        }
        user.setRegDate(new Date());
        user.setUpdateDate(new Date());
        user.setOnline(0l);
        user.setFansCount(0);
        user.setFollowCount(0);
        user.setPassword(encoder.encode(user.getPassword()));
        save(user);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 获取用户列表
     * @return
     */
    @Override
    public Result getUserList(){
        List<User> list = list();
        return new Result(list);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public Result userLogin(User user){

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 注册用户
     * @param user
     * @param code
     * @return
     */
    @Override
    public Result registerUser(User user, String code){

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据用户查询
     * @param userId
     * @return
     */
    @Override
    public Result getUserById(String userId){
        User user = getById(userId);
        return new Result(user);
    }

    /**
     * 修改用户
     * @param user
     * @param userId
     * @return
     */
    @Override
    public Result editUser(User user, String userId){
        if(isExist(user.getMobile())){
            return new Result(ResultEnum.USER_SAME_NAME);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUpdateDate(new Date());
        updateById(user);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据用户ID删除
     * @param userId
     * @return
     */
    @Override
    public Result deleteUser(String userId){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userId);
        User user = new User();
        user.setState(Type.STATE_INVALID);
        update(user,wrapper);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @Override
    public Result getLoginUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();
        return new Result(principal);
    }

    /**
     * 修改当前登陆用户信息
     * @param user
     * @return
     */
    @Override
    public Result editLoginUserInfo(User user){
        if(isExist(user.getMobile())){
            return new Result(ResultEnum.USER_SAME_NAME);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUpdateDate(new Date());
        updateById(user);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 用户分页
     * @param user
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Result getUserListWithPage(User user, Integer pageNo, Integer pageSize){

        if(ObjectUtils.isEmpty(pageNo)){
            pageNo = StringUtil.START_PAGE;
        }
        if(ObjectUtils.isEmpty(pageSize)){
            pageSize = StringUtil.PAGE_SIZE;
        }

        //开启分页
        Page userPage = new Page(pageNo, pageSize);
        //查询构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (!ObjectUtils.isEmpty(user.getMobile())) {
            wrapper.eq("mobile", user.getMobile());
        }
        if (!ObjectUtils.isEmpty(user.getNickName())) {
            wrapper.like("nick_name", user.getNickName());
        }
        if (user.getSex() != null && !"".equals(user.getSex())) {
            wrapper.eq("sex", user.getSex());
        }
        if (user.getBirthday() != null && !"".equals(user.getBirthday())) {
            wrapper.eq("birthday", user.getBirthday());
        }
        if (user.getEmail() != null && !"".equals(user.getEmail())) {
            wrapper.like("email", user.getEmail());
        }
        if (user.getOnline() != null && !"".equals(user.getOnline())) {
            wrapper.eq("online", user.getOnline());
        }
        if (user.getInterest() != null && !"".equals(user.getInterest())) {
            wrapper.like("interest", user.getInterest());
        }
        IPage<User> userIPage = userMapper.selectPage(userPage, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", pageSize);
        data.put("total", userPage.getTotal());
        data.put("pageNo", userPage.getCurrent());
        data.put("list", userIPage.getRecords());
        return new Result(data);
    }

    /**
     * 发送手机验证码
     * @param mobile
     * @return
     */
    @Override
    public Result sendSms(String mobile){

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 关注某用户
     * @param userId
     * @return
     */
    @Override
    public Result followUser(String userId){

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 删除某用户关注
     * @param userId
     * @return
     */
    @Override
    public Result deleteFollowUser(String userId){

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 查询我的粉丝
     * @return
     */
    @Override
    public Result getUserFans(){

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 查询我的关注
     * @return
     */
    @Override
    public Result getUserFollow(){

        return new Result(ResultEnum.SUCCESS);
    }


    /**
     * 查询我的关注ID列表
     * @return
     */
    @Override
    public Result getuserFollowIdList(){

        return new Result(ResultEnum.SUCCESS);
    }

}
