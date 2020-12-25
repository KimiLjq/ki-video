package com.stu.video.util;

import com.stu.video.jjwt.JwtConstants;
import com.stu.video.jjwt.JwtInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/22 20:31
 * @Version: 1.0
 * @Description: 生成token的工具类
 */
public class JwtTokenUtils {
    private static Key getKeyInstance(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary("mall-user");
        return new SecretKeySpec(bytes,signatureAlgorithm.getJcaName());
    }

    /**
     * 生成token的方法
     * @param jwtInfo
     * @param expire
     * @return
     */
    public static String generatorToken(JwtInfo jwtInfo, int expire){
        return Jwts.builder().claim(JwtConstants.JWT_KEY_USER_ID,jwtInfo.getUsername())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.HS256,getKeyInstance()).compact();
    }

    /**
     * 根据token获取token中的信息
     * @param token
     * @return
     */
    public static JwtInfo getTokenInfo(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return new JwtInfo(claims.get(JwtConstants.JWT_KEY_USER_ID).toString());
    }
}
