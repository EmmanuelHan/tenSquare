package com.tensquare.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.user.entity.Admin;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IAdminService extends IService<Admin> {

   /**
    * 分页查询
    * @param admin
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Admin admin, Integer page, Integer limit);
}
