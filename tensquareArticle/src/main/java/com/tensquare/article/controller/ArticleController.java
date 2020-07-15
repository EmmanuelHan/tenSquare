package com.tensquare.article.controller;

import com.tensquare.article.entity.Article;
import com.tensquare.article.service.IArticleService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author HanLei
 * @Date 2020-03-12
 */
@Slf4j
@CrossOrigin
@RestController
public class ArticleController {

    @Resource
    private IArticleService articleService;

    /**
     * 增加文章
     */
    @PostMapping("/article")
    public Result articleAdd(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    /**
     * 文章全部列表
     */
    @GetMapping("/article")
    public Result articleList() {
        return articleService.getList();
    }

    /**
     * 根据ID查询文章
     */
    @GetMapping("/article/{articleId}")
    public Result articleById(@PathVariable String articleId) {
        return articleService.getArticleById(articleId);
    }

    /**
     * 修改文章
     */
    @PutMapping("/article/{articleId}")
    public Result articleEdit(@PathVariable String articleId, @RequestBody Article article) {
        return articleService.articleEdit(articleId, article);
    }

    /**
     * 根据ID删除文章
     */
    @DeleteMapping("/article/{articleId}")
    public Result articleDeleteById(@PathVariable String articleId) {
        return articleService.articleDeleteById(articleId);
    }

    /**
     * 根据条件查询文章列表
     */
    @PostMapping("/article/search")
    public Result articleSearchByParam(@RequestBody Article article) {
        return articleService.articleSearchByParam(article);
    }

    /**
     * 文章分页
     */
    @PostMapping("/article/search/{pageNo}/{pageSize}")
    public Result articleSearchByParamWithPage(@RequestBody Article article, @PathVariable int pageNo, @PathVariable int pageSize) {
        return articleService.articleSearchByParamWithPage(article, pageNo, pageSize);
    }

    /**
     * 点赞
     */
    @PutMapping("/article/thumbup/{articleId}")
    public Result articleThumbupById(@PathVariable String articleId) {
        return articleService.articleThumbUpById(articleId);
    }

    /**
     * 根据频道ID获取文章列表
     */
    @PostMapping("/article/channel/{channelId}/{pageNo}/{pageSize}")
    public Result articleChannelByIdWithPage(@PathVariable String channelId, @PathVariable int pageNo,@PathVariable int pageSize) {
        return articleService.articleByChannelIdWithPage(channelId,pageNo,pageSize);
    }

    /**
     * 根据专栏ID获取文章列表
     */
    @PostMapping("/article/column/{columnId}/{pageNo}/{pageSize}")
    public Result articleColumnByIdWithPage(@PathVariable String columnId,@PathVariable int pageNo,@PathVariable int pageSize) {
        return articleService.articleByColumnIdWithPage(columnId, pageNo, pageSize);
    }

    /**
     * 文章审核
     */
    @PutMapping("/article/examine/{articleId}")
    public Result articleExamineById(@PathVariable String articleId) {
        return articleService.articleExamineById(articleId);
    }

    /**
     * 头条文章
     */
    @GetMapping("/article/top")
    public Result articleTopList() {
        return articleService.articleTopList();
    }


}
