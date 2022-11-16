package com.tensquare.notice.service;

import com.tensquare.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("tensquare-article")
public interface IArticleClient {

    /**
     * 根据文章id查询文章数据
     * @param articleId 文章编号
     * @return 文章 结果包装
     */
    @GetMapping("article/{articleId}")
    Result findById(@PathVariable("articleId") String articleId);
}
