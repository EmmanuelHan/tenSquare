package com.tensquare.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.article.entity.Article;
import com.tensquare.article.mapper.ArticleMapper;
import com.tensquare.article.service.IArticleService;
import com.tensquare.common.annotation.Permission;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
import com.tensquare.common.system.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date 2020-03-12
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
     * @param article page  limit
     * @return jsonResponse
     */
    @Override
    public Result findByParam(Article article, Integer page, Integer limit) {

        //开启分页
        IPage<Article> articlePage = new Page<>(page, limit);
        //查询构造器
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq(!ObjectUtils.isEmpty(article.getId()), "id", article.getId());
        wrapper.eq(!ObjectUtils.isEmpty(article.getColumnId()), "column_id", article.getColumnId());
        wrapper.eq(!ObjectUtils.isEmpty(article.getUserId()), "user_id", article.getUserId());
        wrapper.eq(!ObjectUtils.isEmpty(article.getTitle()), "title", article.getTitle());
        wrapper.eq(!ObjectUtils.isEmpty(article.getContent()), "content", article.getContent());
        wrapper.eq(!ObjectUtils.isEmpty(article.getImage()), "image", article.getImage());
        wrapper.eq(!ObjectUtils.isEmpty(article.getCreateTime()), "create_time", article.getCreateTime());
        wrapper.eq(!ObjectUtils.isEmpty(article.getUpdateTime()), "update_time", article.getUpdateTime());
        wrapper.eq(!ObjectUtils.isEmpty(article.getIsPublic()), "is_public", article.getIsPublic());
        wrapper.eq(!ObjectUtils.isEmpty(article.getIsTop()), "is_top", article.getIsTop());
        wrapper.eq(!ObjectUtils.isEmpty(article.getVisits()), "visits", article.getVisits());
        wrapper.eq(!ObjectUtils.isEmpty(article.getThumbUp()), "thumb_up", article.getThumbUp());
        wrapper.eq(!ObjectUtils.isEmpty(article.getComment()), "comment", article.getComment());
        wrapper.eq(!ObjectUtils.isEmpty(article.getState()), "state", article.getState());
        wrapper.eq(!ObjectUtils.isEmpty(article.getChannelId()), "channel_id", article.getChannelId());
        wrapper.eq(!ObjectUtils.isEmpty(article.getUrl()), "url", article.getUrl());
        wrapper.eq(!ObjectUtils.isEmpty(article.getType()), "type", article.getType());
        IPage<Article> articleIPage = page(articlePage, wrapper);
        return new Result(articleIPage);
    }

    /**
     * 增加文章
     *
     * @param article
     * @return
     */
    @Permission(Constants.ROLE_USER)
    @Override
    public Result addArticle(Article article) {
        Date now = new Date();
        article.setCreateTime(now);
        article.setUpdateTime(now);
        article.setVisits(0);
        article.setThumbUp(0);
        article.setComment(0);
        article.setState("2");
        save(article);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 文章全部列表
     *
     * @return
     */
    @Override
    public Result getList() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("state", "1");
        List<Article> list = list(wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        return new Result(data);
    }

    /**
     * 根据ID查询文章
     *
     * @param articleId
     * @return
     */
    @Override
    public Result getArticleById(String articleId) {
        Article article = getById(articleId);
        Map<String, Object> data = new HashMap<>(1);
        data.put("article", article);
        return new Result(data);
    }

    /**
     * 修改文章
     *
     * @param articleId
     * @param article
     * @return
     */
    @Permission(Constants.ROLE_USER)
    @Override
    public Result articleEdit(String articleId, Article article) {
        article.setId(articleId);
        article.setUpdateTime(new Date());
        updateById(article);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据ID删除文档
     *
     * @param articleId
     * @return
     */
    @Permission(Constants.ROLE_USER)
    @Override
    public Result articleDeleteById(String articleId) {
        Article article = new Article();
        article.setId(articleId);
        article.setState("0");
        updateById(article);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据条件查询文章列表
     *
     * @param article
     * @return
     */
    @Override
    public Result articleSearchByParam(Article article) {
        return null;
    }

    /**
     * 文章分页
     *
     * @param article
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Result articleSearchByParamWithPage(Article article, int pageNo, int pageSize) {
        //开启分页
        IPage<Article> articlePage = new Page<>(pageNo, pageSize);
        //查询构造器
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq(!ObjectUtils.isEmpty(article.getContent()), "content", article.getContent());
        wrapper.eq(!ObjectUtils.isEmpty(article.getType()), "type", article.getType());
        IPage<Article> articleIPage = page(articlePage, wrapper);

        Map<String, Object> data = new HashMap<>(4);
        data.put("pageSize", articleIPage.getSize());
        data.put("total", articleIPage.getTotal());
        data.put("pageNo", articleIPage.getCurrent());
        data.put("list", articleIPage.getRecords());
        return new Result(data);
    }

    /**
     * 点赞
     *
     * @param articleId
     * @return
     */
    @Permission(Constants.ROLE_USER)
    @Override
    public Result articleThumbUpById(String articleId) {

        articleMapper.updateThumbUpById(articleId);

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据频道ID获取文章列表
     *
     * @param channelId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Result articleByChannelIdWithPage(String channelId, int pageNo, int pageSize) {
        //开启分页
        IPage<Article> articlePage = new Page<>(pageNo, pageSize);
        //查询构造器
        QueryWrapper<Article> wrapper = new QueryWrapper<>();

        wrapper.eq(!ObjectUtils.isEmpty(channelId), "channel_id", channelId);
        IPage<Article> articleIPage = page(articlePage, wrapper);

        return new Result(articleIPage);
    }

    /**
     * 根据专栏ID获取文章列表
     *
     * @param columnId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Result articleByColumnIdWithPage(String columnId, int pageNo, int pageSize) {
        //开启分页
        IPage<Article> articlePage = new Page<>(pageNo, pageSize);
        //查询构造器
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq(!ObjectUtils.isEmpty(columnId), "column_id", columnId);
        IPage<Article> articleIPage = page(articlePage, wrapper);

        return new Result(articleIPage);
    }

    /**
     * 文章审核
     *
     * @param articleId
     * @return
     */
    @Permission(Constants.ROLE_ADMIN)
    @Override
    public Result articleExamineById(String articleId) {
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 头条文章
     *
     * @return
     */
    @Override
    public Result articleTopList() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("is_top", "1");
        wrapper.orderByDesc("update_time");
        wrapper.last("limit 10");
        List<Article> list = list(wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        return new Result(data);
    }

}
