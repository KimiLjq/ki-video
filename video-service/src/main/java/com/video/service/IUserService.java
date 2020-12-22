package com.video.service;

import com.stu.video.aspect.OutputException;
import com.stu.video.mapper.UserDao;
import com.stu.video.rest.Rest;
import com.stu.video.vo.UserVo;

public interface IUserService {
    /**
     * 用户登录
     * @param username 用户名
     *        password 密码
     */
    Rest<UserVo> login(String username, String password) throws OutputException;

    /**
     * 保存用户(用户注册)
     * @param user
     */
    void saveUser(UserDao user);

    /**
     * 查询用户是否存在
     * @param username 用户名
     * @return
     */
    Rest<UserVo> queryUserByUsername(String username);

    /**
     * 查询用户是否存在
     * @param email 邮箱
     * @return
     */
    Rest<UserVo> queryUserByEmail(String email);

    /**
     * 获取验证码
     */

    Rest<String> verification(String email);

}
