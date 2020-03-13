package com.tensquare.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.article.entity.Column;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IColumnService extends IService<Column> {

   /**
    * 分页查询
    * @param column
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Column column, Integer page, Integer limit);
}
