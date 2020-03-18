package com.tensquare.gathering.entity;

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
@TableName("gathering")
public class Gathering implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 编号
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 活动名称
    */
    @TableField("name")
    private String name;

    /**
    * 大会简介
    */
    @TableField("summary")
    private String summary;

    /**
    * 详细说明
    */
    @TableField("detail")
    private String detail;

    /**
    * 主办方
    */
    @TableField("sponsor")
    private String sponsor;

    /**
    * 活动图片
    */
    @TableField("image")
    private String image;

    /**
    * 开始时间
    */
    @TableField("start_time")
    private Date startTime;

    /**
    * 截止时间
    */
    @TableField("end_time")
    private Date endTime;

    /**
    * 举办地点
    */
    @TableField("address")
    private String address;

    /**
    * 报名截止
    */
    @TableField("enroll_time")
    private Date enrollTime;

    /**
    * 是否可见
    */
    @TableField("state")
    private String state;

    /**
    * 城市
    */
    @TableField("city")
    private String city;


}
