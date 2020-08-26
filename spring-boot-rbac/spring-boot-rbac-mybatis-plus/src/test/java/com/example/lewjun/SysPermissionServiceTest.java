package com.example.lewjun;

import com.example.lewjun.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class SysPermissionServiceTest {
    @Autowired
    private SysPermissionService sysPermissionService;

    @Test
    public void testList() {
        log.info("【queryAll: {}】", sysPermissionService.list());
    }

    @Test
    public void findByRoleId() {
        log.info("【: {}】", sysPermissionService.findByRoleId(1));
    }

    @Test
    public void findSubPermissionById() {
        log.info("【: {}】", sysPermissionService.findSubPermissionById(1));
    }

    @Test
    public void findByRoleIdWithSubPermission() {
        log.info("【1: {}】", sysPermissionService.findByRoleIdWithSubPermission(1));
    }

    @Test
    public void findByIdWithSubSysPermission() {
        log.info("【: {}】", sysPermissionService.findByIdWithSubSysPermission(1));
    }

    @Test
    public void removeById() {
        sysPermissionService.removeById(4);
    }
}
