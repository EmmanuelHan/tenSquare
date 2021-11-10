package com.tensquare.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.user.entity.Admin;
import com.tensquare.common.entity.Result;

/**
 * 代码生成器
 * service 接口
 *
 * @Author HanLei
 * @Date 2020-03-17
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 增加管理员
     *
     * @param admin
     * @return
     */
    Result addAdmin(Admin admin);

    /**
     * 管理员全部列表
     * @return
     */
    Result getAdminList();


    /**
     * 根据ID查询
     * @param adminId
     * @return
     */
    Result getAdminById(String adminId);

    /**
     * 编辑管理员
     * @param adminId
     * @param admin
     * @return
     */
    Result editAdmin(String adminId, Admin admin);

    /**
     * 根据ID删除
     * @param adminId
     * @return
     */
    Result deleteAdminById(String adminId);

    /**
     * 管理员分页
     * @param pageNo
     * @param pageSize
     * @param admin
     * @return
     */
    Result getAdminListByParamWithPage(Integer pageNo, Integer pageSize, Admin admin);

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    Result adminLogin(Admin admin);
}
