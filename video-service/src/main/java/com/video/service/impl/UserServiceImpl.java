package com.video.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.video.aspect.OutputException;
import com.stu.video.entity.User;
import com.stu.video.entity.Video;
import com.stu.video.enums.VerificationType;
import com.stu.video.jjwt.JwtInfo;
import com.stu.video.jjwt.JwtTokenService;
import com.stu.video.mapper.UserDao;
import com.stu.video.redis.RedisOperator;
import com.stu.video.rest.Rest;
import com.stu.video.enums.RestCode;
import com.stu.video.util.ImageUtil;
import com.stu.video.util.MailUtil;
import com.stu.video.vo.UserVo;
import com.video.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/19 15:28
 * @Version: 1.0
 * @Description:
 */

@Component
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    public Rest<UserVo> queryUserByUsername(String username) throws OutputException {
        if (StringUtils.isBlank(username)) {
            throw new OutputException(RestCode.DATA_FORMAT_EXCEPTION, "用户名为空");
        }
        User user = userDao.selectByUsername(username);
        if (user == null) {
            throw new OutputException(RestCode.UNFOUND, "不存在该用户");
        }
        UserVo userVo = user.transformToVo();

        return new Rest<>(RestCode.SUCCEED, userVo);
    }

    public Rest<UserVo> queryUserByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new OutputException(RestCode.DATA_FORMAT_EXCEPTION, "邮箱为空");
        }
        User user = userDao.selectByEmail(email);
        if (user == null) {
            throw new OutputException(RestCode.UNFOUND, "用户不存在");
        }
        UserVo userVo = user.transformToVo();

        return new Rest<>(RestCode.SUCCEED, userVo);
    }

    public Rest<String> modifyAvatar(MultipartFile file, HttpServletRequest request) throws IllegalStateException, OutputException {


        if (file == null || file.isEmpty()) throw new OutputException(RestCode.DATA_FORMAT_EXCEPTION, "上传文件为空");
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D:/IDEA/workspace/ki-video/static/avatar/"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String newUrl[] = imageUtil.newFileUrl("headImage", file);
//        String localUrl = newUrl[0];
//        String httpUrl = newUrl[1];
//        boolean compressSuccess = imageUtil.compressFile(file, localUrl);
//        if (!compressSuccess) throw new OutputException(RestCode.DATA_FORMAT_EXCEPTION, "图片格式异常");
//        //数据库处理
//        User user = userMapper.selectByPrimaryKey(id);
//        user.setHeadImage(httpUrl);
//        userMapper.updateByPrimaryKeySelective(user);
//        JSONObject json = new JSONObject();
//        json.put("headImage", httpUrl);
//        return json;
        return new Rest<String>(RestCode.SUCCEED, fileName);
    }
}
