package com.stu.video.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserLikeVideo)实体类
 *
 * @author kimijiaqili
 * @since 2021-03-19 10:44:44
 */
public class UserLikeVideo implements Serializable {
    private static final long serialVersionUID = -37199439483104380L;
    /**
    * 用户ID
    */
    private String username;
    /**
    * 视频ID
    */
    private Integer videoId;
    /**
    * 创建时间
    */
    private Date createTime;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}