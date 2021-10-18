package com.tensquare.gathering.controller;

import com.tensquare.gathering.entity.UserGath;
import com.tensquare.gathering.service.IUserGathService;
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
 * @Date   2020-03-12
 */
@Slf4j
@Controller
@RequestMapping("/gathering/usergath")
public class UserGathController {

    @Resource
    private IUserGathService  userGathServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "userGath/userGath_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param userGath
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,UserGath userGath){
        mv.setViewName("userGath/userGath_addOrUpdate");
        if(userGath != null){
            mv.addObject("obj",userGath);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param userGath
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(UserGath userGath,Integer page , Integer limit){
        return userGathServiceImpl.findByParam(userGath, page, limit);
    }

    /**
    * 新增or修改
    * @param userGath
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(UserGath userGath){
        try {
            userGathServiceImpl.saveOrUpdate(userGath);
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
            userGathServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
