package com.stu.video.vo;

import java.io.Serializable;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/11 13:47
 * @Version: 1.0
 * @Description:
 */
public class CategoryVo implements Serializable {
    private static final long serialVersionUID = 4L;

    private Integer id;

    private String content;

    public CategoryVo(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
