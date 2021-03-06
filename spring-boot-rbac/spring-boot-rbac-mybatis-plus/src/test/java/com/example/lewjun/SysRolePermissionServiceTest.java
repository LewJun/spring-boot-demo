package com.example.lewjun;

import com.example.lewjun.domain.SysRolePermission;
import com.example.lewjun.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
class SysRolePermissionServiceTest {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Test
    void testList() {
        log.info("【queryAll: {}】", sysRolePermissionService.list());
    }

    @Test
    void testSave() {
        sysRolePermissionService.save(
                new SysRolePermission()
                        .setRoleId(1)
                        .setPermissionId(1)
        );
    }
}
