package com.video.service.impl;

import com.stu.video.aspect.OutputException;
import com.stu.video.entity.User;
import com.stu.video.mapper.UserDao;
import com.stu.video.rest.Rest;
import com.stu.video.rest.RestCode;
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
    @Autowired
    private UserDao userDao;

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


        UserVo userVo =user.transformToUserVo();
        return new Rest<>(RestCode.SUCCEED, userVo);
    }

    @Override
    public void saveUser(UserDao user) {

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
    public Rest<String> verification(String email) {

        return null;
    }
}
