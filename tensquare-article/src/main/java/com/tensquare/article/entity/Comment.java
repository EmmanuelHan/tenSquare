package com.tensquare.article.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {

    @Id
    private String _id;

    private String articleId;

    private String content;

    private String userId;

    private String parentId;

    private Date publishDate;

    private Integer thumbsUp;

}
