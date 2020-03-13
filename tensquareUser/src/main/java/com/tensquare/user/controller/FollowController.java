package com.tensquare.user.controller;

import com.tensquare.user.entity.Follow;
import com.tensquare.user.service.IFollowService;
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
@RequestMapping("/user/follow")
public class FollowController {

    @Resource
    private IFollowService  followServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "follow/follow_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param follow
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Follow follow){
        mv.setViewName("follow/follow_addOrUpdate");
        if(follow != null){
            mv.addObject("obj",follow);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param follow
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Follow follow,Integer page , Integer limit){
        return followServiceImpl.findByParam(follow, page, limit);
    }

    /**
    * 新增or修改
    * @param follow
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Follow follow){
        try {
            followServiceImpl.saveOrUpdate(follow);
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
            followServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
