package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.SnowFlakeIdGenerator;

@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleSearchDao;

    @Autowired
    private SnowFlakeIdGenerator idGenerator;

    /**
     * 增加文章
     * @param article
     */
    public void save(Article article){
        article.setId(idGenerator.nextId());
        articleSearchDao.save(article);
    }


    public Page<Article> selectByKey(String key, int pageNo, int pageSize) {
        Pageable pageale = PageRequest.of(pageNo-1,pageSize);
        return articleSearchDao.findByTitleOrContentLike(key,key,pageale);
    }
}
