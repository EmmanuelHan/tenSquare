package com.tensquare.qa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.qa.entity.Pl;
import com.tensquare.common.entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IPlService extends IService<Pl> {

   /**
    * 分页查询
    * @param pl
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Pl pl, Integer page, Integer limit);
}
