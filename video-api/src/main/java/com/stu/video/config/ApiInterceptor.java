package com.stu.video.config;

import com.alibaba.fastjson.JSONObject;
import com.stu.video.jjwt.JwtInfo;
import com.stu.video.jjwt.JwtTokenService;
import com.stu.video.rest.Rest;
import com.stu.video.enums.RestCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/22 14:43
 * @Version: 1.0
 * @Description: 请求拦截器
 */
public class ApiInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String username = request.getHeader("headerUsername");
        String token = request.getHeader("headerUserToken");

        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(token)) {
            JwtInfo jwtInfo = jwtTokenService.stringInfoFromToken(token);
            if (StringUtils.isBlank(jwtInfo.getUsername())) {
                returnErrorResponse(response, new Rest<String>(RestCode.UNFOUND, "账号在其他设备登录"));

                return false;
            }
        }
        else {
            returnErrorResponse(response, new Rest<String>(RestCode.DATA_FORMAT_EXCEPTION, "请登录"));

            return false;
        }
        return true;
    }

    /**
     * 把拦截数据返回给前端
     * @param response
     * @param result
     * @throws IOException
     */
    private void returnErrorResponse(HttpServletResponse response, Rest<String> result) throws IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(((JSONObject) JSONObject.toJSON(result)).toJSONString().getBytes("utf-8"));
            out.flush();
        } finally {
            if(out!=null){
                out.close();
            }
        }
    }
}
