package com.tensquare.article.repository;

import com.tensquare.article.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByArticleId(String articleId);
}
