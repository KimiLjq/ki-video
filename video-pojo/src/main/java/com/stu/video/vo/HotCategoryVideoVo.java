package com.stu.video.vo;

import com.stu.video.entity.Video;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/11 14:49
 * @Version: 1.0
 * @Description:
 */
public class HotCategoryVideoVo implements Serializable {
    private static final long serialVersionUID = 4L;

    private Integer id;

    private String name;

    private String title;

    private boolean isMulti;

    private List<VideoVo> data;

    private List<VideoCategoryVo> dataList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<VideoCategoryVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<VideoCategoryVo> dataList) {
        this.dataList = dataList;
    }
}
