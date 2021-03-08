package com.stu.video.entity;

import com.stu.video.vo.MarqueeDataVo;
import com.stu.video.vo.UserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.Date;
import java.io.Serializable;

/**
 * (Video)实体类
 *
 * @author kimijiaqili
 * @since 2021-03-08 13:28:07
 */
public class Video implements Serializable {
    private static final long serialVersionUID = -49040348195873469L;

    private String ip = "http://172.16.75.32:8080";
    /**
    * 短视频ID
    */
    private Integer id;
    /**
    * 发布者id
    */
    private Integer userId;
    /**
    * 视频title
    */
    private String title;
    /**
    * 视频封面路径
    */
    private String poster;
    /**
    * 视频存放路径
    */
    private String videoUrl;
    /**
    * 所使用音频的id
    */
    private Integer audioId;
    /**
    * 0：是用户上传的，1：外部资源
    */
    private Integer isUcg;
    /**
    * 视频文件类型
    */
    private String type;
    /**
    * 一级类型
    */
    private String firstType;
    /**
    * 二级类型
    */
    private String secondType;
    /**
    * 视频描述
    */
    private String videoDesc;
    /**
    * 视频播放时长
    */
    private Integer videoSecond;
    /**
    * 视频高度
    */
    private Integer videoHeight;
    /**
    * 视频宽度
    */
    private Integer videoWidth;
    /**
    * 观看次数
    */
    private Integer amount;
    /**
    * 点赞数量
    */
    private Integer likeCount;
    /**
    * 视频上传时间
    */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getAudioId() {
        return audioId;
    }

    public void setAudioId(Integer audioId) {
        this.audioId = audioId;
    }

    public Integer getIsUcg() {
        return isUcg;
    }

    public void setIsUcg(Integer isUcg) {
        this.isUcg = isUcg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstType() {
        return firstType;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public Integer getVideoSecond() {
        return videoSecond;
    }

    public void setVideoSecond(Integer videoSecond) {
        this.videoSecond = videoSecond;
    }

    public Integer getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public Integer getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public MarqueeDataVo transformToUserVo() {
        MarqueeDataVo marqueeDataVo = new MarqueeDataVo();
        marqueeDataVo.setId(this.getId());
        marqueeDataVo.setActived(false);
        marqueeDataVo.setAmount(this.getAmount());
        marqueeDataVo.setAudioId(this.getAudioId());
        marqueeDataVo.setCreateTime(this.getCreateTime());
        marqueeDataVo.setFirstType(this.getFirstType());
        marqueeDataVo.setIsUcg(this.getIsUcg());
        marqueeDataVo.setLikeCount(this.getLikeCount());
        marqueeDataVo.setPoster(this.ip + this.getPoster());
        marqueeDataVo.setSecondType(this.getSecondType());
        marqueeDataVo.setTitle(this.getTitle());
        marqueeDataVo.setType(this.getType());
        marqueeDataVo.setUserId(this.getUserId());
        marqueeDataVo.setVideoDesc(this.getVideoDesc());
        marqueeDataVo.setVideoHeight(this.getVideoHeight());
        marqueeDataVo.setVideoSecond(this.getVideoSecond());
        marqueeDataVo.setVideoUrl(this.getVideoUrl());
        marqueeDataVo.setVideoWidth(this.getVideoWidth());

        return marqueeDataVo;
    }

}