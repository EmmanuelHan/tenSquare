package com.tensquare.user.security;

import com.tensquare.user.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Slf4j
public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    //登录用户名
    private String username;
    //登录密码
    private String password;
    //账户状态
    private String state;
    //账户最后更新时间
    private Date updateDate;
    //编号
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    private Collection<Role> authorities;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<Role> getAuthorities() {
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

    /**
     * 用户过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return !"2".equals(state);
//        return true;
    }

    /**
     * 证书过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, -7);
        Date time = instance.getTime();
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
        return !"0".equals(state);
//        return true;
    }
}
