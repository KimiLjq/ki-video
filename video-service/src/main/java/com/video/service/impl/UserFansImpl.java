package com.video.service.impl;

import com.stu.video.aspect.OutputException;
import com.stu.video.entity.UserFans;
import com.stu.video.enums.RestCode;
import com.stu.video.mapper.UserFansDao;
import com.stu.video.rest.Rest;
import com.stu.video.vo.FollowUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/18 9:39
 * @Version: 1.0
 * @Description:
 */
@Component
public class UserFansImpl {
    @Autowired
    private UserFansDao userFansDao;

    public Rest<UserFans> queryRelationshipByUsername(String username, String fansUsername) throws OutputException {
        if (StringUtils.isBlank(username) && StringUtils.isBlank(fansUsername)) {
            throw new OutputException(RestCode.DATA_FORMAT_EXCEPTION, "传入数据为空");
        }

        UserFans userFans = userFansDao.queryRelationShipByUsername(username, fansUsername);

        if (userFans != null) {
            return new Rest<>(RestCode.SUCCEED, userFans);
        }
        else {
            throw new OutputException(RestCode.UNFOUND, "未找到相关信息");
        }
    }

    public Rest<UserFans> addRelationshipByUsername(String username, String fansUsername) throws OutputException {
        if (StringUtils.isBlank(username) && StringUtils.isBlank(fansUsername)) {
            throw new OutputException(RestCode.DATA_FORMAT_EXCEPTION, "传入数据为空");
        }

        UserFans userFans = new UserFans();
        userFans.setUsername(username);
        userFans.setFansUsername(fansUsername);

        if (userFansDao.insert(userFans) > 0) {
            return new Rest<>(RestCode.SUCCEED, "关注成功", userFans);
        }
        else {
            throw new OutputException(RestCode.UNKNOWN, "关注失败");
        }
    }

    public Rest<String> cancelRelationshipByUsername(String username, String fansUsername) throws OutputException {
        if (StringUtils.isBlank(username) && StringUtils.isBlank(fansUsername)) {
            throw new OutputException(RestCode.DATA_FORMAT_EXCEPTION, "传入数据为空");
        }

        if (userFansDao.deleteByUsername(username, fansUsername) > 0) {
            return new Rest<>(RestCode.SUCCEED, "取关成功");
        }
        else {
            throw new OutputException(RestCode.UNKNOWN, "取关失败");
        }
    }

    public Rest<List<FollowUserVo>> queryMyFollowUserList(String username, String loginUsername) {
        List<FollowUserVo> followUserVos = userFansDao.queryFollowUserByUsername(username);
        if (!StringUtils.isBlank(loginUsername)) {
            if (username.equals(loginUsername)) {
                for(FollowUserVo followUserVo:followUserVos) {
                    followUserVo.setFollow(true);
                }
            }
            else {
                for(FollowUserVo followUserVo:followUserVos) {
                    if (userFansDao.queryRelationShipByUsername(followUserVo.getUsername(), loginUsername) != null ) {
                        followUserVo.setFollow(true);
                    }
                }
            }
        }

        return new Rest<>(RestCode.SUCCEED, followUserVos);
    }

    public Rest<List<FollowUserVo>> queryMyFansList(String username, String loginUsername) {
        List<FollowUserVo> fansList = userFansDao.queryFansByUsername(username);

        if (!StringUtils.isBlank(loginUsername)) {
            for (FollowUserVo fans:fansList) {
                if (userFansDao.queryRelationShipByUsername(fans.getUsername(), loginUsername) != null) {
                    fans.setFollow(true);
                }
            }
        }

        return new Rest<>(RestCode.SUCCEED, fansList);
    }
}
