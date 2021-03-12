package com.stu.video.controller;

import com.stu.video.entity.Comment;
import com.stu.video.rest.Rest;
import com.stu.video.vo.CommentsVo;
import com.video.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/11 20:35
 * @Version: 1.0
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("ki-video/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @RequestMapping(value = "/postComment", method = RequestMethod.POST)
    @ResponseBody
    public Rest<CommentsVo> postComment(String fromUsername, @RequestParam(required = false) String toUsername, String fromUserAvatar,
                                     Integer videoId, @RequestParam(required = false) Integer fatherCommentId, String content) {
        return this.commentService.postComment(fromUsername, toUsername, fromUserAvatar, videoId, fatherCommentId, content);
    }

    @RequestMapping(value = "/getComments", method = RequestMethod.POST)
    @ResponseBody
    public Rest<List<CommentsVo>> getComments(Integer videoId) {
        return this.commentService.getComments(videoId);
    }
}
