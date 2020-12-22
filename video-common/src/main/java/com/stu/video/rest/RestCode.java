package com.stu.video.rest;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RestCode {
    SUCCEED(200, "请求成功"),
    UNKNOWN(500, "未知错误"),
    DATA_FORMAT_EXCEPTION(511, "数据格式不符合规范！"),
    UNFOUND(512, "不存在相关信息");

    private int code;
    private String defaultMsg;

    RestCode(int code, String defaultMsg) {
        this.code = code;
        this.defaultMsg = defaultMsg;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }
}
