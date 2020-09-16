package com.example.lewjun.web;

import com.example.lewjun.base.MyBaseController;
import com.example.lewjun.domain.SysUserRole;
import com.example.lewjun.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/userRoles")
public class SysUserRoleController extends MyBaseController<SysUserRole, SysUserRoleService> {

    @Autowired
    public SysUserRoleController(final SysUserRoleService baseService) {
        super(baseService);
    }

}
