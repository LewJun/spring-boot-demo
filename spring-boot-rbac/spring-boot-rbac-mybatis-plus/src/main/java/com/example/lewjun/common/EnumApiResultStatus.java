package com.example.lewjun.common;

public enum EnumApiResultStatus {
    OK(0, "请求成功"),
    FAIL(-1, "系统异常"),
    CONTENT_NOT_FOUND(0x001, "资源不存在"),
    TOKEN_INVALID(0x002, "TOKEN无效"),
    AUTHENTICATION_INVALID(0x003, "认证失败"),
    ACCESS_DENIED_ERR(0x004, "授权失败"),
    // -------------------------------------------------- //
    SYS_DEPT_NOT_EXISTS(0x100, "所选部门不存在。"),
    SYS_DEPT_REMOVE_ROOT_ERR(0x101, "不能删除根部门。"),
    SYS_DEPT_HAS_SUB_ERR(0x102, "所选部门存在下级部门。"),
    SYS_DEPT_USER_USED_ERR(0x103, "所选部门被用户使用。"),
    SYS_DEPT_PARENT_DEPT_NOT_EXISTS(0x104, "父级部门不存在。"),
    SYS_DEPT_DUPLICATE_IN_SAME_DEPT(0x105, "同一个部门下的子部门名称不能重复。"),

    // -------------------------------------------------- //
    SYS_USER_NOT_EXISTS(0x200, "所选用户不存在。"),
    SYS_USER_USERNAME_EXISTS(0x201, "用户名已存在。"),
    SYS_USER_REMOVE_ROOT_ERR(0x202, "不允许删除超级管理员。"),
    SYS_USER_USERNAME_OR_PASSWORD_WRONG_ERR(0x203, "用户名或密码错误。"),
    // -------------------------------------------------- //
    SYS_ROLE_NOT_EXISTS(0x300, "所选角色不存在。"),
    SYS_ROLE_NAME_EXISTS(0x301, "名称已存在"),
    SYS_ROLE_USER_USED_ERR(0x302, "所选角色正在被用户使用。"),
    SYS_ROLE_DEPT_USED_ERR(0x303, "所选角色正在被部门使用。"),
    // -------------------------------------------------- //
    SYS_PERMISSION_NOT_EXISTS(0x400, "所选权限不存在。"),
    SYS_PERMISSION_NAME_EXISTS(0x401, "权限名称重复"),
    SYS_PERMISSION_URL_EXISTS(0x402, "url地址请求方式重复"),
    SYS_PERMISSION_HAS_SUB_ERR(0x403, "所选权限存在子权限。"),
    SYS_PERMISSION_ROLE_USED_ERR(0x404, "所选权限正在被角色使用。"),
    // -------------------------------------------------- //
    ;

    public final int code;

    public final String msg;

    EnumApiResultStatus(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
