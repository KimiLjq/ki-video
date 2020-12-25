package com.stu.video.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VerificationType {
    REGISTER(1);

    private int code;

    VerificationType(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
}
