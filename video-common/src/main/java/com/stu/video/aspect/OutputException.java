package com.stu.video.aspect;

import com.stu.video.rest.RestCode;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/20 14:11
 * @Version: 1.0
 * @Description:
 */
public class OutputException extends RuntimeException{
    private RestCode code;
    private String msg;

    public OutputException(RestCode restCode) {
        this.code = restCode;
    }

    public OutputException(RestCode restCode, String msg) {
        this.code = restCode;
        this.msg = msg;
    }

    public RestCode getRestCode() {
        return this.code;
    }

    public String getMsg() {
        if (msg == null || msg.equals("")) return code.getDefaultMsg();
        return msg;
    }
}
