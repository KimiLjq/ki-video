package com.video.service.impl;

import com.stu.video.aspect.OutputException;
import com.stu.video.entity.User;
import com.stu.video.enums.VerificationType;
import com.stu.video.jjwt.JwtInfo;
import com.stu.video.jjwt.JwtTokenService;
import com.stu.video.mapper.UserDao;
import com.stu.video.redis.RedisOperator;
import com.stu.video.rest.Rest;
import com.stu.video.enums.RestCode;
import com.stu.video.util.MailUtil;
import com.stu.video.vo.UserVo;
import com.video.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/19 15:28
 * @Version: 1.0
 * @Description:
 */

@Service
public class UserServiceImpl implements IUserService {
    public static final String VERIFICATION_REDIS_KEY = "verify:%s type:%s";

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private RedisOperator redisOperator;

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
        jwtTokenService.generatorToken(jwtInfo);

        UserVo userVo =user.transformToUserVo();
        return new Rest<>(RestCode.SUCCEED, userVo);
    }

    @Override
    public Rest<UserVo> register(String username, String password, String email, String verification) throws OutputException {
        String codeKey = String.format(VERIFICATION_REDIS_KEY, email, VerificationType.REGISTER);
        String serverVerCode = redisOperator.get(codeKey);
        if (!verification.equals(serverVerCode)) {
            throw new OutputException(RestCode.UNFOUND, "验证码错误");
        }

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(email)) {
            throw new OutputException(RestCode.DATA_FORMAT_EXCEPTION, "数据填写不完整");
        }

        User user = userDao.selectByUsername(username);
        if (user != null) {
            throw new OutputException(RestCode.EXIST, "该账号已存在");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setAvatarUrl("");
        newUser.setPassword(password);
        newUser.setEmail(email);
        userDao.insert(newUser);
        UserVo userVo = user.transformToUserVo();

        return new Rest<>(RestCode.SUCCEED, userVo);
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
        UserVo userVo = user.transformToUserVo();

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
        UserVo userVo = user.transformToUserVo();

        return new Rest<>(RestCode.SUCCEED, userVo);
    }

    @Override
    public Rest<String> verification(String email, String type) {
        String redisKey =String.format(VERIFICATION_REDIS_KEY, email, type);
        String oldCode = redisOperator.get(redisKey);
        if (StringUtils.isBlank(oldCode)) {
            return new Rest<String>(RestCode.SUCCEED, "验证码仍在有效期内");
        }

        String verificationCode = String.valueOf((int) (Math.random() * 900000) + 100000);
        MailUtil mailUtil = new MailUtil();
        mailUtil.sendVerificationMail(email, verificationCode);
        redisOperator.set(redisKey, verificationCode, 600);

        return new Rest<String>(RestCode.SUCCEED, "");
    }
}
