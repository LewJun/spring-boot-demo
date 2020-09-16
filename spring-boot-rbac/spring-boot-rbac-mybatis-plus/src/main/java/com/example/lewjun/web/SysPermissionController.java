package com.example.lewjun.web;

import com.example.lewjun.base.MyBaseController;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/permissions")
public class SysPermissionController extends MyBaseController<SysPermission, SysPermissionService> {

    @Autowired
    public SysPermissionController(final SysPermissionService baseService) {
        super(baseService);
    }
}
