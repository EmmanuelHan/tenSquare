package com.tensquare.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.article.entity.Channel;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IChannelService extends IService<Channel> {

   /**
    * 分页查询
    * @param channel
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Channel channel, Integer page, Integer limit);
}
