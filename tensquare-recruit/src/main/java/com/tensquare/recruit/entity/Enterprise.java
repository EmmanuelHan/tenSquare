package com.tensquare.recruit.entity;

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
@TableName("tb_enterprise")
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 企业名称
    */
    @TableField("name")
    private String name;

    /**
    * 企业简介
    */
    @TableField("summary")
    private String summary;

    /**
    * 企业地址
    */
    @TableField("address")
    private String address;

    /**
    * 标签列表
    */
    @TableField("labels")
    private String labels;

    /**
    * 坐标
    */
    @TableField("coordinate")
    private String coordinate;

    /**
    * 是否热门
    */
    @TableField("ishot")
    private String ishot;

    /**
    * LOGO
    */
    @TableField("logo")
    private String logo;

    /**
    * 职位数
    */
    @TableField("job_count")
    private Integer jobCount;

    /**
    * URL
    */
    @TableField("url")
    private String url;


}
