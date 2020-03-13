package com.tensquare.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.user.entity.User;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IUserService extends IService<User> {

   /**
    * 分页查询
    * @param user
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(User user, Integer page, Integer limit);
}
