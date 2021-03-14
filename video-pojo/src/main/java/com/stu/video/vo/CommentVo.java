package com.stu.video.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/13 12:36
 * @Version: 1.0
 * @Description:
 */
public class CommentVo implements Serializable {
    private static final long serialVersionUID = 6L;
    /**
     * 评论ID
     */
    private Integer commentId;
    /**
     * 评论人username
     */
    private String fromUsername;
    /**
     * 受评人username
     */
    private String toUsername;
    /**
     * 评论人头像
     */
    private String fromUserAvatar;
    /**
     * 被评论视频ID
     */
    private Integer videoId;
    /**
     * 被回贴的评论ID
     */
    private Integer fatherCommentId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private String createTime;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getFromUserAvatar() {
        return fromUserAvatar;
    }

    public void setFromUserAvatar(String fromUserAvatar) {
        this.fromUserAvatar = fromUserAvatar;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(Integer fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
