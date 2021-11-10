package com.tensquare.qa.entity;

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
@TableName("tb_problem")
public class Problem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 标题
    */
    @TableField("title")
    private String title;

    /**
    * 内容
    */
    @TableField("content")
    private String content;

    /**
    * 创建日期
    */
    @TableField("create_time")
    private Date createTime;

    /**
    * 修改日期
    */
    @TableField("update_time")
    private Date updateTime;

    /**
    * 用户ID
    */
    @TableField("user_id")
    private String userId;

    /**
    * 昵称
    */
    @TableField("nick_name")
    private String nickName;

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
    * 回复数
    */
    @TableField("reply")
    private Integer reply;

    /**
    * 是否解决
    */
    @TableField("solve")
    private String solve;

    /**
    * 回复人昵称
    */
    @TableField("reply_name")
    private String replyName;

    /**
    * 回复日期
    */
    @TableField("reply_time")
    private Date replyTime;


}
