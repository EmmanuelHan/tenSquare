package com.tensquare.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.user.entity.UserRoleRel;
import com.tensquare.user.mapper.UserRoleRelMapper;
import com.tensquare.user.service.IUserRoleRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author HanLei
 * @Date 2020-08-19
 */
@Slf4j
@Service
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel> implements IUserRoleRelService {

    @Resource
    private UserRoleRelMapper userRoleRelMapper;

    @Override
    public List<UserRoleRel> list() {

        return userRoleRelMapper.selectList(null);
    }

}
