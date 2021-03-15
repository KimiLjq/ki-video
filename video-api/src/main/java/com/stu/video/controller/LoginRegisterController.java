package com.stu.video.controller;

import com.stu.video.rest.Rest;
import com.stu.video.vo.UserVo;
import com.video.service.impl.LoginRegisterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/15 14:22
 * @Version: 1.0
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("ki-video/loginRegister")
public class LoginRegisterController {
    @Autowired
    private LoginRegisterImpl loginRegister;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Rest<UserVo> login(String username, String password) {
        return this.loginRegister.login(username, password);
    }

    @RequestMapping(value = "/autoLogin", method = RequestMethod.POST)
    @ResponseBody
    public Rest<UserVo> autoLogin(String username) {
        return this.loginRegister.autoLogin(username);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Rest<String> register(String username, String email, String password, String verification) {
        return this.loginRegister.register(username, email, password, verification);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Rest<String> resetPassword(String email, String newPassword, String verification) {
        return this.loginRegister.resetPassword(email, newPassword, verification);
    }

    @RequestMapping(value = "/verificationCode", method = RequestMethod.POST)
    @ResponseBody
    public Rest<String> verificationCode(String email, String type) {
        return this.loginRegister.verification(email, type);
    }
}
