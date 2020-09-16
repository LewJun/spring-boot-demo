package com.example.lewjun.web;

import com.example.lewjun.base.MyBaseController;
import com.example.lewjun.domain.SysRolePermission;
import com.example.lewjun.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/rolePermissions")
public class SysRolePermissionController extends MyBaseController<SysRolePermission, SysRolePermissionService> {

    @Autowired
    public SysRolePermissionController(final SysRolePermissionService baseService) {
        super(baseService);
    }

    @DeleteMapping("/delete")
    public boolean delete(final SysRolePermission sysRolePermission) {
        return baseService.remove(sysRolePermission);
    }
}
