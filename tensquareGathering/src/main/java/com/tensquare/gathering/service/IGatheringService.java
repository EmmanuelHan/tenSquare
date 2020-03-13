package com.tensquare.gathering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.gathering.entity.Gathering;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IGatheringService extends IService<Gathering> {

   /**
    * 分页查询
    * @param gathering
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Gathering gathering, Integer page, Integer limit);
}
