package com.stu.video.entity;

import com.stu.video.vo.UserVo;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author kimijiaqili
 * @since 2020-12-19 14:53:44
 */
public class User implements Serializable {
    private static final long serialVersionUID = 380467811977498468L;
    /**
    * 用户ID，自动增长
    */
    private Integer id;
    /**
    * 用户名
    */
    private String username;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 用户头像，如果没有则选用默认头像
    */
    private String avatarUrl;
    /**
    * 用户类型：0平台注册用户、1微信、2QQ，默认为0
    */
    private Integer userType;
    /**
    * 用户邮箱，用于发送验证码
    */
    private String email;
    /**
    * 用户电话号码，可为空
    */
    private Integer telephone;
    /**
    * 用户个人说说
    */
    private String description;
    /**
    * 粉丝数量
    */
    private Integer fansCount;
    /**
    * 关注数量
    */
    private Integer followsCount;
    /**
    * 收到的点赞数
    */
    private Integer receiveLikeCount;
    /**
    * 账号创建时间
    */
    private Date createTime;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getFollowsCount() {
        return followsCount;
    }

    public void setFollowsCount(Integer followsCount) {
        this.followsCount = followsCount;
    }

    public Integer getReceiveLikeCount() {
        return receiveLikeCount;
    }

    public void setReceiveLikeCount(Integer receiveLikeCount) {
        this.receiveLikeCount = receiveLikeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserVo transformToVo() {
        UserVo userVo = new UserVo();
        userVo.setId(this.id);
        userVo.setUsername(this.username);
        userVo.setAvatarUrl(this.avatarUrl);
        userVo.setUserType(this.userType);
        userVo.setEmail(this.email);
        userVo.setTelephone(this.telephone);
        userVo.setDescription(this.description);
        userVo.setFansCount(this.fansCount);
        userVo.setFollowsCount(this.followsCount);
        userVo.setReceiveLikeCount(this.receiveLikeCount);
        userVo.setCreateTime(this.createTime);

        return userVo;
    }

}