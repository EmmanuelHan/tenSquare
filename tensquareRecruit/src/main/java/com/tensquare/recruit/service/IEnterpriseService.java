package com.tensquare.recruit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.recruit.entity.Enterprise;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IEnterpriseService extends IService<Enterprise> {

   /**
    * 分页查询
    * @param enterprise
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Enterprise enterprise, Integer page, Integer limit);
}
