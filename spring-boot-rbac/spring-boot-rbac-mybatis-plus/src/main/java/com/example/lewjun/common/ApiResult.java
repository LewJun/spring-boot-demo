package com.example.lewjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

// 若为null，则不要返回给前端
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static ApiResult ok() {
        return fail(EnumApiResultStatus.OK);
    }

    public static ApiResult ok(final Object data) {
        return of(EnumApiResultStatus.OK, data);
    }

    public static ApiResult fail() {
        return fail(EnumApiResultStatus.FAIL);
    }

    public static ApiResult fail(final Object data) {
        return of(EnumApiResultStatus.FAIL, data);
    }

    public static ApiResult fail(final EnumApiResultStatus status) {
        return of(status, null);
    }

    public static ApiResult of(final EnumApiResultStatus status, final Object data) {
        return new ApiResult(status.code, status.msg, data);
    }

    public static ApiResult fail(final String msg) {
        return new ApiResult(EnumApiResultStatus.FAIL.code, msg, null);
    }
}
