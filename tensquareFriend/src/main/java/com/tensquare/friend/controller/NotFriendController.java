package com.tensquare.friend.controller;

import com.tensquare.friend.service.INotFriendService;
import com.tensquare.friend.entity.NotFriend;
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
 * @Date   2020-06-19
 */
@Slf4j
@Controller
@RequestMapping("/friend/notfriend")
public class NotFriendController {

    @Resource
    private INotFriendService  notFriendServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "notFriend/notFriend_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param notFriend
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,NotFriend notFriend){
        mv.setViewName("notFriend/notFriend_addOrUpdate");
        if(notFriend != null){
            mv.addObject("obj",notFriend);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param notFriend
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(NotFriend notFriend,Integer page , Integer limit){
        return notFriendServiceImpl.findByParam(notFriend, page, limit);
    }

    /**
    * 新增or修改
    * @param notFriend
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(NotFriend notFriend){
        try {
            notFriendServiceImpl.saveOrUpdate(notFriend);
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
            notFriendServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
