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



 }
