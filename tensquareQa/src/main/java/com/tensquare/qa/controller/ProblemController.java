package com.tensquare.qa.controller;

import com.tensquare.qa.entity.Problem;
import com.tensquare.qa.service.IProblemService;
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
@RequestMapping("/qa/problem")
public class ProblemController {

    @Resource
    private IProblemService  problemServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "problem/problem_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param problem
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Problem problem){
        mv.setViewName("problem/problem_addOrUpdate");
        if(problem != null){
            mv.addObject("obj",problem);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param problem
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Problem problem,Integer page , Integer limit){
        return problemServiceImpl.findByParam(problem, page, limit);
    }

    /**
    * 新增or修改
    * @param problem
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Problem problem){
        try {
            problemServiceImpl.saveOrUpdate(problem);
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
            problemServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
