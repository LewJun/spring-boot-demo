package com.example.lewjun.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysUser extends BaseObj {
    private long id;
    private String username;
    private String password;
}
