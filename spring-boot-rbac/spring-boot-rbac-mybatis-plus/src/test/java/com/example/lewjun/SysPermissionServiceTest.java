package com.example.lewjun;

import com.example.lewjun.domain.SysPermission;
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
        log.info("【: {}】", sysPermissionService.findSubPermissionByPermissionId(1));
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

    @Test
    public void testSave() {
        sysPermissionService.save(
                new SysPermission()
                        .setName("用户管理")
                        .setUrl("/sys/user/#")
                        .setParentId(1L)
        );
    }

    @Test
    public void testUpdate() {
        sysPermissionService.updateById(
                new SysPermission()
                        .setId(13L)
                        .setName("部门管理")
                        .setUrl("/sys/user/#")
                        .setParentId(1L)
        );
    }
}
