package com.stu.video.controller;

import com.stu.video.entity.UserFans;
import com.stu.video.rest.Rest;
import com.video.service.impl.UserFansImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/18 9:53
 * @Version: 1.0
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("ki-video/userFans")
public class UserFansController {
    @Autowired
    private UserFansImpl userFans;

    @RequestMapping(value = "/queryRelationship", method = RequestMethod.POST)
    public Rest<UserFans> queryRelationship(String username, String fansUsername) {
        return userFans.queryRelationshipByUsername(username, fansUsername);
    }

    @RequestMapping(value = "/addRelationship", method = RequestMethod.POST)
    public Rest<UserFans> addRelationship(String username, String fansUsername) {
        return userFans.addRelationshipByUsername(username, fansUsername);
    }

    @RequestMapping(value = "/cancelRelationship", method = RequestMethod.POST)
    public Rest<String> cancelRelationship(String username, String fansUsername) {
        return userFans.cancelRelationshipByUsername(username, fansUsername);
    }

}
