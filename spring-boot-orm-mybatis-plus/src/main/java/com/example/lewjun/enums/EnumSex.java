package com.example.lewjun.enums;

public enum EnumSex {
    UNKNOWN(-1, "未知"),
    MALE(0, "女"),
    FEMALE(1, "男");

    public final int code;

    public final String desc;

    EnumSex(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }
}
