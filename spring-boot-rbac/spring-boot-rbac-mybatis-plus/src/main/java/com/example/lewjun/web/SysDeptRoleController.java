package com.example.lewjun.web;

import com.example.lewjun.base.MyBaseController;
import com.example.lewjun.domain.SysDeptRole;
import com.example.lewjun.service.SysDeptRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/deptRoles")
public class SysDeptRoleController extends MyBaseController<SysDeptRole, SysDeptRoleService> {

    @Autowired
    public SysDeptRoleController(final SysDeptRoleService baseService) {
        super(baseService);
    }
}
