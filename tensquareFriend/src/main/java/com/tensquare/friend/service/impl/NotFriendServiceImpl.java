package com.tensquare.friend.service.impl;

import com.tensquare.friend.entity.NotFriend;
import com.tensquare.friend.mapper.NotFriendMapper;
import com.tensquare.friend.service.INotFriendService;
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
 * @Date 2020-06-19
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
     * @param notFriend page  limit
     * @param pageSize
     * @param pageNo
     * @return jsonResponse
     */
    @Override
    public Result findByParam(NotFriend notFriend, Integer pageSize, Integer pageNo) {

        if (pageNo == null) {
            pageNo = StringUtil.START_PAGE;
        }
        if (pageSize == null) {
            pageSize = StringUtil.PAGE_SIZE;
        }
        //开启分页
        IPage<NotFriend> notFriendPage = new Page<>(pageNo, pageSize);
        //查询构造器
        QueryWrapper<NotFriend> wrapper = new QueryWrapper<>();

        if (notFriend.getUserId() != null && !"".equals(notFriend.getUserId())) {
            wrapper.eq("user_id", notFriend.getUserId());
        }
        if (notFriend.getFriendId() != null && !"".equals(notFriend.getFriendId())) {
            wrapper.eq("friend_id", notFriend.getFriendId());
        }
        IPage<NotFriend> notFriendIPage = page(notFriendPage, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", pageSize);
        data.put("total", notFriendIPage.getTotal());
        data.put("pageNo", notFriendIPage.getCurrent());
        data.put("list", notFriendIPage.getRecords());
        return new Result(data);
    }


}
