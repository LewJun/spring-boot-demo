package com.example.lewjun.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户登录信息表
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysUserLogin extends BaseObj {
    private long userId;
    private String password;
}
