package com.tensquare.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.article.entity.Article;
import entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface IArticleService extends IService<Article> {

   /**
    * 分页查询
    * @param article
    * @param page
    * @param limit
    * @return
    */
   Result findByParam(Article article, Integer page, Integer limit);
}
