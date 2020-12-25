package com.stu.video.jjwt;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/22 20:30
 * @Version: 1.0
 * @Description:
 */
public class JwtInfo {
    private String username;

    public JwtInfo(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
