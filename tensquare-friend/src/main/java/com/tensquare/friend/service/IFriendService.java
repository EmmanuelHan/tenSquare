package com.tensquare.friend.service;

import com.tensquare.friend.entity.Friend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.common.entity.Result;

 /**
 *  代码生成器
 *  service 接口
 * @Author HanLei
 * @Date   2020-06-19
 */
public interface IFriendService extends IService<Friend> {

    /**
     * 分页查询
     * @param friend
     * @param page
     * @param limit
     * @return
     */
    Result findByParam(Friend friend, Integer page, Integer limit);

     /**
      * 根据用户ID和被关注用户ID查询记录个数
      * @param userId
      * @param friendId
      * @return
      */
     int countByUserIdAndFriendId(String userId,String friendId);

     void updateLikeEachOther(String userId,String friendId,String like);

     Result addRelationship(String userId,String friendId) throws Exception;

     /**
      * 删除好友
      * @param friendId
      * @return
      */
     Result deleteFriend(String friendId) throws Exception;
 }
