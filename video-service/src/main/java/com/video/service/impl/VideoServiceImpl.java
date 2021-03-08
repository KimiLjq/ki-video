package com.video.service.impl;

import com.stu.video.aspect.OutputException;
import com.stu.video.entity.Video;
import com.stu.video.enums.RestCode;
import com.stu.video.mapper.VideoDao;
import com.stu.video.rest.Rest;
import com.stu.video.vo.MarqueeDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/8 14:34
 * @Version: 1.0
 * @Description:
 */
@Component
public class VideoServiceImpl {
    @Autowired
    private VideoDao videoDao;

    public Rest<List<MarqueeDataVo>> marqueeData() throws OutputException {
        List<Video> result = videoDao.queryHotspot(7);
        if (result == null || result.size() == 0) {
            throw new OutputException(RestCode.UNKNOWN, "数据库未找到相关数据");
        }
        List<MarqueeDataVo> videoList = new ArrayList<>();
        for(Video video:result) {
            MarqueeDataVo marqueeDataVo = video.transformToUserVo();
            videoList.add(marqueeDataVo);
        }

        return new Rest<>(RestCode.SUCCEED, "数据请求成功", videoList);
    }
}
