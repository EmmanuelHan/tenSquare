package com.tensquare.notice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author hanlei
 * @since 2022-10-28
 */
@Data
@TableName("tb_notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 接收消息用户的ID
     */
    private String receiverId;

    /**
     * 进行操作用户的ID
     */
    private String operatorId;
    /**
     * 进行操作的用户昵称
     */
    @TableField(exist = false)
    private String operatorName;

    /**
     * 操作类型（评论，点赞等）
     */
    private String action;

    /**
     * 被操作的对象，例如文章，评论等
     */
    private String targetType;

    /**
     * 被操作对象的id，例如文章的id，评论的id
     */
    private String targetId;

    /**
     * 对象名称或简介
     */
    @TableField(exist = false)
    private String targetName;

    /**
     * 发表日期
     */
    private Date createTime;

    /**
     * 通知类型
     */
    private String type;

    private String state;


}
