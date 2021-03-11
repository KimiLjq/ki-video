package com.stu.video.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/11 12:05
 * @Version: 1.0
 * @Description:
 */
public class HotTagVo implements Serializable {
    private static final long serialVersionUID = 3L;

    private List<CategoryVo> tagList;
    private List<VideoCategoryVo> videoCategoryList;

    public List<CategoryVo> getTagList() {
        return tagList;
    }

    public void setTagList(List<CategoryVo> tagList) {
        this.tagList = tagList;
    }

    public List<VideoCategoryVo> getVideoCategoryList() {
        return videoCategoryList;
    }

    public void setVideoCategoryList(List<VideoCategoryVo> videoCategoryList) {
        this.videoCategoryList = videoCategoryList;
    }
}
