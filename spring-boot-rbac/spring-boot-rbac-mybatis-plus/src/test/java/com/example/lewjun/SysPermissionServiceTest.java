package com.example.lewjun;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.service.SysPermissionService;
import com.example.lewjun.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
class SysPermissionServiceTest {
    @Autowired
    private SysPermissionService sysPermissionService;

    @Test
    void testList() {
        log.info("【queryAll: {}】", sysPermissionService.list());
    }

    @Test
    void findByRoleId() {
        log.info("【: {}】", JsonUtils.object2String(sysPermissionService.findByRoleId(new MyPageInfo<>(1, 2), 1)));
    }

    @Test
    void findSubPermissionById() {
        log.info("【: {}】", JsonUtils.object2String(sysPermissionService.findSubPermissionByPermissionId(new MyPageInfo<>(1, 2), 1)));
    }

    @Test
    void findByRoleIdWithSubPermission() {
        log.info("【: {}】", JsonUtils.object2String(sysPermissionService.findByRoleIdWithSubPermission(new MyPageInfo<>(1, 2), 1)));
    }

    @Test
    void findByIdWithSubSysPermission() {
        log.info("【: {}】", JsonUtils.object2String(sysPermissionService.findByIdWithSubSysPermission(new MyPageInfo<>(1, 2), 1)));
    }

    @Test
    void removeById() {
        sysPermissionService.removeById(4);
    }

    @Test
    void testSave() {
        sysPermissionService.save(
                new SysPermission()
                        .setName("用户管理")
                        .setUrl("/sys/user/#")
                        .setParentId(1)
        );
    }

    @Test
    void testUpdate() {
        sysPermissionService.updateById(
                new SysPermission()
                        .setId(13)
                        .setName("部门管理")
                        .setUrl("/sys/user/#")
                        .setParentId(1)
        );
    }
}
