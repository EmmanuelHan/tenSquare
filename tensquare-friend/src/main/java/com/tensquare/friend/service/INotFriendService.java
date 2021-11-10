package com.tensquare.friend.service;

import com.tensquare.friend.entity.NotFriend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.common.entity.Result;

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
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result findByParam(NotFriend notFriend, Integer pageNo, Integer pageSize);

}
