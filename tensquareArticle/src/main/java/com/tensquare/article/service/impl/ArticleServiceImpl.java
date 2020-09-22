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
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import util.StringUtil;

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

        if (!ObjectUtils.isEmpty(article.getId())) {
            wrapper.eq("id", article.getId());
        }
        if (!ObjectUtils.isEmpty(article.getColumnId())) {
            wrapper.eq("column_id", article.getColumnId());
        }
        if (!ObjectUtils.isEmpty(article.getUserId())) {
            wrapper.eq("user_id", article.getUserId());
        }
        if (!ObjectUtils.isEmpty(article.getTitle())) {
            wrapper.eq("title", article.getTitle());
        }
        if (article.getContent() != null && !"".equals(article.getContent())) {
            wrapper.eq("content", article.getContent());
        }
        if (article.getImage() != null && !"".equals(article.getImage())) {
            wrapper.eq("image", article.getImage());
        }
        if (!ObjectUtils.isEmpty(article.getCreateTime())) {
            wrapper.eq("create_time", article.getCreateTime());
        }
        if (!ObjectUtils.isEmpty(article.getUpdateTime())) {
            wrapper.eq("update_time", article.getUpdateTime());
        }
        if (article.getIsPublic() != null && !"".equals(article.getIsPublic())) {
            wrapper.eq("is_public", article.getIsPublic());
        }
        if (article.getIsTop() != null && !"".equals(article.getIsTop())) {
            wrapper.eq("is_top", article.getIsTop());
        }
        if (!ObjectUtils.isEmpty(article.getVisits())) {
            wrapper.eq("visits", article.getVisits());
        }
        if (!ObjectUtils.isEmpty(article.getThumbUp())) {
            wrapper.eq("thumb_up", article.getThumbUp());
        }
        if (!ObjectUtils.isEmpty(article.getComment())) {
            wrapper.eq("comment", article.getComment());
        }
        if (article.getState() != null && !"".equals(article.getState())) {
            wrapper.eq("state", article.getState());
        }
        if (article.getChannelId() != null && !"".equals(article.getChannelId())) {
            wrapper.eq("channel_id", article.getChannelId());
        }
        if (article.getUrl() != null && !"".equals(article.getUrl())) {
            wrapper.eq("url", article.getUrl());
        }
        if (article.getType() != null && !"".equals(article.getType())) {
            wrapper.eq("type", article.getType());
        }
        IPage<Article> articleIPage = page(articlePage, wrapper);

        return new Result(articleIPage);
    }

    /**
     * 增加文章
     *
     * @param article
     * @return
     */
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
        Map<String, Object> data = new HashMap<>();
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

        if (!ObjectUtils.isEmpty(article.getContent())) {
            wrapper.eq("content", article.getContent());
        }
        if (!ObjectUtils.isEmpty(article.getType())) {
            wrapper.eq("type", article.getType());
        }
        IPage<Article> articleIPage = page(articlePage, wrapper);

        Map<String, Object> data = new HashMap<>();
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

        if (!ObjectUtils.isEmpty(channelId)) {
            wrapper.eq("channel_id", channelId);
        }
        IPage<Article> articleIPage = page(articlePage, wrapper);

        return new Result(articleIPage);
    }

    /**
     * 根据专栏ID获取文章列表
     * @param columnId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Result articleByColumnIdWithPage(String columnId, int pageNo, int pageSize){
        //开启分页
        IPage<Article> articlePage = new Page<>(pageNo, pageSize);
        //查询构造器
        QueryWrapper<Article> wrapper = new QueryWrapper<>();

        if (!ObjectUtils.isEmpty(columnId)) {
            wrapper.eq("column_id", columnId);
        }
        IPage<Article> articleIPage = page(articlePage, wrapper);

        return new Result(articleIPage);
    }

    /**
     * 文章审核
     *
     * @param articleId
     * @return
     */
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
        Map<String,Object> data = new HashMap<>();
        data.put("list", list);
        return new Result(data);
    }

}
