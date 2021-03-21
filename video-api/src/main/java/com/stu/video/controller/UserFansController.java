package com.stu.video.controller;

import com.stu.video.entity.UserFans;
import com.stu.video.rest.Rest;
import com.stu.video.vo.FollowUserVo;
import com.video.service.impl.UserFansImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseBody
    public Rest<UserFans> queryRelationship(String username, String fansUsername) {
        return userFans.queryRelationshipByUsername(username, fansUsername);
    }

    @RequestMapping(value = "/addRelationship", method = RequestMethod.POST)
    @ResponseBody
    public Rest<UserFans> addRelationship(String username, String fansUsername) {
        return userFans.addRelationshipByUsername(username, fansUsername);
    }

    @RequestMapping(value = "/cancelRelationship", method = RequestMethod.POST)
    @ResponseBody
    public Rest<String> cancelRelationship(String username, String fansUsername) {
        return userFans.cancelRelationshipByUsername(username, fansUsername);
    }

    @RequestMapping(value = "/queryFollowUsers", method = RequestMethod.POST)
    @ResponseBody
    public Rest<List<FollowUserVo>> queryFollowUsers(String username, @RequestParam(required = false) String loginUsername) {
        return userFans.queryMyFollowUserList(username, loginUsername);
    }

    @RequestMapping(value = "/queryFansList", method = RequestMethod.POST)
    @ResponseBody
    public Rest<List<FollowUserVo>> queryFansList(String username, @RequestParam(required = false) String loginUsername) {
        return userFans.queryMyFansList(username, loginUsername);
    }

}
