package com.tensquare.article.controller;

import com.tensquare.article.entity.Channel;
import com.tensquare.article.service.IChannelService;
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
@RequestMapping("/article/channel")
public class ChannelController {

    @Resource
    private IChannelService  channelServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "channel/channel_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param channel
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Channel channel){
        mv.setViewName("channel/channel_addOrUpdate");
        if(channel != null){
            mv.addObject("obj",channel);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param channel
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Channel channel,Integer page , Integer limit){
        return channelServiceImpl.findByParam(channel, page, limit);
    }

    /**
    * 新增or修改
    * @param channel
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Channel channel){
        try {
            channelServiceImpl.saveOrUpdate(channel);
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
            channelServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
