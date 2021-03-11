package com.video.service.impl;

import com.stu.video.aspect.OutputException;
import com.stu.video.entity.Video;
import com.stu.video.enums.RestCode;
import com.stu.video.mapper.VideoDao;
import com.stu.video.rest.Rest;
import com.stu.video.util.TransformToVoUtil;
import com.stu.video.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TransformToVoUtil<Video, VideoVo> transformToVoUtil;

    public Rest<List<VideoVo>> marqueeData() throws OutputException {
        List<Video> result = videoDao.queryHotspot(7);
        if (result == null || result.size() == 0) {
            throw new OutputException(RestCode.UNKNOWN, "数据库未找到相关数据");
        }
        List<VideoVo> videoList = new ArrayList<>();
        for(Video video:result) {
            VideoVo videoVo = video.transformToVo();
            videoList.add(videoVo);
        }

        return new Rest<>(RestCode.SUCCEED, "数据请求成功", videoList);
    }

    public Rest<List<VideoCategoryVo>> homePage() throws OutputException {
        List<VideoCategoryVo> categoryVoList = new ArrayList<>();

        VideoCategoryVo specialEffect = new VideoCategoryVo();
        specialEffect.setTitle("疯狂特效师");
        specialEffect.setFirstType("特效");
        specialEffect.setSecondType("疯狂特效师");
        List<Video> specialVideos = videoDao.queryVideosByCategory(specialEffect.getFirstType(), specialEffect.getSecondType());
        List<VideoVo> specialVideosVo = transformToVoUtil.transformToVo(specialVideos);
        specialEffect.setData(specialVideosVo);
        categoryVoList.add(specialEffect);

        VideoCategoryVo game = new VideoCategoryVo();
        game.setTitle("王者皮皮怪");
        game.setFirstType("游戏");
        game.setSecondType("王者皮皮怪");
        List<Video> gameVideos = videoDao.queryVideosByCategory(game.getFirstType(), game.getSecondType());
        List<VideoVo> gameVideosVo = transformToVoUtil.transformToVo(gameVideos);
        game.setData(gameVideosVo);
        categoryVoList.add(game);

        VideoCategoryVo vivi = new VideoCategoryVo();
        vivi.setTitle("晏大小姐Vivi");
        vivi.setFirstType("时尚");
        vivi.setSecondType("晏大小姐Vivi");
        List<Video> viviVideos = videoDao.queryVideosByCategory(vivi.getFirstType(), vivi.getSecondType());
        List<VideoVo> viviVideosVo = transformToVoUtil.transformToVo(viviVideos);
        vivi.setData(viviVideosVo);
        categoryVoList.add(vivi);

        VideoCategoryVo mashu = new VideoCategoryVo();
        mashu.setTitle("玛丽小酥");
        mashu.setFirstType("情感");
        mashu.setSecondType("玛丽小酥");
        List<Video> mashuVideos = videoDao.queryVideosByCategory(mashu.getFirstType(), mashu.getSecondType());
        List<VideoVo> mashuVideosVo = transformToVoUtil.transformToVo(mashuVideos);
        mashu.setData(mashuVideosVo);
        categoryVoList.add(mashu);

        return new Rest<>(RestCode.SUCCEED, "数据请求成功", categoryVoList);
    }

    public Rest<VideoCategoryVo> punchline() throws OutputException {
        VideoCategoryVo videoCategoryVo = new VideoCategoryVo();
        videoCategoryVo.setTitle("冷笑话");
        videoCategoryVo.setFirstType("搞笑");
        videoCategoryVo.setSecondType("冷笑话");
        List<Video> videoList = videoDao.queryVideosByCategory(videoCategoryVo.getFirstType(), "");
        List<VideoVo> videoVos = transformToVoUtil.transformToVo(videoList);
        if (videoVos == null || videoVos.size() == 0) {
            throw new OutputException(RestCode.UNFOUND, "数据请求失败");
        }
        videoCategoryVo.setData(videoVos);

        return new Rest<>(RestCode.SUCCEED, "数据请求成功", videoCategoryVo);
    }

    public Rest<VideoCategoryVo> young() throws OutputException {
        VideoCategoryVo videoCategoryVo = new VideoCategoryVo();
        videoCategoryVo.setTitle("玛栗小酥");
        videoCategoryVo.setFirstType("情感");
        videoCategoryVo.setSecondType("玛丽小酥");
        List<Video> videoList = videoDao.queryVideosByCategory(videoCategoryVo.getFirstType(), videoCategoryVo.getSecondType());
        List<VideoVo> videoVos = transformToVoUtil.transformToVo(videoList);
        if (videoVos == null || videoVos.size() == 0) {
            throw new OutputException(RestCode.UNFOUND, "数据请求失败");
        }
        videoCategoryVo.setData(videoVos);

        return new Rest<>(RestCode.SUCCEED, "数据请求成功", videoCategoryVo);
    }

    public Rest<List<VideoCategoryVo>> fashion() throws OutputException {
        List<VideoCategoryVo> categoryVoList = new ArrayList<>();

        List<VideoVo> videoList = transformToVoUtil.transformToVo(videoDao.queryVideosByCategory("时尚", ""));
        Map<String, List<VideoVo>> map = new HashMap<>();
        for (VideoVo videoVo:videoList) {
            List<VideoVo> list = map.get(videoVo.getSecondType());
            if (list == null) {
                list = new ArrayList<>();
                map.put(videoVo.getSecondType(), list);
            }
            list.add(videoVo);
        }

        Integer id = 0;
        for (Map.Entry<String, List<VideoVo>> entry:map.entrySet()) {
            VideoCategoryVo videoCategoryVo = new VideoCategoryVo();
            videoCategoryVo.setId(id++);
            videoCategoryVo.setTitle(entry.getKey());
            videoCategoryVo.setFirstType("时尚");
            videoCategoryVo.setSecondType(entry.getKey());
            videoCategoryVo.setData(entry.getValue());
            categoryVoList.add(videoCategoryVo);
        }

        return new Rest<>(RestCode.SUCCEED, categoryVoList);
    }

    public Rest<HotTagVo> today() {
        HotTagVo hotTagVo = new HotTagVo();
        List<String> categoryList = videoDao.queryAllCategory();
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (int i=1; i<=categoryList.size(); i++) {
            CategoryVo categoryVo = new CategoryVo(i, categoryList.get(i-1));
            categoryVoList.add(categoryVo);
        }
        hotTagVo.setTagList(categoryVoList);

        List<VideoCategoryVo> videoCategoryVos = new ArrayList<>();

        int id = 0;
        VideoCategoryVo vivi = new VideoCategoryVo();
        vivi.setId(id++);
        vivi.setTitle("晏大小姐Vivi");
        vivi.setFirstType("时尚");
        vivi.setSecondType("晏大小姐Vivi");
        List<Video> viviVideos = videoDao.queryVideosByCategory(vivi.getFirstType(), vivi.getSecondType());
        List<VideoVo> viviVideosVo = transformToVoUtil.transformToVo(viviVideos);
        vivi.setData(viviVideosVo);
        videoCategoryVos.add(vivi);

        VideoCategoryVo game = new VideoCategoryVo();
        game.setId(id++);
        game.setTitle("王者皮皮怪");
        game.setFirstType("游戏");
        game.setSecondType("王者皮皮怪");
        List<Video> gameVideos = videoDao.queryVideosByCategory(game.getFirstType(), game.getSecondType());
        List<VideoVo> gameVideosVo = transformToVoUtil.transformToVo(gameVideos);
        game.setData(gameVideosVo);
        videoCategoryVos.add(game);

        hotTagVo.setVideoCategoryList(videoCategoryVos);

        return new Rest<>(RestCode.SUCCEED, hotTagVo);
    }

    public Rest<HotCategoryVideoVo> hotTag(String hotTag) {
        HotCategoryVideoVo result = new HotCategoryVideoVo();

        List<VideoVo> videoList = transformToVoUtil.transformToVo(videoDao.queryVideosByCategory(hotTag, ""));
        Map<String, List<VideoVo>> map = new HashMap<>();
        for (VideoVo videoVo:videoList) {
            List<VideoVo> list = map.get(videoVo.getSecondType());
            if (list == null) {
                list = new ArrayList<>();
                map.put(videoVo.getSecondType(), list);
            }
            list.add(videoVo);
        }

        result.setName(hotTag);
        if (map.size() > 1) {
            result.setMulti(true);
            List<VideoCategoryVo> categoryVoList = new ArrayList<>();
            Integer id = 0;
            for (Map.Entry<String, List<VideoVo>> entry:map.entrySet()) {
                VideoCategoryVo videoCategoryVo = new VideoCategoryVo();
                videoCategoryVo.setId(id++);
                videoCategoryVo.setTitle(entry.getKey());
                videoCategoryVo.setFirstType(hotTag);
                videoCategoryVo.setSecondType(entry.getKey());
                videoCategoryVo.setData(entry.getValue());
                categoryVoList.add(videoCategoryVo);
                result.setTitle(entry.getKey());
            }
            result.setDataList(categoryVoList);
        }
        else {
            result.setMulti(false);
            result.setData(videoList);
            if (videoList.size() > 0) {
                result.setTitle(videoList.get(0).getSecondType());
            }
        }

        return new Rest<>(RestCode.SUCCEED, result);
    }
}
