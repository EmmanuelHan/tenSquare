package com.tensquare.friend.service;

import com.tensquare.friend.entity.NotFriend;
import com.baomidou.mybatisplus.extension.service.IService;
import entity.Result;

 /**
 *  代码生成器
 *  service 接口
 * @Author HanLei
 * @Date   2020-06-19
 */
public interface INotFriendService extends IService<NotFriend> {

    /**
     * 分页查询
     * @param notFriend
     * @param page
     * @param limit
     * @return
     */
    Result findByParam(NotFriend notFriend, Integer page, Integer limit);

}
