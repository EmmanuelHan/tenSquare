package com.tensquare.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.article.entity.Article;
import entity.Result;

/**
 * 代码生成器
 * service 接口
 *
 * @Author HanLei
 * @Date 2020-03-12
 */
public interface IArticleService extends IService<Article> {

    /**
     * 分页查询
     *
     * @param article
     * @param page
     * @param limit
     * @return
     */
    Result findByParam(Article article, Integer page, Integer limit);

    /**
     * 增加文章
     * @param article
     * @return
     */
    Result addArticle(Article article);

    /**
     * 文章全部列表
     * @return
     */
    Result getList();

    /**
     * 根据ID查询文章
     * @param articleId
     * @return
     */
    Result getArticleById(String articleId);

    /**
     * 修改文章
     * @param articleId
     * @param article
     * @return
     */
    Result articleEdit(String articleId, Article article);

    /**
     * 根据ID删除文档
     * @param articleId
     * @return
     */
    Result articleDeleteById(String articleId);

    /**
     * 根据条件查询文章列表
     * @param article
     * @return
     */
    Result articleSearchByParam(Article article);

    /**
     * 文章分页
     * @param article
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result articleSearchByParamWithPage(Article article, int pageNo, int pageSize);

    /**
     * 点赞
     * @param articleId
     * @return
     */
    Result articleThumbUpById(String articleId);

    /**
     * 根据频道ID获取文章列表
     * @param channelId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result articleByChannelIdWithPage(String channelId, int pageNo, int pageSize);

    /**
     * 根据专栏ID获取文章列表
     * @param columnId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result articleByColumnIdWithPage(String columnId, int pageNo, int pageSize);

    /**
     * 文章审核
     * @param articleId
     * @return
     */
    Result articleExamineById(String articleId);

    /**
     * 头条文章
     * @return
     */
    Result articleTopList();

}
