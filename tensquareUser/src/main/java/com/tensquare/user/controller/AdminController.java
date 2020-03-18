package com.tensquare.user.controller;

import com.tensquare.user.entity.Admin;
import com.tensquare.user.service.IAdminService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author HanLei
 * @Date   2020-03-17
 */
@Slf4j
@Controller
@RequestMapping("/mapper/user/admin")
public class AdminController {

    @Resource
    private IAdminService  adminServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "admin/admin_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param admin
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Admin admin){
        mv.setViewName("admin/admin_addOrUpdate");
        if(admin != null){
            mv.addObject("obj",admin);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param admin
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Admin admin,Integer page , Integer limit){
        return adminServiceImpl.findByParam(admin, page, limit);
    }

    /**
    * 新增or修改
    * @param admin
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Admin admin){
        try {
            adminServiceImpl.saveOrUpdate(admin);
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
            adminServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
