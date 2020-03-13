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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import util.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date   2020-03-12
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> list() {

       return userMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param user  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(User user,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page userPage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(user.getId()!=null && !"".equals(user.getId())){
            ((QueryWrapper) wrapper).eq("id",user.getId());
        }
        if(user.getMobile()!=null && !"".equals(user.getMobile())){
            ((QueryWrapper) wrapper).eq("mobile",user.getMobile());
        }
        if(user.getPassword()!=null && !"".equals(user.getPassword())){
            ((QueryWrapper) wrapper).eq("password",user.getPassword());
        }
        if(user.getNickName()!=null && !"".equals(user.getNickName())){
            ((QueryWrapper) wrapper).eq("nick_name",user.getNickName());
        }
        if(user.getSex()!=null && !"".equals(user.getSex())){
            ((QueryWrapper) wrapper).eq("sex",user.getSex());
        }
        if(user.getBirthday()!=null && !"".equals(user.getBirthday())){
            ((QueryWrapper) wrapper).eq("birthday",user.getBirthday());
        }
        if(user.getAvatar()!=null && !"".equals(user.getAvatar())){
            ((QueryWrapper) wrapper).eq("avatar",user.getAvatar());
        }
        if(user.getEmail()!=null && !"".equals(user.getEmail())){
            ((QueryWrapper) wrapper).eq("email",user.getEmail());
        }
        if(user.getRegDate()!=null && !"".equals(user.getRegDate())){
            ((QueryWrapper) wrapper).eq("reg_date",user.getRegDate());
        }
        if(user.getUpdateDate()!=null && !"".equals(user.getUpdateDate())){
            ((QueryWrapper) wrapper).eq("update_date",user.getUpdateDate());
        }
        if(user.getLastDate()!=null && !"".equals(user.getLastDate())){
            ((QueryWrapper) wrapper).eq("last_date",user.getLastDate());
        }
        if(user.getOnline()!=null && !"".equals(user.getOnline())){
            ((QueryWrapper) wrapper).eq("online",user.getOnline());
        }
        if(user.getInterest()!=null && !"".equals(user.getInterest())){
            ((QueryWrapper) wrapper).eq("interest",user.getInterest());
        }
        if(user.getPersonality()!=null && !"".equals(user.getPersonality())){
            ((QueryWrapper) wrapper).eq("personality",user.getPersonality());
        }
        if(user.getFansCount()!=null && !"".equals(user.getFansCount())){
            ((QueryWrapper) wrapper).eq("fans_count",user.getFansCount());
        }
        if(user.getFollowCount()!=null && !"".equals(user.getFollowCount())){
            ((QueryWrapper) wrapper).eq("follow_count",user.getFollowCount());
        }
        IPage<User> userIPage = userMapper.selectPage(userPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", userPage.getTotal());
        data.put("pageNo", userPage.getCurrent());
        data.put("list", userIPage.getRecords());
        return new Result(data);
    }

}
