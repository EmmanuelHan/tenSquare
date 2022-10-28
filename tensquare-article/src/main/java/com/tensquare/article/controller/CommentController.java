package com.tensquare.article.controller;

import com.tensquare.article.entity.Comment;
import com.tensquare.article.entity.ResultEnum;
import com.tensquare.article.service.impl.CommentServiceImpl;
import com.tensquare.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    //根据id查询评论
    @GetMapping("{id}")
    public Result findById(@PathVariable String id) {
        return commentService.findById(id);
    }

    //查询所有
    @GetMapping
    public Result findAll() {
        return commentService.findAll();
    }

    //新增
    @PostMapping
    public Result save(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    //修改
    @PutMapping("{id}")
    public Result update(@PathVariable String id,
                         @RequestBody Comment comment) {
        comment.set_id(id);
        return commentService.update(comment);
    }

    //删除
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable String id) {
        return commentService.deleteById(id);
    }


    @GetMapping("{articleId}")
    public Result findByArticleId(@PathVariable String articleId){
        return new Result(commentService.findByArticleId(articleId));
    }

    @PutMapping("thumbsUp/{id}")
    public Result thumbsUp(@PathVariable String id){
        if(ObjectUtils.isEmpty(id)){
            return new Result(ResultEnum.PARAM);
        }
        String userId = "123";
        Object record = redisTemplate.opsForValue().get("thumbsUp_" + userId + "_" + id);
        if(record != null){
            return new Result(ResultEnum.ARTICLE_THUMBS_UP);
        }
        redisTemplate.opsForValue().set("thumbsUp_" + userId + "_" + id,1);
        commentService.thumbsUp(id);
        return new Result();
    }

}
