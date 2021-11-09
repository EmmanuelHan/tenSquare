package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tensquare.common.util.SnowFlakeIdGenerator;

import javax.annotation.Resource;

@Service
public class ArticleSearchService {

    @Resource
    private ArticleSearchDao articleSearchDao;

    @Resource
    private SnowFlakeIdGenerator idGenerator;

    /**
     * 增加文章
     * @param article 保存的文章信息
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
