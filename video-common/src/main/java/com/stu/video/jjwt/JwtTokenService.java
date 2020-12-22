package com.stu.video.jjwt;

import com.stu.video.util.JwtTokenUtils;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/22 21:49
 * @Version: 1.0
 * @Description:
 */
public class JwtTokenService {
    /**
     * token过期时间
     */
    private int expire = 60*60*24;

    public String generatorToken(JwtInfo jwtInfo){
        return JwtTokenUtils.generatorToken(jwtInfo,expire);
    }

    public JwtInfo stringInfoFromToken(String token){
        return JwtTokenUtils.getTokenInfo(token);
    }
}
