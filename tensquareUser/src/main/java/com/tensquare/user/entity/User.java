package com.tensquare.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 代码生成器生成
 *
 * @Author HanLei
 * @Date 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 出生年月日
     */
    @TableField("birthday")
    private Date birthday;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * E-Mail
     */
    @TableField("email")
    private String email;

    /**
     * 注册日期
     */
    @TableField("reg_date")
    private Date regDate;

    /**
     * 修改日期
     */
    @TableField("update_date")
    private Date updateDate;

    /**
     * 最后登陆日期
     */
    @TableField("last_date")
    private Date lastDate;

    /**
     * 在线时长（分钟）
     */
    @TableField("online")
    private Long online;

    /**
     * 兴趣
     */
    @TableField("interest")
    private String interest;

    /**
     * 个性
     */
    @TableField("personality")
    private String personality;

    /**
     * 粉丝数
     */
    @TableField("fans_count")
    private Integer fansCount;

    /**
     * 关注数
     */
    @TableField("follow_count")
    private Integer followCount;

    /**
     * 状态
     */
    @TableField("state")
    private String state;

}
