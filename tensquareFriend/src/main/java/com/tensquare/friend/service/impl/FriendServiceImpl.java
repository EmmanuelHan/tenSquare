package com.tensquare.friend.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tensquare.friend.entity.Friend;
import com.tensquare.friend.entity.NotFriend;
import com.tensquare.friend.feign.UserFeign;
import com.tensquare.friend.mapper.FriendMapper;
import com.tensquare.friend.service.IFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.friend.service.INotFriendService;
import entity.Result;
import entity.ResultEnum;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {

    @Resource
    private FriendMapper friendMapper;

    @Resource
    private UserFeign userFeign;

    @Resource
    private INotFriendService notFriendService;

    @Override
    public List<Friend> list() {

        return friendMapper.selectList(null);
    }

    /**
     * 分页查询
     *
     * @param friend page  limit
     * @return jsonResponse
     */
    @Override
    public Result findByParam(Friend friend, Integer page, Integer limit) {

        if (page == null) {
            page = StringUtil.START_PAGE;
        }
        if (limit == null) {
            limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page friendPage = new Page(page, limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if (friend.getUserId() != null && !"".equals(friend.getUserId())) {
            ((QueryWrapper) wrapper).eq("user_id", friend.getUserId());
        }
        if (friend.getFriendId() != null && !"".equals(friend.getFriendId())) {
            ((QueryWrapper) wrapper).eq("friend_id", friend.getFriendId());
        }
        if (friend.getLike() != null && !"".equals(friend.getLike())) {
            ((QueryWrapper) wrapper).eq("like", friend.getLike());
        }
        IPage<Friend> friendIPage = friendMapper.selectPage(friendPage, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", friendPage.getTotal());
        data.put("pageNo", friendPage.getCurrent());
        data.put("list", friendIPage.getRecords());
        return new Result(data);
    }

    /**
     * 根据用户ID和被关注用户ID查询记录个数
     *
     * @param userId
     * @param friendId
     * @return
     */
    @Override
    public int countByUserIdAndFriendId(String userId, String friendId) {
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("friendId", friendId);
        return count(wrapper);
    }

    @Override
    public void updateLikeEachOther(String userId, String friendId, String like) {
        UpdateWrapper<Friend> wrapper = new UpdateWrapper<>();
        wrapper.set("liek", like);
        wrapper.eq("userId", userId);
        wrapper.eq("friendId", friendId);
        update(wrapper);
    }

    /**
     * 添加用户关系
     * @param friendId
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public Result addRelationship(String friendId, String type) throws Exception{
//        Result loginUserInfo = userFeign.getLoginUserInfo();
//        if(ResultEnum.SUCCESS.getCode() != loginUserInfo.getCode()){
//            return loginUserInfo;
//        }
//        User user = JSON.parseObject(loginUserInfo.getData().toString(), User.class);
        User user = new User();
        user.setId("361837103241236480");
        //如果是喜欢
        if ("1".equals(type)) {
            return addFriends(user.getId(), friendId);
        } else if("2".equals(type)){
            //不喜欢
            return addNotFriend(user.getId(), friendId);
        } else {
            return new Result(ResultEnum.PARAM);
        }
    }

    /**
     * 添加好友关系
     * @param userId
     * @param friendId
     * @return
     */
    private Result addFriends(String userId, String friendId){
        //判断如果用户已经添加了这个好友，则不进行任何操作,返回0
        if (countByUserIdAndFriendId(userId, friendId) > 0) {
            return new Result(ResultEnum.FRIEND_SAVE_FRIEND);
        }
        //向喜欢表中添加记录
        Friend friend = new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friend.setLike("0");//单向喜欢
        save(friend);
        //判断对方是否喜欢你，如果喜欢，将like设置为1
        if (countByUserIdAndFriendId(friendId, userId) > 0) {
            updateLikeEachOther(userId, friendId, "1");
            updateLikeEachOther(friendId, userId, "1");
        }
        //更新用户粉丝数和关注数
        userFeign.updateFansAndFollow(userId, friendId, 1);

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 把用户列为非好友
     * @param userId
     * @param friendId
     * @return
     */
    private Result addNotFriend(String userId, String friendId){
        QueryWrapper<NotFriend> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("friendId", friendId);
        int count = notFriendService.count(wrapper);
        if(count > 0){
            return new Result(ResultEnum.FRIEND_SAVE_NOT_FRIEND);
        }
        NotFriend notFriend = new NotFriend();
        notFriend.setUserId(userId);
        notFriend.setFriendId(friendId);
        notFriendService.save(notFriend);
        return new Result(ResultEnum.SUCCESS);
    }


    /**
     * 删除好友
     * @param friendId
     * @return
     */
    @Override
    public Result deleteFriend(String friendId) throws Exception{
        //        Result loginUserInfo = userFeign.getLoginUserInfo();
//        if(ResultEnum.SUCCESS.getCode() != loginUserInfo.getCode()){
//            return loginUserInfo;
//        }
//        User user = JSON.parseObject(loginUserInfo.getData().toString(), User.class);
        User user = new User();
        user.setId("361837103241236480");

        //删除好友表中的数据
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", user.getId());
        wrapper.eq("friendId", friendId);
        remove(wrapper);

        //更新好友表中对方和自己的关系为单向喜欢
        updateLikeEachOther(friendId,user.getId(), "0");

        //更新用户粉丝数和对方关注数
        userFeign.updateFansAndFollow(user.getId(), friendId, -1);

        //添加非好友关系
        NotFriend notFriend = new NotFriend();
        notFriend.setUserId(user.getId());
        notFriend.setFriendId(friendId);
        notFriendService.save(notFriend);

        return new Result(ResultEnum.SUCCESS);
    }


}
