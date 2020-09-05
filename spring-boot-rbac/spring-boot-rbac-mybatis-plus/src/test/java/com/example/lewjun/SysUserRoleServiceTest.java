package com.example.lewjun;

import com.example.lewjun.domain.SysUserRole;
import com.example.lewjun.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
class SysUserRoleServiceTest {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Test
    void testList() {
        log.info("【queryAll: {}】", sysUserRoleService.list());
    }

    @Test
    void testSave() {
        sysUserRoleService.save(
                new SysUserRole()
                        .setUserId(1L)
                        .setRoleId(1L)
        );
    }

    @Test
    void testDelete() {
        sysUserRoleService.remove(
                new SysUserRole()
                        .setUserId(1L)
                        .setRoleId(5L)
        );
    }
}
