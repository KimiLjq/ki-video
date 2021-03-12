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
public class UserServiceImpl implements IUserService {
    public static final String VERIFICATION_REDIS_KEY = "verify:%s type:%s";

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private ImageUtil imageUtil;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public Rest<UserVo> login(String username, String password) throws OutputException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new OutputException(RestCode.DATA_FORMAT_EXCEPTION, "用户名或密码不能为空");
        }
        User user = userDao.selectByUsername(username);
        if (user == null) {
            throw new OutputException(RestCode.UNFOUND, "未找到该用户");
        }
        else if (!user.getPassword().equals(password)) {
            throw new OutputException(RestCode.UNFOUND, "密码错误");
        }

        JwtInfo jwtInfo = new JwtInfo(username);
        String token = jwtTokenService.generatorToken(jwtInfo);

        UserVo userVo =user.transformToVo();
        userVo.setUserToken(token);
        return new Rest<>(RestCode.SUCCEED, userVo);
    }

    @Override
    public Rest<UserVo> autoLogin(String username){
        User user = userDao.selectByUsername(username);
        UserVo userVo = user.transformToVo();
        return new Rest<>(RestCode.SUCCEED, userVo);
    }

    @Override
    public Rest<String> register(String username,String email, String password,  String verification) {
        String codeKey = String.format(VERIFICATION_REDIS_KEY, email, VerificationType.REGISTER.getCode());
        String serverVerCode = redisOperator.get(codeKey);
        if (!verification.equals(serverVerCode)) {
            return new Rest<>(RestCode.UNFOUND, "验证码错误");
        }

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(email)) {
            return new Rest<>(RestCode.DATA_FORMAT_EXCEPTION, "数据填写不完整");
        }

        User user = userDao.selectByUsername(username);
        if (user != null) {
            return new Rest<>(RestCode.EXIST, "该账号已存在");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setAvatarUrl("");
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setAvatarUrl("/ki-video/user/avatar/default-avatar.png");
        newUser.setCreateTime(new Date());
        userDao.insert(newUser);

        return new Rest<>(RestCode.SUCCEED, "注册成功");
    }

    public Rest<String> resetPassword(String email, String newPassword, String verification) {
        String codeKey = String.format(VERIFICATION_REDIS_KEY, email, VerificationType.RESETPASSWORD.getCode());
        String serverVerCode = redisOperator.get(codeKey);
        if (!verification.equals(serverVerCode)) {
            return new Rest<>(RestCode.UNFOUND, "验证码错误");
        }

        if (StringUtils.isBlank(email) || StringUtils.isBlank(newPassword)) {
            return new Rest<>(RestCode.DATA_FORMAT_EXCEPTION, "数据填写不完整");
        }

        int result = userDao.resetPassword(email, newPassword);
        if (result == 0) {
            return new Rest<>(RestCode.UNKNOWN, "密码修改不成功");
        }

        return new Rest<>(RestCode.SUCCEED, "密码修改成功");
    }

    @Override
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

    @Override
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

    @Override
    public Rest<String> verification(String email, String type) {
        String redisKey =String.format(VERIFICATION_REDIS_KEY, email, type);
        String oldCode = redisOperator.get(redisKey);
        if (!StringUtils.isBlank(oldCode)) {
            return new Rest<String>(RestCode.EXIST, "验证码仍在有效期内");
        }

        String verificationCode = String.valueOf((int) (Math.random() * 900000) + 100000);
        mailUtil.sendVerificationMail(email, verificationCode);
        redisOperator.set(redisKey, verificationCode, 3600);

        return new Rest<String>(RestCode.SUCCEED, "");
    }

    @Override
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
