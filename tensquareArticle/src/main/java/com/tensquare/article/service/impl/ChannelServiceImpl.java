package com.tensquare.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.article.entity.Channel;
import com.tensquare.article.mapper.ChannelMapper;
import com.tensquare.article.service.IChannelService;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import util.StringUtil;

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
        Page channelPage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(channel.getId()!=null && !"".equals(channel.getId())){
            ((QueryWrapper) wrapper).eq("id",channel.getId());
        }
        if(channel.getName()!=null && !"".equals(channel.getName())){
            ((QueryWrapper) wrapper).eq("name",channel.getName());
        }
        if(channel.getState()!=null && !"".equals(channel.getState())){
            ((QueryWrapper) wrapper).eq("state",channel.getState());
        }
        IPage<Channel> channelIPage = channelMapper.selectPage(channelPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", channelPage.getTotal());
        data.put("pageNo", channelPage.getCurrent());
        data.put("list", channelIPage.getRecords());
        return new Result(data);
    }

}
