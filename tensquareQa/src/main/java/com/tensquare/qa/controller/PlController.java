package com.tensquare.qa.controller;

import com.tensquare.qa.entity.Pl;
import com.tensquare.qa.service.IPlService;
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
@RequestMapping("/qa/pl")
public class PlController {

    @Resource
    private IPlService  plServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "pl/pl_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param pl
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Pl pl){
        mv.setViewName("pl/pl_addOrUpdate");
        if(pl != null){
            mv.addObject("obj",pl);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param pl
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Pl pl,Integer page , Integer limit){
        return plServiceImpl.findByParam(pl, page, limit);
    }

    /**
    * 新增or修改
    * @param pl
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Pl pl){
        try {
            plServiceImpl.saveOrUpdate(pl);
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
            plServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
