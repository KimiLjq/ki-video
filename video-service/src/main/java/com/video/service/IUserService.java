package com.video.service;

import com.stu.video.aspect.OutputException;
import com.stu.video.mapper.UserDao;
import com.stu.video.rest.Rest;
import com.stu.video.vo.UserVo;

public interface IUserService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     */
    Rest<UserVo> login(String username, String password) throws OutputException;

    Rest<UserVo> register(String username, String password, String email, String verification);

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
     * @param email 邮箱
     * @param type 验证码的用途，由前台确定并传给后台
     */

    Rest<String> verification(String email, String type);

}
