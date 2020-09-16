package com.example.lewjun.common;

public enum EnumApiResultStatus {
    OK(0, "请求成功"),
    FAIL(-1, "系统异常");

    public final int code;

    public final String msg;

    EnumApiResultStatus(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
