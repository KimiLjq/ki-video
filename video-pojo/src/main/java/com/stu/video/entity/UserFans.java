package com.stu.video.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserFans)实体类
 *
 * @author kimijiaqili
 * @since 2021-03-17 11:35:18
 */
public class UserFans implements Serializable {
    private static final long serialVersionUID = 582977804086773855L;
    /**
    * 用户ID
    */
    private String username;
    /**
    * 粉丝ID
    */
    private String fansUsername;
    /**
    * 是否互相关注：0否、1是
    */
    private Integer interactFollow;
    /**
    * 关注时间
    */
    private Date createTime;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFansUsername() {
        return fansUsername;
    }

    public void setFansUsername(String fansUsername) {
        this.fansUsername = fansUsername;
    }

    public Integer getInteractFollow() {
        return interactFollow;
    }

    public void setInteractFollow(Integer interactFollow) {
        this.interactFollow = interactFollow;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}