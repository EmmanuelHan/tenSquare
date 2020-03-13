package com.tensquare.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.base.entity.City;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface ICityService extends IService<City> {

   /**
    * 分页查询
    * @param city
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(City city, Integer page, Integer limit);
}
