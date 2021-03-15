package com.video.service.impl;

import com.stu.video.aspect.OutputException;
import com.stu.video.entity.User;
import com.stu.video.enums.RestCode;
import com.stu.video.enums.VerificationType;
import com.stu.video.jjwt.JwtInfo;
import com.stu.video.jjwt.JwtTokenService;
import com.stu.video.mapper.UserDao;
import com.stu.video.redis.RedisOperator;
import com.stu.video.rest.Rest;
import com.stu.video.util.MailUtil;
import com.stu.video.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/15 14:22
 * @Version: 1.0
 * @Description:
 */
@Component
public class LoginRegisterImpl {
    public static final String VERIFICATION_REDIS_KEY = "verify:%s type:%s";

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private MailUtil mailUtil;

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

    public Rest<UserVo> autoLogin(String username){
        User user = userDao.selectByUsername(username);
        UserVo userVo = user.transformToVo();
        return new Rest<>(RestCode.SUCCEED, userVo);
    }

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
}
