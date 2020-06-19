package com.tensquare.user.security;

import com.tensquare.user.entity.User;
import com.tensquare.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class MyCustomUserService implements UserDetailsService {


    @Autowired
    private IUserService userService;

    /**
     * @Description: 实现了UserDetailsService接口中的loadUserByUsername方法
     * 执行登录,构建Authentication对象必须的信息,
     * 如果用户不存在，则抛出UsernameNotFoundException异常
     * @Date: 2019/3/27-16:04
     * @Param: [s]
     * @return: org.springframework.security.core.userdetails.UserDetails
     **/
    @Override
    public MyUserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        /**
         * @Author: Galen
         * @Description: 查询数据库，获取登录的用户信息
         **/
        User user = userService.getUserByMobile(mobile);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("不存在该用户");
        } else {
            MyUserDetails myUserDetails = new MyUserDetails();
            myUserDetails.setUsername(user.getMobile());
            myUserDetails.setPassword(user.getPassword());
            myUserDetails.setState(user.getState());
            myUserDetails.setUpdateDate(user.getUpdateDate());
            myUserDetails.setId(user.getId());
            return myUserDetails;
        }
    }
}
