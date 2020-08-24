package com.example.lewjun;

import com.example.lewjun.domain.SysRole;
import com.example.lewjun.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SysRoleServiceTest {
    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void testSave() {
        log.info("【save: {}】", sysRoleService.save(new SysRole(0L, "user")));
    }

    @Test
    public void testDelete() {
        sysRoleService.deleteById(2L);
    }
}
