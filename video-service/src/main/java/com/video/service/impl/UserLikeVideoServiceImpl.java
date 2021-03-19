package com.video.service.impl;

import com.stu.video.aspect.OutputException;
import com.stu.video.entity.UserLikeVideo;
import com.stu.video.entity.Video;
import com.stu.video.enums.RestCode;
import com.stu.video.mapper.UserDao;
import com.stu.video.mapper.UserLikeVideoDao;
import com.stu.video.mapper.VideoDao;
import com.stu.video.rest.Rest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/19 10:48
 * @Version: 1.0
 * @Description:
 */
@Component
public class UserLikeVideoServiceImpl {
    @Autowired
    private UserLikeVideoDao userLikeVideoDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private VideoDao videoDao;

    public Rest<UserLikeVideo> addLikeVideo(String username, Integer videoId, String author) throws OutputException {
        UserLikeVideo userLikeVideo = new UserLikeVideo();
        userLikeVideo.setUsername(username);
        userLikeVideo.setVideoId(videoId);
        if (userLikeVideoDao.insert(userLikeVideo) > 0) {
            if (StringUtils.isBlank(author)) {
                userDao.addReceiveLikeCount(author);
            }
            videoDao.addLikeCount(videoId);
            return new Rest<>(RestCode.SUCCEED, "点赞成功", userLikeVideo);
        }

        throw new OutputException(RestCode.UNKNOWN, "点赞失败");
    }

    public Rest<String> cancelLikeVideo(String username, Integer videoId, String author) throws OutputException {
        if (userLikeVideoDao.deleteById(username, videoId) > 0) {
            if (StringUtils.isBlank(author)) {
                userDao.deleteReceiveLikeCount(author);
            }
            videoDao.addLikeCount(videoId);
            return new Rest<>(RestCode.SUCCEED, "取消点赞成功");
        }

        throw new OutputException(RestCode.UNKNOWN, "取消点赞失败");
    }


}
