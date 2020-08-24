package com.example.lewjun;

import com.example.lewjun.domain.SysUserRole;
import com.example.lewjun.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SysUserRoleServiceTest {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Test
    public void testDelete() {
        sysUserRoleService.delete(new SysUserRole(1L, 1L));
        sysUserRoleService.delete(new SysUserRole(2L, 3L));

        log.info("【queryAll: {}】", sysUserRoleService.queryAll());
    }

    @Test
    public void testSave() {
        log.info("【save: {}】", sysUserRoleService.save(new SysUserRole(3L, 2L)));
    }

    @Test
    public void testDeleteById() {
        sysUserRoleService.deleteById(new SysUserRole(1L, 1L));
        sysUserRoleService.deleteById(new SysUserRole(2L, 3L));

        log.info("【queryAll: {}】", sysUserRoleService.queryAll());
    }
}
