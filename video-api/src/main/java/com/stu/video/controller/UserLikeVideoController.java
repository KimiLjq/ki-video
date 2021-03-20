package com.stu.video.controller;

import com.stu.video.entity.UserLikeVideo;
import com.stu.video.rest.Rest;
import com.video.service.impl.UserLikeVideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/19 13:16
 * @Version: 1.0
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("ki-video/userLikeVideo")
public class UserLikeVideoController {
    @Autowired
    private UserLikeVideoServiceImpl userLikeVideoService;

    @RequestMapping(value = "/queryLikeVideo", method = RequestMethod.POST)
    public Rest<UserLikeVideo> queryLikeVideo(String username, Integer videoId) {
        return userLikeVideoService.queryLikeVideo(username, videoId);
    }

    @RequestMapping(value = "/addLikeVideo", method = RequestMethod.POST)
    public Rest<UserLikeVideo> addLikeVideo(String username, Integer videoId, @RequestParam(required = false) String author) {
        return userLikeVideoService.addLikeVideo(username, videoId, author);
    }

    @RequestMapping(value = "/cancelLikeVideo", method = RequestMethod.POST)
    public Rest<String> cancelLikeVideo(String username, Integer videoId, @RequestParam(required = false) String author) {
        return userLikeVideoService.cancelLikeVideo(username, videoId, author);
    }
}