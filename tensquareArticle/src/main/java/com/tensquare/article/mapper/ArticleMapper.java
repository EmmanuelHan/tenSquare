package com.tensquare.article.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tensquare.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 代码生成器
 * Mapper 接口
 * @Author HanLei
 * @Date   2020-03-12
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 通过XML *Mapper.xml
     * 需要注意配置文件中需要包含准确的路径
     * mybatis-plus:
     *   mapper-locations: classpath:mapper/article/*.xml
     * @param id
     * @url https://mp.baomidou.com/guide/wrapper.html#%E4%BD%BF%E7%94%A8-wrapper-%E8%87%AA%E5%AE%9A%E4%B9%89sql
     */
    void updateThumbUpById(String id);

    /**
     * 方案2
     * @Select("select * from mysql_data ${ew.customSqlSegment}")
     * List<MysqlData> getAll(@Param(Constants.WRAPPER) Wrapper wrapper);
     */
    @Update("")
    void updateById(@Param(Constants.WRAPPER) Wrapper wrapper);


}
