package com.example.lewjun.common;

import lombok.Data;

@Data
public class ApiResult<T> {
    private final T data;
    private int code;
    private String msg = "接口响应正常";

    public ApiResult(final T data) {
        this.data = data;
    }
}
