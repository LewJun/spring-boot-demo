package com.example.lewjun.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
public class SysPermissionWithSubSysPermission extends SysPermission {
    private List<SysPermission> subSysPermissions;
}
