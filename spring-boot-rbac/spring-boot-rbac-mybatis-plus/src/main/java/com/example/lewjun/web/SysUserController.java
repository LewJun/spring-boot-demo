package com.example.lewjun.web;

import com.example.lewjun.base.MyBaseController;
import com.example.lewjun.domain.SysUser;
import com.example.lewjun.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/users")
public class SysUserController extends MyBaseController<SysUser> {
    @Autowired
    public SysUserController(final SysUserService sysUserService) {
        super(sysUserService);
    }
}
