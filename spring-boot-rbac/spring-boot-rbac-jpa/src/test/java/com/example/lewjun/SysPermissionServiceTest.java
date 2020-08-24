package com.example.lewjun;

import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SysPermissionServiceTest {
    @Autowired
    SysPermissionService sysPermissionService;

    @Test
    public void testSave() {

        log.info("【save: {}】", sysPermissionService.save(
                new SysPermission(0L, "permitAll", "/permitAll", "所有人都可以访问", 0L)
        ));

        log.info("【save: {}】", sysPermissionService.queryAll());
    }

    @Test
    public void testDelete() {
        sysPermissionService.deleteById(2L);
    }
}
