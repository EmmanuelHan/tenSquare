package com.tensquare.recruit.entity;

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
@TableName("tb_mapper.recruit")
public class Recruit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 职位名称
    */
    @TableField("job_name")
    private String jobName;

    /**
    * 薪资范围
    */
    @TableField("salary")
    private String salary;

    /**
    * 经验要求
    */
    @TableField("condition")
    private String condition;

    /**
    * 学历要求
    */
    @TableField("education")
    private String education;

    /**
    * 任职方式
    */
    @TableField("type")
    private String type;

    /**
    * 办公地址
    */
    @TableField("address")
    private String address;

    /**
    * 企业ID
    */
    @TableField("e_id")
    private String eId;

    /**
    * 创建日期
    */
    @TableField("create_time")
    private Date createTime;

    /**
    * 状态
    */
    @TableField("state")
    private String state;

    /**
    * 网址
    */
    @TableField("url")
    private String url;

    /**
    * 标签
    */
    @TableField("label")
    private String label;

    /**
    * 职位描述
    */
    @TableField("content_descr")
    private String contentDescr;

    /**
    * 职位要求
    */
    @TableField("content_require")
    private String contentRequire;


}
