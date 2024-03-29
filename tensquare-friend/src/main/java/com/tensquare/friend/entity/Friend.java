package com.tensquare.friend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 代码生成器生成
* @Author HanLei
* @Date   2020-06-19
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_friend")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 用户ID
    */
    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private String userId;

    /**
    * 好友ID
    */
    @TableId(value = "friend_id",type = IdType.ASSIGN_ID)
    private String friendId;

    /**
    * 是否互相喜欢
    */
    @TableField("like")
    private String like;


}
