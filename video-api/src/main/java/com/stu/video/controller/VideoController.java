package com.stu.video.controller;

import com.stu.video.rest.Rest;
import com.stu.video.vo.HotCategoryVideoVo;
import com.stu.video.vo.HotTagVo;
import com.stu.video.vo.VideoVo;
import com.stu.video.vo.VideoCategoryVo;
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
    public Rest<List<VideoVo>> marqueeData() {
        return this.videoService.marqueeData();
    }

    @RequestMapping(value = "/homePage", method = RequestMethod.POST)
    @ResponseBody
    public Rest<List<VideoCategoryVo>> homePage() {
        return this.videoService.homePage();
    }

    @RequestMapping(value = "/punchline", method = RequestMethod.POST)
    @ResponseBody
    public Rest<VideoCategoryVo> punchline() {
        return this.videoService.punchline();
    }

    @RequestMapping(value = "/young", method = RequestMethod.POST)
    @ResponseBody
    public Rest<VideoCategoryVo> young() {
        return this.videoService.young();
    }

    @RequestMapping(value = "/fashion", method = RequestMethod.POST)
    @ResponseBody
    public Rest<List<VideoCategoryVo>> fashion() {
        return this.videoService.fashion();
    }

    @RequestMapping(value = "/today", method = RequestMethod.POST)
    @ResponseBody
    public Rest<HotTagVo> today() {
        return this.videoService.today();
    }

    @RequestMapping(value = "/hotTag", method = RequestMethod.POST)
    @ResponseBody
    public Rest<HotCategoryVideoVo> hotTag(String hotTag) {
        return this.videoService.hotTag(hotTag);
    }

    @RequestMapping(value = "/recommendVideo", method = RequestMethod.POST)
    @ResponseBody
    public Rest<List<VideoVo>> recommendVideo(String type, Integer videoId) {
        return this.videoService.recommendVideo(type, videoId);
    }
}
