package com.stu.video.controller;

import com.stu.video.entity.Video;
import com.stu.video.rest.Rest;
import com.stu.video.vo.MarqueeDataVo;
import com.stu.video.vo.UserVo;
import com.video.service.impl.UserServiceImpl;
import com.video.service.impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/8 14:35
 * @Version: 1.0
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("ki-video/video")
public class VideoController {
    @Autowired
    private VideoServiceImpl videoService;

    @RequestMapping(value = "/marqueeData", method = RequestMethod.POST)
    @ResponseBody
    public Rest<List<MarqueeDataVo>> marqueeData() {
        return this.videoService.marqueeData();
    }
}
