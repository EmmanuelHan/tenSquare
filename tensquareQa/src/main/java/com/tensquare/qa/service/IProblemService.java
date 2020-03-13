package com.tensquare.qa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.qa.entity.Problem;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IProblemService extends IService<Problem> {

   /**
    * 分页查询
    * @param problem
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Problem problem, Integer page, Integer limit);
}
