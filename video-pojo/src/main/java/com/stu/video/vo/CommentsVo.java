package com.stu.video.vo;

import com.stu.video.entity.Comment;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/12 15:22
 * @Version: 1.0
 * @Description:
 */
public class CommentsVo implements Serializable {
    private static final long serialVersionUID = 5L;

    private Integer id;

    private Comment firstComment;

    private List<Comment> secondComment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comment getFirstComment() {
        return firstComment;
    }

    public void setFirstComment(Comment firstComment) {
        this.firstComment = firstComment;
    }

    public List<Comment> getSecondComment() {
        return secondComment;
    }

    public void setSecondComment(List<Comment> secondComment) {
        this.secondComment = secondComment;
    }
}
