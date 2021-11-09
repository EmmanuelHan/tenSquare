package com.tensquare.gathering.controller;

import com.tensquare.gathering.entity.Gathering;
import com.tensquare.gathering.service.IGatheringService;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
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
@RequestMapping("/gathering/gathering")
public class GatheringController {

    @Resource
    private IGatheringService  gatheringServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "gathering/gathering_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param gathering
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Gathering gathering){
        mv.setViewName("gathering/gathering_addOrUpdate");
        if(gathering != null){
            mv.addObject("obj",gathering);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param gathering
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Gathering gathering,Integer page , Integer limit){
        return gatheringServiceImpl.findByParam(gathering, page, limit);
    }

    /**
    * 新增or修改
    * @param gathering
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Gathering gathering){
        try {
            gatheringServiceImpl.saveOrUpdate(gathering);
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
            gatheringServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
