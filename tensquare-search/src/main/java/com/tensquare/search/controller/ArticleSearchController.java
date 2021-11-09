package com.tensquare.search.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tensquare.search.entity.Article;
import com.tensquare.search.entity.Paging;
import com.tensquare.search.service.ArticleSearchService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {

    @Resource
    private ArticleSearchService articleSearchService;

    /**
     * 该方法不会是接口，这个方法只是测试，保存搜索库的方法是不是通行的
     * @param article
     * @return
     */
    @PostMapping()
    public Result save(@RequestBody Article article){
        articleSearchService.save(article);
        return new Result(ResultEnum.SUCCESS);
    }

    @GetMapping()
    public Result selectByKey(@RequestBody String param){
        JSONObject jsonObject = JSON.parseObject(param);
        String key = jsonObject.getString("key");
        Paging paging = JSON.parseObject(param, Paging.class);
        Page<Article> articles = articleSearchService.selectByKey(key,paging.getPageNo(),paging.getPageSize());
        return new Result(new PageResult<>(articles.getTotalElements(),articles.getContent()));
    }

}
