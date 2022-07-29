package com.tensquare.user.controller;

import com.tensquare.user.entity.Admin;
import com.tensquare.user.service.IAdminService;
import com.tensquare.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author HanLei
 * @Date 2020-03-17
 */
@Slf4j
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private IAdminService adminService;


    /**
     * 增加管理员
     */
    @PostMapping()
    public Result addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    /**
     * 管理员全部列表
     */
    @GetMapping()
    public Result getAdminList() {
        return adminService.getAdminList();
    }

    /**
     * 根据ID查询
     */
    @GetMapping(value = "/{adminId}")
    public Result getAdminById(@PathVariable String adminId) {
        return adminService.getAdminById(adminId);
    }

    /**
     * 修改管理员
     */
    @PutMapping(value = "/{adminId}")
    public Result editAdmin(@PathVariable String adminId,@RequestBody Admin admin) {
        return adminService.editAdmin(adminId,admin);
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping(value = "/{adminId}")
    public Result deleteAdminById(@PathVariable String adminId) {
        return adminService.deleteAdminById(adminId);
    }

    /**
     * 管理员分页
     */
    @PostMapping(value = "/search/{pageNo}/{pageSize}")
    public Result getAdminListByParamWithPage(@PathVariable Integer pageNo,@PathVariable Integer pageSize,@RequestBody Admin admin) {
        return adminService.getAdminListByParamWithPage(pageNo,pageSize,admin);
    }

    /**
     * 管理员登录
     */
    @PostMapping(value = "/login")
    public Result adminLogin(@RequestBody Admin admin) {
        return adminService.adminLogin(admin);
    }



}
