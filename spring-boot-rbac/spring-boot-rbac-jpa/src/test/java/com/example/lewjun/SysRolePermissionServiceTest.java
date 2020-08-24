package com.example.lewjun;

import com.example.lewjun.domain.SysRolePermission;
import com.example.lewjun.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SysRolePermissionServiceTest {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Test
    public void testSave() {
        log.info("【save: {}】", sysRolePermissionService.save(new SysRolePermission(1L, 1L)));
    }

    @Test
    public void testDelete() {
        sysRolePermissionService.delete(new SysRolePermission(2L, 3L));
    }
}
