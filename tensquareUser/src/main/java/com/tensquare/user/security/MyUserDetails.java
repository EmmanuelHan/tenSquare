package com.tensquare.user.security;

import com.tensquare.user.entity.User;
import com.tensquare.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Slf4j
public class MyUserDetails implements UserDetails {

    @Resource
    private IUserService userService;

    private static final long serialVersionUID = 1L;

    //登录用户名
    private String username;
    //登录密码
    private String password;
    //账户状态
    private String state;
    //账户最后更新时间
    private Date updateDate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    private Collection<? extends GrantedAuthority> authorities;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 用户锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        log.info("用户锁定【{}】",!"2".equals(state));
        return !"2".equals(state);
//        return true;
    }

    /***
     * 密码过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, -7);
        Date time = instance.getTime();
        log.info("密码过期【{}】",updateDate.after(time));
        return updateDate.after(time);
//        return true;
    }


    /**
     * 用户禁用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        log.info("用户禁用【{}】",!"0".equals(state));
        return !"0".equals(state);
//        return true;
    }
}
