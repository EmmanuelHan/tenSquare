package com.tensquare.recruit.controller;

import com.tensquare.recruit.entity.Recruit;
import com.tensquare.recruit.service.IRecruitService;
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
@RequestMapping("/mapper.recruit/recruit")
public class RecruitController {

    @Resource
    private IRecruitService  recruitServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "recruit/recruit_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param recruit
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Recruit recruit){
        mv.setViewName("recruit/recruit_addOrUpdate");
        if(recruit != null){
            mv.addObject("obj",recruit);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param recruit
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Recruit recruit,Integer page , Integer limit){
        return recruitServiceImpl.findByParam(recruit, page, limit);
    }

    /**
    * 新增or修改
    * @param recruit
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Recruit recruit){
        try {
            recruitServiceImpl.saveOrUpdate(recruit);
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
            recruitServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
