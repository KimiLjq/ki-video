package com.stu.video.vo;

import com.stu.video.entity.Video;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/9 11:28
 * @Version: 1.0
 * @Description:
 */
public class VideoCategoryVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private String firstType;
    private String secondType;
    private boolean isMulti;
    private List<VideoVo> data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isMulti() {
        return isMulti;
    }

    public void setMulti(boolean multi) {
        isMulti = multi;
    }

    public List<VideoVo> getData() {
        return data;
    }

    public void setData(List<VideoVo> data) {
        this.data = data;
    }
}
