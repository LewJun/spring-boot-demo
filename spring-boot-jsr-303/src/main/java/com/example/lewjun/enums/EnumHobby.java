package com.example.lewjun.enums;

public enum EnumHobby {
    UNKNOWN(-1, "未知"),
    CODING(0, "编码"),
    READING(1, "阅读"),
    GAME(2, "游戏");

    public final int code;

    public final String desc;

    EnumHobby(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }
}
