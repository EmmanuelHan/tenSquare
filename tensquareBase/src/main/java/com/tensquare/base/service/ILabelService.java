package com.tensquare.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.base.entity.Label;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface ILabelService extends IService<Label> {

   /**
    * 分页查询
    * @param label
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Label label, Integer page, Integer limit);
}
