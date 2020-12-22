package com.stu.video.jjwt;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/22 20:30
 * @Version: 1.0
 * @Description:
 */
public class JwtInfo {
    private String uid;

    public JwtInfo(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
