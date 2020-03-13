package com.tensquare.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* 代码生成器生成
* @Author HanLei
* @Date   2020-03-12
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("label")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 标签ID
    */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
    * 标签名称
    */
    @TableField("label_name")
    private String labelName;

    /**
    * 状态
    */
    @TableField("state")
    private String state;

    /**
    * 使用数量
    */
    @TableField("count")
    private Long count;

    /**
    * 是否推荐
    */
    @TableField("recommend")
    private String recommend;

    /**
    * 粉丝数
    */
    @TableField("fans")
    private Long fans;


}
