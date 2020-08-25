package com.example.lewjun.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysRolePermission extends BaseObj {
    private long roleId;
    private long permissionId;
}
