package com.stu.video.vo;

import java.io.Serializable;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/20 14:50
 * @Version: 1.0
 * @Description:
 */
public class FollowUserVo implements Serializable {
    private static final long serialVersionUID = 10L;

    private Integer id;

    private String username;

    private String avatarUrl;

    private String description;

    private boolean follow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }
}
