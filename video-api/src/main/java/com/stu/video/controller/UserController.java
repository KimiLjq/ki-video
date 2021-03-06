package com.stu.video.controller;

import com.stu.video.aspect.OutputException;
import com.stu.video.rest.Rest;
import com.stu.video.vo.UserVo;
import com.video.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/19 15:25
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/login")
    @ResponseBody
    public Rest<UserVo> login(String username, String password) {
        return this.userServiceImpl.login(username, password);
    }

    @RequestMapping("/register")
    @ResponseBody
    public Rest<UserVo> register(String username, String email, String password, String verification) {
      return this.userServiceImpl.register(username, email, password, verification);
    }

    @RequestMapping("/verificationCode")
    @ResponseBody
    public Rest<String> verificationCode(String email, String type) {
        return this.userServiceImpl.verification(email, type);
    }

    @RequestMapping("/queryUserByUsername")
    @ResponseBody
    public Rest<UserVo> queryUserByUsername(String username) throws OutputException {
        return this.userServiceImpl.queryUserByUsername(username);
    }

    @RequestMapping("/queryUserByEmail")
    @ResponseBody
    public Rest<UserVo> queryUserByEmail(String email) {
        return this.userServiceImpl.queryUserByEmail(email);
    }
}
