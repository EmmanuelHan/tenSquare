package com.tensquare.article.controller;

import com.tensquare.article.entity.Channel;
import com.tensquare.article.service.IChannelService;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author HanLei
 * @Date 2020-03-12
 */
@Slf4j
@RestController
public class ChannelController {

    @Resource
    private IChannelService channelService;

    /**
     * 增加频道
     */
    @PostMapping("/channel")
    public Result channelAdd(@RequestBody Channel channel) {
        return channelService.channelAdd(channel);
    }

    /**
     * 频道全部列表
     */
    @GetMapping("/channel")
    public Result channelAllList() {
        return channelService.channelAllList();
    }

    /**
     * 根据ID查询频道
     */
    @GetMapping("/channel/{channelId}")
    public Result channelById(@PathVariable String channelId) {
        return channelService.channelById(channelId);
    }

    /**
     * 修改频道
     */
    @PutMapping("/channel/{channelId}")
    public Result channelEdit(@PathVariable String channelId, @RequestBody Channel channel) {
        return channelService.channelEdit(channelId, channel);
    }

    /**
     * 根据ID删除频道
     */
    @DeleteMapping("/channel/{channelId}")
    public Result channelDeleteById(@PathVariable String channelId) {
        return channelService.channelDeleteById(channelId);
    }

    /**
     * 根据条件查询频道列表
     */
    @PostMapping("/channel/search")
    public Result channelByParam(@RequestBody Channel channel) {
        return channelService.channelByParam(channel);
    }

    /**
     * 频道分页
     */
    @PostMapping("/channel/search/{pageNo}/{pageSize}")
    public Result channelByParamWithPage(@RequestBody Channel channel, @PathVariable int pageNo, @PathVariable int pageSize) {
        return channelService.channelByParamWithPage(channel, pageNo, pageSize);
    }


}
