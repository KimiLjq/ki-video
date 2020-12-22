package com.stu.video.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/22 14:43
 * @Version: 1.0
 * @Description: 请求拦截器
 */
public class ApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        return true;
    }
}
