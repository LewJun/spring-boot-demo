package com.example.lewjun.common;

import lombok.Data;

@Data
public class ApiResult {
    private Object data;
    private int code;
    private String msg;

    private ApiResult() {
    }

    private ApiResult(final Object data) {
        this();
        this.data = data;
    }

    private ApiResult(final int code, final String msg, final Object data) {
        this(data);
        this.code = code;
        this.msg = msg;
    }

    public static ApiResult ofOk() {
        return of(EnumApiResultStatus.OK);
    }

    public static ApiResult ofOk(final Object data) {
        return of(EnumApiResultStatus.OK, data);
    }

    public static ApiResult ofFail() {
        return of(EnumApiResultStatus.FAIL);
    }

    public static ApiResult ofFail(final Object data) {
        return of(EnumApiResultStatus.FAIL, data);
    }

    public static ApiResult of(final EnumApiResultStatus status) {
        return of(status, null);
    }

    public static ApiResult of(final EnumApiResultStatus status, final Object data) {
        return new ApiResult(status.code, status.msg, data);
    }
}
