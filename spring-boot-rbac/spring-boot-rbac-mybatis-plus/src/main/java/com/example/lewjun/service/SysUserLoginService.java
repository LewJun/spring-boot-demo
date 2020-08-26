package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysUserLogin;

public interface SysUserLoginService extends MyIService<SysUserLogin> {

    /**
     * 登录
     *
     * @param sysUserLogin 用户信息
     * @return true or false
     */
    boolean login(SysUserLogin sysUserLogin);

    /**
     * 修改密码
     *
     * @param sysUserLogin 旧用户登录信息
     * @param newPassword  新密码
     * @return true or false
     */
    boolean changePassword(SysUserLogin sysUserLogin, String newPassword);
}
