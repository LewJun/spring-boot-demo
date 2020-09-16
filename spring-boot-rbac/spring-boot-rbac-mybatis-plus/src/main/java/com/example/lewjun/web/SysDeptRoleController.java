package com.example.lewjun.web;

import com.example.lewjun.base.MyBaseController;
import com.example.lewjun.domain.SysDeptRole;
import com.example.lewjun.service.SysDeptRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/deptRoles")
public class SysDeptRoleController extends MyBaseController<SysDeptRole> {

    private final SysDeptRoleService sysDeptRoleService;

    @Autowired
    public SysDeptRoleController(final SysDeptRoleService sysDeptRoleService) {
        super(sysDeptRoleService);
        this.sysDeptRoleService = sysDeptRoleService;
    }

    @DeleteMapping("/delete")
    public boolean delete(final SysDeptRole sysDeptRole) {
        return sysDeptRoleService.remove(sysDeptRole);
    }
}
