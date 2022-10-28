package com.tensquare.notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hanlei
 * @since 2022-10-28
 */
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    @Override
    public String toString() {
        return "TbNoticeFresh{" +
            "userId = " + userId +
            ", noticeId = " + noticeId +
        "}";
    }
}
