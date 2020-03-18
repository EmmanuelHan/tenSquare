package com.tensquare.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.user.entity.User;
import com.tensquare.user.mapper.UserMapper;
import com.tensquare.user.service.IUserService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
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


    /**
     * 根据电话查询用户
     * @param mobile
     * @return
     */
    public User getUserByMobile(String mobile){
        Wrapper wrapper = new QueryWrapper();
        ((QueryWrapper) wrapper).eq("mobile", mobile);
        ((QueryWrapper) wrapper).eq("state", Type.STATE_NORMAL);
        User one = getOne(wrapper);
        return one;
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
        Wrapper wrapper = new QueryWrapper();

        if (user.getId() != null && !"".equals(user.getId())) {
            ((QueryWrapper) wrapper).eq("id", user.getId());
        }
        if (user.getMobile() != null && !"".equals(user.getMobile())) {
            ((QueryWrapper) wrapper).eq("mobile", user.getMobile());
        }
        if (user.getPassword() != null && !"".equals(user.getPassword())) {
            ((QueryWrapper) wrapper).eq("password", user.getPassword());
        }
        if (user.getNickName() != null && !"".equals(user.getNickName())) {
            ((QueryWrapper) wrapper).eq("nick_name", user.getNickName());
        }
        if (user.getSex() != null && !"".equals(user.getSex())) {
            ((QueryWrapper) wrapper).eq("sex", user.getSex());
        }
        if (user.getBirthday() != null && !"".equals(user.getBirthday())) {
            ((QueryWrapper) wrapper).eq("birthday", user.getBirthday());
        }
        if (user.getAvatar() != null && !"".equals(user.getAvatar())) {
            ((QueryWrapper) wrapper).eq("avatar", user.getAvatar());
        }
        if (user.getEmail() != null && !"".equals(user.getEmail())) {
            ((QueryWrapper) wrapper).eq("email", user.getEmail());
        }
        if (user.getRegDate() != null && !"".equals(user.getRegDate())) {
            ((QueryWrapper) wrapper).eq("reg_date", user.getRegDate());
        }
        if (user.getUpdateDate() != null && !"".equals(user.getUpdateDate())) {
            ((QueryWrapper) wrapper).eq("update_date", user.getUpdateDate());
        }
        if (user.getLastDate() != null && !"".equals(user.getLastDate())) {
            ((QueryWrapper) wrapper).eq("last_date", user.getLastDate());
        }
        if (user.getOnline() != null && !"".equals(user.getOnline())) {
            ((QueryWrapper) wrapper).eq("online", user.getOnline());
        }
        if (user.getInterest() != null && !"".equals(user.getInterest())) {
            ((QueryWrapper) wrapper).eq("interest", user.getInterest());
        }
        if (user.getPersonality() != null && !"".equals(user.getPersonality())) {
            ((QueryWrapper) wrapper).eq("personality", user.getPersonality());
        }
        if (user.getFansCount() != null && !"".equals(user.getFansCount())) {
            ((QueryWrapper) wrapper).eq("fans_count", user.getFansCount());
        }
        if (user.getFollowCount() != null && !"".equals(user.getFollowCount())) {
            ((QueryWrapper) wrapper).eq("follow_count", user.getFollowCount());
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
        if(!ObjectUtils.isEmpty(user.getSex())){
            user.setSex(Type.SEX_NONE);
        }
        user.setRegDate(new Date());
        user.setUpdateDate(new Date());
        user.setOnline(0l);
        user.setFansCount(0);
        user.setFollowCount(0);
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
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getMobile(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            log.info("用户名或密码错误", e);
            return new Result(ResultEnum.LOGIN_ERROR);
        } catch (AuthorizationException e) {
            log.info("没有权限访问",e);
            return new Result(ResultEnum.NO_ACCESS);
        }

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

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 修改用户
     * @param user
     * @param userId
     * @return
     */
    @Override
    public Result editUser(User user, String userId){

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据用户ID删除
     * @param userId
     * @return
     */
    @Override
    public Result deleteUser(String userId){

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @Override
    public Result getLoginUserInfo(){

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 修改当前登陆用户信息
     * @param user
     * @return
     */
    @Override
    public Result editLoginUserInfo(User user){

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

        return new Result(ResultEnum.SUCCESS);
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
