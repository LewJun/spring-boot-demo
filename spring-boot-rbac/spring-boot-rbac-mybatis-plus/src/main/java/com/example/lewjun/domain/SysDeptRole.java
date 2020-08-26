package com.example.lewjun.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysDeptRole extends BaseObj {
    private long deptId;
    private long roleId;
}
