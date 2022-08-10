package com.tensquare.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.auth.entity.Role;
import com.tensquare.common.entity.Result;

/**
 *  代码生成器
 *  service 接口
 * @Author HanLei
 * @Date   2020-08-19
 */
public interface IRoleService extends IService<Role> {

    /**
     * 分页查询
     * @param role
     * @param page
     * @param limit
     * @return
     */
    Result findByParam(Role role, Integer page, Integer limit);

}
