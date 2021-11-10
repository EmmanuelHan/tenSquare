package com.tensquare.qa.entity;

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
@TableName("tb_pl")
public class Pl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 问题ID
    */
    @TableId(value = "problem_id", type = IdType.ASSIGN_ID)
    private String problemId;

    /**
    * 标签ID
    */
    @TableField("label_id")
    private String labelId;


}
