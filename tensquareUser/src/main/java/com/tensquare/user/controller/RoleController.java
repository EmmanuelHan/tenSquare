package com.tensquare.user.controller;

import com.tensquare.user.service.IRoleService;
import com.tensquare.user.entity.Role;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author HanLei
 * @Date   2020-08-19
 */
@Slf4j
@Controller
@RequestMapping("/user/role")
public class RoleController {

    @Resource
    private IRoleService  roleServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "role/role_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param role
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Role role){
        mv.setViewName("role/role_addOrUpdate");
        if(role != null){
            mv.addObject("obj",role);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param role
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Role role,Integer page , Integer limit){
        return roleServiceImpl.findByParam(role, page, limit);
    }

    /**
    * 新增or修改
    * @param role
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Role role){
        try {
            roleServiceImpl.saveOrUpdate(role);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("新增或修改失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

    /**
    * 删除
    * @param ids
    * @return
    */
    @ResponseBody
    @RequestMapping("/delByIds")
    public Result delByIds(@RequestParam("ids[]") List<Integer> ids){
        try {
            roleServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
