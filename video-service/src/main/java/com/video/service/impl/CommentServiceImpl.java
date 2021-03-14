package com.video.service.impl;

import com.stu.video.entity.Comment;
import com.stu.video.enums.RestCode;
import com.stu.video.mapper.CommentDao;
import com.stu.video.rest.Rest;
import com.stu.video.util.TransformToVoUtil;
import com.stu.video.vo.CommentVo;
import com.stu.video.vo.CommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/11 20:36
 * @Version: 1.0
 * @Description:
 */
@Component
public class CommentServiceImpl {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TransformToVoUtil<Comment, CommentVo> transformToVoUtil;

    public Rest<CommentsVo> postComment(String fromUsername, String toUsername, String fromUserAvatar,
                                    Integer videoId, Integer fatherCommentId, String content) {
        Comment comment = new Comment();
        comment.setFromUsername(fromUsername);
        comment.setToUsername(toUsername);
        comment.setFromUserAvatar(fromUserAvatar);
        comment.setVideoId(videoId);
        comment.setFatherCommentId(fatherCommentId);
        comment.setContent(content);

        commentDao.insert(comment);
        
        comment.setCreateTime(new Date());
        CommentVo commentVo = comment.transformToVo();

        CommentsVo commentsVo = new CommentsVo();
        commentsVo.setId(comment.getCommentId());
        commentsVo.setFirstComment(commentVo);
        return new Rest<>(RestCode.SUCCEED, "评论成功", commentsVo);
    }

    public Rest<List<CommentsVo>> getComments(Integer videoId) {
        List<CommentsVo> commentsVos = new ArrayList<>();
        List<Comment> commentList = commentDao.queryByVideoId(videoId);
        List<CommentVo> commentVoList = transformToVoUtil.transformToVo(commentList);

        Map<Integer, CommentsVo> map = new HashMap<>();
        for(CommentVo commentVo:commentVoList) {
            if (commentVo.getFatherCommentId() == null) {
                CommentsVo commentsVo = new CommentsVo();
                commentsVo.setId(commentVo.getCommentId());
                commentsVo.setFirstComment(commentVo);
                map.put(commentVo.getCommentId(), commentsVo);
            }
            else {
                CommentsVo commentsVo = map.get(commentVo.getFatherCommentId());
                if (commentsVo.getSecondComment() == null) {
                    commentsVo.setSecondComment(new ArrayList<>());
                }
                commentsVo.getSecondComment().add(commentVo);
            }
        }

        for (Map.Entry<Integer, CommentsVo> entry:map.entrySet()) {
            commentsVos.add(0, entry.getValue());
        }

        return new Rest<>(RestCode.SUCCEED, "获取评论数据成功", commentsVos);
    }
}
