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
@TableName("user_gath")
public class UserGath implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 用户ID
    */
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String userId;

    /**
    * 活动ID
    */
    @TableField("gath_id")
    private String gathId;

    /**
    * 点击时间
    */
    @TableField("exe_time")
    private Date exeTime;


}
