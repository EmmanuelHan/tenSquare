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
@TableName("column")
public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 专栏名称
    */
    @TableField("name")
    private String name;

    /**
    * 专栏简介
    */
    @TableField("summary")
    private String summary;

    /**
    * 用户ID
    */
    @TableField("user_id")
    private String userId;

    /**
    * 申请日期
    */
    @TableField("create_time")
    private Date createTime;

    /**
    * 审核日期
    */
    @TableField("check_time")
    private Date checkTime;

    /**
    * 状态
    */
    @TableField("state")
    private String state;


}
