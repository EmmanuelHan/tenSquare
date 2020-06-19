package com.tensquare.user.service.impl;

import com.tensquare.user.entity.NotFriend;
import com.tensquare.user.mapper.NotFriendMapper;
import com.tensquare.user.service.INotFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @Date   2020-06-19
 */
@Slf4j
@Service
public class NotFriendServiceImpl extends ServiceImpl<NotFriendMapper, NotFriend> implements INotFriendService {

    @Resource
    private NotFriendMapper notFriendMapper;

    @Override
    public List<NotFriend> list() {

       return notFriendMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param notFriend  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(NotFriend notFriend,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page notFriendPage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(notFriend.getUserId()!=null && !"".equals(notFriend.getUserId())){
            ((QueryWrapper) wrapper).eq("user_id",notFriend.getUserId());
        }
        if(notFriend.getFriendId()!=null && !"".equals(notFriend.getFriendId())){
            ((QueryWrapper) wrapper).eq("friend_id",notFriend.getFriendId());
        }
        IPage<NotFriend> notFriendIPage = notFriendMapper.selectPage(notFriendPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", notFriendPage.getTotal());
        data.put("pageNo", notFriendPage.getCurrent());
        data.put("list", notFriendIPage.getRecords());
        return new Result(data);
    }

}
