package com.tensquare.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.article.entity.Article;
import com.tensquare.article.mapper.ArticleMapper;
import com.tensquare.article.service.IArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<Article> list() {

       return articleMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param article  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Article article,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page articlePage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(article.getId()!=null && !"".equals(article.getId())){
            ((QueryWrapper) wrapper).eq("id",article.getId());
        }
        if(article.getColumnId()!=null && !"".equals(article.getColumnId())){
            ((QueryWrapper) wrapper).eq("column_id",article.getColumnId());
        }
        if(article.getUserId()!=null && !"".equals(article.getUserId())){
            ((QueryWrapper) wrapper).eq("user_id",article.getUserId());
        }
        if(article.getTitle()!=null && !"".equals(article.getTitle())){
            ((QueryWrapper) wrapper).eq("title",article.getTitle());
        }
        if(article.getContent()!=null && !"".equals(article.getContent())){
            ((QueryWrapper) wrapper).eq("content",article.getContent());
        }
        if(article.getImage()!=null && !"".equals(article.getImage())){
            ((QueryWrapper) wrapper).eq("image",article.getImage());
        }
        if(article.getCreateTime()!=null && !"".equals(article.getCreateTime())){
            ((QueryWrapper) wrapper).eq("create_time",article.getCreateTime());
        }
        if(article.getUpdateTime()!=null && !"".equals(article.getUpdateTime())){
            ((QueryWrapper) wrapper).eq("update_time",article.getUpdateTime());
        }
        if(article.getIsPublic()!=null && !"".equals(article.getIsPublic())){
            ((QueryWrapper) wrapper).eq("is_public",article.getIsPublic());
        }
        if(article.getIsTop()!=null && !"".equals(article.getIsTop())){
            ((QueryWrapper) wrapper).eq("is_top",article.getIsTop());
        }
        if(article.getVisits()!=null && !"".equals(article.getVisits())){
            ((QueryWrapper) wrapper).eq("visits",article.getVisits());
        }
        if(article.getThumbUp()!=null && !"".equals(article.getThumbUp())){
            ((QueryWrapper) wrapper).eq("thumb_up",article.getThumbUp());
        }
        if(article.getComment()!=null && !"".equals(article.getComment())){
            ((QueryWrapper) wrapper).eq("comment",article.getComment());
        }
        if(article.getState()!=null && !"".equals(article.getState())){
            ((QueryWrapper) wrapper).eq("state",article.getState());
        }
        if(article.getChannelId()!=null && !"".equals(article.getChannelId())){
            ((QueryWrapper) wrapper).eq("channel_id",article.getChannelId());
        }
        if(article.getUrl()!=null && !"".equals(article.getUrl())){
            ((QueryWrapper) wrapper).eq("url",article.getUrl());
        }
        if(article.getType()!=null && !"".equals(article.getType())){
            ((QueryWrapper) wrapper).eq("type",article.getType());
        }
        IPage<Article> articleIPage = articleMapper.selectPage(articlePage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", articlePage.getTotal());
        data.put("pageNo", articlePage.getCurrent());
        data.put("list", articleIPage.getRecords());
        return new Result(data);
    }

}
