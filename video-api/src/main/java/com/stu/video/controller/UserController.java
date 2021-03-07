package com.stu.video.controller;

import com.stu.video.aspect.OutputException;
import com.stu.video.rest.Rest;
import com.stu.video.vo.UserVo;
import com.video.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/19 15:25
 * @Version: 1.0
 * @Description:
 */

@CrossOrigin
@RestController
@RequestMapping("ki-video/user")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Rest<UserVo> login(String username, String password) {
        return this.userServiceImpl.login(username, password);
    }

    @RequestMapping(value = "/autoLogin", method = RequestMethod.POST)
    @ResponseBody
    public Rest<UserVo> autoLogin(String username) {
        return this.userServiceImpl.autoLogin(username);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Rest<String> register(String username, String email, String password, String verification) {
      return this.userServiceImpl.register(username, email, password, verification);
    }

    @RequestMapping(value = "/verificationCode", method = RequestMethod.POST)
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

    @RequestMapping("/modifyAvatar")
    @ResponseBody
    public Rest<String> modifyAvatar(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException{

        return this.userServiceImpl.modifyAvatar(file, request);
    }
}
