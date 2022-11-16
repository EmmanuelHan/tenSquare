package com.tensquare.notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hanlei
 * @since 2022-10-28
 */
@Data
@TableName("tb_notice_fresh")
public class NoticeFresh implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 通知id
     */
    private String noticeId;

}
