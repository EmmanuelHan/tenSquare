package com.tensquare.user.controller;

import com.tensquare.user.entity.Admin;
import com.tensquare.user.service.IAdminService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author HanLei
 * @Date 2020-03-17
 */
@Slf4j
@Controller
public class AdminController {

    @Resource
    private IAdminService adminService;


    /**
     * 增加管理员
     */
    @ResponseBody
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public Result addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    /**
     * 管理员全部列表
     */
    @ResponseBody
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public Result getAdminList() {
        return adminService.getAdminList();
    }

    /**
     * 根据ID查询
     */
    @ResponseBody
    @RequestMapping(value = "/admin/{adminId}", method = RequestMethod.GET)
    public Result getAdminById(@PathVariable String adminId) {
        return adminService.getAdminById(adminId);
    }

    /**
     * 修改管理员
     */
    @ResponseBody
    @RequestMapping(value = "/admin/{adminId}", method = RequestMethod.PUT)
    public Result editAdmin(@PathVariable String adminId,@RequestBody Admin admin) {
        return adminService.editAdmin(adminId,admin);
    }

    /**
     * 根据ID删除
     */
    @ResponseBody
    @RequestMapping(value = "/admin/{adminId}", method = RequestMethod.DELETE)
    public Result deleteAdminById(@PathVariable String adminId) {
        return adminService.deleteAdminById(adminId);
    }

    /**
     * 管理员分页
     */
    @ResponseBody
    @RequestMapping(value = "/admin/search/{pageNo}/{pageSize}", method = RequestMethod.POST)
    public Result getAdminListByParamWithPage(@PathVariable Integer pageNo,@PathVariable Integer pageSize,@RequestBody Admin admin) {
        return adminService.getAdminListByParamWithPage(pageNo,pageSize,admin);
    }


    /**
     * @ResponseBody
     */
    @ResponseBody
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public Result adminLogin(@RequestBody Admin admin) {
        return adminService.adminLogin(admin);
    }



}
