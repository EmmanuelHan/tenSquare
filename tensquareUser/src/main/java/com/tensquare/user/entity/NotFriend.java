package com.tensquare.user.entity;

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
@TableName("not_friend")
public class NotFriend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 用户ID
    */
    @TableField("user_id")
    private String userId;

    /**
    * 好友ID
    */
    @TableField("friend_id")
    private String friendId;


}
