package com.tensquare.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.user.entity.Follow;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-17
*/
public interface IFollowService extends IService<Follow> {

   /**
    * 分页查询
    * @param follow
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Follow follow, Integer page, Integer limit);
}
