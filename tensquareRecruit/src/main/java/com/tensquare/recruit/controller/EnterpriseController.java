package com.tensquare.recruit.controller;

import com.tensquare.recruit.entity.Enterprise;
import com.tensquare.recruit.service.IEnterpriseService;
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
@RequestMapping("/recruit/enterprise")
public class EnterpriseController {

    @Resource
    private IEnterpriseService  enterpriseServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "enterprise/enterprise_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param enterprise
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Enterprise enterprise){
        mv.setViewName("enterprise/enterprise_addOrUpdate");
        if(enterprise != null){
            mv.addObject("obj",enterprise);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param enterprise
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Enterprise enterprise,Integer page , Integer limit){
        return enterpriseServiceImpl.findByParam(enterprise, page, limit);
    }

    /**
    * 新增or修改
    * @param enterprise
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Enterprise enterprise){
        try {
            enterpriseServiceImpl.saveOrUpdate(enterprise);
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
            enterpriseServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
