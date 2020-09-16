package com.example.lewjun.web;

import com.example.lewjun.base.MyBaseController;
import com.example.lewjun.domain.SysRole;
import com.example.lewjun.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/roles")
public class SysRoleController extends MyBaseController<SysRole, SysRoleService> {

    @Autowired
    public SysRoleController(final SysRoleService baseService) {
        super(baseService);
    }
}
