package com.tensquare.article.service.impl;

import com.tensquare.article.config.project.Constant;
import com.tensquare.article.entity.Comment;
import com.tensquare.article.entity.ResultEnum;
import com.tensquare.article.repository.CommentRepository;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.SnowFlakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl {

    @Autowired
    private SnowFlakeIdGenerator snowFlakeIdGenerator;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Constant constant;


    public Result findById(String id) {
        return new Result(commentRepository.findById(id).get());
    }

    public Result findAll() {
        return new Result(commentRepository.findAll());
    }

    public Result save(Comment comment) {
        String id = snowFlakeIdGenerator.nextId();
        comment.set_id(id);

        //初始化数据
        comment.setPublishDate(new Date());
        comment.setThumbsUp(0);
        commentRepository.save(comment);
        return new Result(ResultEnum.SUCCESS);
    }

    public Result update(Comment comment) {
        commentRepository.save(comment);
        return new Result(ResultEnum.SUCCESS);
    }

    public Result deleteById(String id) {
        commentRepository.deleteById(id);
        return new Result(ResultEnum.SUCCESS);
    }


    public List<Comment> findByArticleId(String articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public void thumbsUp(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.inc("thumbsUp",1);
        mongoTemplate.updateFirst(query,update, constant.getDatabase());
    }
}
