package com.tensquare.search.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tensquare.search.entity.Article;
import com.tensquare.search.entity.Paging;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    /**
     * 该方法不会是接口，这个方法只是测试，保存搜索库的方法是不是通行的
     * @param article
     * @return
     */
    @RequestMapping(method= RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleSearchService.save(article);
        return new Result(ResultEnum.SUCCESS);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result selectByKey(@RequestBody String param){
        JSONObject jsonObject = JSON.parseObject(param);
        String key = jsonObject.getString("key");
        Paging paging = JSON.parseObject(param, Paging.class);
        Page<Article> articles = articleSearchService.selectByKey(key,paging.getPageNo(),paging.getPageSize());
        return new Result(new PageResult<Article>(articles.getTotalElements(),articles.getContent()));
    }

}
