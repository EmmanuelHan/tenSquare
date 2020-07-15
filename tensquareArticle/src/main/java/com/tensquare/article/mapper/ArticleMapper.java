package com.tensquare.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 代码生成器
 * Mapper 接口
 * @Author HanLei
 * @Date   2020-03-12
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    void updateThumbUpById(String id);

}
