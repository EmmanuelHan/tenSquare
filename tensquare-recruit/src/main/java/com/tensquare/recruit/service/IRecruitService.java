package com.tensquare.recruit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.recruit.entity.Recruit;
import com.tensquare.common.entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IRecruitService extends IService<Recruit> {

   /**
    * 分页查询
    * @param recruit
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Recruit recruit, Integer page, Integer limit);
}
