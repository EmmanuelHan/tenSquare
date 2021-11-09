package com.tensquare.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.article.entity.Channel;
import com.tensquare.common.entity.Result;

/**
 * 代码生成器
 * service 接口
 *
 * @Author HanLei
 * @Date 2020-03-12
 */
public interface IChannelService extends IService<Channel> {

    /**
     * 分页查询
     *
     * @param channel
     * @param page
     * @param limit
     * @return
     */
    Result findByParam(Channel channel, Integer page, Integer limit);

    /**
     * 增加频道
     *
     * @param channel
     * @return
     */
    Result channelAdd(Channel channel);

    /**
     * 频道全部列表
     *
     * @return
     */
    Result channelAllList();

    /**
     * 根据ID查询频道
     *
     * @param channelId
     * @return
     */
    Result channelById(String channelId);

    /**
     * 修改频道
     *
     * @param channelId
     * @param channel
     * @return
     */
    Result channelEdit(String channelId, Channel channel);

    /**
     * 根据ID删除频道
     *
     * @param channelId
     * @return
     */
    Result channelDeleteById(String channelId);

    /**
     * 根据参数查询频道
     *
     * @param channel
     * @return
     */
    Result channelByParam(Channel channel);

    /**
     * 查询频道并分页
     *
     * @param channel
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result channelByParamWithPage(Channel channel, int pageNo, int pageSize);
}
