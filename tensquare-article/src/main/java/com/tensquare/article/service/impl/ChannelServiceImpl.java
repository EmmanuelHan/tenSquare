package com.tensquare.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.article.entity.Channel;
import com.tensquare.article.mapper.ChannelMapper;
import com.tensquare.article.service.IChannelService;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
import com.tensquare.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date   2020-03-12
 */
@Slf4j
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements IChannelService {

    @Resource
    private ChannelMapper channelMapper;

    @Override
    public List<Channel> list() {

       return channelMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param channel  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Channel channel,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        IPage<Channel> channelPage = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<Channel> wrapper = new QueryWrapper<>();

        if(!ObjectUtils.isEmpty(channel.getId())){
            wrapper.eq("id",channel.getId());
        }
        if(!ObjectUtils.isEmpty(channel.getName())){
            wrapper.eq("name",channel.getName());
        }
        if(!ObjectUtils.isEmpty(channel.getState())){
            wrapper.eq("state",channel.getState());
        }
        IPage<Channel> channelIPage = page(channelPage, wrapper);

        return new Result(channelIPage);
    }


    /**
     * 增加频道
     *
     * @param channel
     * @return
     */
    @Override
    public Result channelAdd(Channel channel){
        channel.setState("1");
        save(channel);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 频道全部列表
     *
     * @return
     */
    @Override
    public Result channelAllList(){
        QueryWrapper<Channel> wrapper = new QueryWrapper<>();
        wrapper.eq("state","1");
        List<Channel> list = list(wrapper);
        Map<String,Object> data = new HashMap<>();
        data.put("list",list);
        return new Result(data);
    }

    /**
     * 根据ID查询频道
     *
     * @param channelId
     * @return
     */
    @Override
    public Result channelById(String channelId){
        Channel channel = getById(channelId);
        Map<String,Object> data = new HashMap<>();
        data.put("channel",channel);
        return new Result(data);
    }

    /**
     * 修改频道
     *
     * @param channelId
     * @param channel
     * @return
     */
    @Override
    public Result channelEdit(String channelId, Channel channel){
        channel.setId(channelId);
        updateById(channel);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据ID删除频道
     *
     * @param channelId
     * @return
     */
    @Override
    public Result channelDeleteById(String channelId){
        UpdateWrapper<Channel> wrapper = new UpdateWrapper<>();
        wrapper.set("state","0");
        wrapper.eq("id",channelId);
        update(wrapper);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据参数查询频道
     *
     * @param channel
     * @return
     */
    @Override
    public Result channelByParam(Channel channel){
        QueryWrapper<Channel> wrapper = new QueryWrapper<>();
        wrapper.like("name",channel.getName());
        wrapper.eq("state","1");
        List<Channel> list = list(wrapper);
        Map<String,Object> data = new HashMap<>();
        data.put("list",list);
        return new Result(data);
    }

    /**
     * 查询频道并分页
     *
     * @param channel
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Result channelByParamWithPage(Channel channel, int pageNo, int pageSize){

        //开启分页
        IPage<Channel> channelPage = new Page<>(pageNo,pageSize);

        QueryWrapper<Channel> wrapper = new QueryWrapper<>();
        wrapper.like("name",channel.getName());
        wrapper.eq("state","1");

        IPage<Channel> channelIPage = page(channelPage, wrapper);

        return new Result(channelIPage);
    }

}
