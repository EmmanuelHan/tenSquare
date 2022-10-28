package com.tensquare.notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author hanlei
 * @since 2022-10-28
 */
@TableName("tb_notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 接收消息用户的ID
     */
    private String receiverId;

    /**
     * 进行操作用户的ID
     */
    private String operatorId;

    /**
     * 操作类型（评论，点赞等）
     */
    private String action;

    /**
     * 被操作的对象，例如文章，评论等
     */
    private String targetType;

    /**
     * 被操作对象的id，例如文章的id，评论的id
     */
    private String targetId;

    /**
     * 发表日期
     */
    private LocalDateTime createtime;

    /**
     * 通知类型
     */
    private String type;

    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TbNotice{" +
            "id = " + id +
            ", receiverId = " + receiverId +
            ", operatorId = " + operatorId +
            ", action = " + action +
            ", targetType = " + targetType +
            ", targetId = " + targetId +
            ", createtime = " + createtime +
            ", type = " + type +
            ", state = " + state +
        "}";
    }
}
