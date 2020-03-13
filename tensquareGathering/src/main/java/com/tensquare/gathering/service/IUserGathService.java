package com.tensquare.gathering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.gathering.entity.UserGath;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IUserGathService extends IService<UserGath> {

   /**
    * 分页查询
    * @param userGath
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(UserGath userGath, Integer page, Integer limit);
}
