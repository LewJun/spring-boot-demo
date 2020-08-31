package com.example.lewjun.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class SysUserLoginDetailsDO extends SysUser {
    private String password;
}
