package com.tensquare.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* 代码生成器生成
* @Author HanLei
* @Date   2020-03-12
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 专栏ID
    */
    @TableField("column_id")
    private String columnId;

    /**
    * 用户ID
    */
    @TableField("user_id")
    private String userId;

    /**
    * 标题
    */
    @TableField("title")
    private String title;

    /**
    * 文章正文
    */
    @TableField("content")
    private String content;

    /**
    * 文章封面
    */
    @TableField("image")
    private String image;

    /**
    * 发表日期
    */
    @TableField("create_time")
    private Date createTime;

    /**
    * 修改日期
    */
    @TableField("update_time")
    private Date updateTime;

    /**
    * 是否公开
    */
    @TableField("is_public")
    private String isPublic;

    /**
    * 是否置顶
    */
    @TableField("is_top")
    private String isTop;

    /**
    * 浏览量
    */
    @TableField("visits")
    private Integer visits;

    /**
    * 点赞数
    */
    @TableField("thumb_up")
    private Integer thumbUp;

    /**
    * 评论数
    */
    @TableField("comment")
    private Integer comment;

    /**
    * 审核状态
    */
    @TableField("state")
    private String state;

    /**
    * 所属频道
    */
    @TableField("channel_id")
    private String channelId;

    /**
    * URL
    */
    @TableField("url")
    private String url;

    /**
    * 类型
    */
    @TableField("type")
    private String type;


}
