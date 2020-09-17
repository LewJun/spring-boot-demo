package com.example.lewjun.enums;

public enum EnumMethod {
    ALL(0, ""),
    GET(1, "GET"),
    POST(2, "POST"),
    PUT(3, "PUT"),
    DELETE(4, "DELETE");

    private final String desc;
    private final int code;

    EnumMethod(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static int getCodeByDesc(final String method) {
        for (final EnumMethod value : values()) {
            if (value.desc.equals(method)) {
                return value.code;
            }
        }
        return -1;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
