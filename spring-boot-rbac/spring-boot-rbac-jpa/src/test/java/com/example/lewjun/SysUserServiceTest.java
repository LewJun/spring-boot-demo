package com.example.lewjun;

import com.example.lewjun.domain.SysUser;
import com.example.lewjun.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SysUserServiceTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testSave() {
        log.info("【add: {}】", sysUserService.save(
                new SysUser()
                        .setUsername("user")
                        .setPassword("user")
        ));
    }

    @Test
    public void testUpdate() {
        log.info("【update: {}】", sysUserService.update(
                new SysUser(1, "admin", "ADMIN")
        ));
    }

    @Test
    public void testDelete() {
        sysUserService.deleteById(2L);
    }

    @Test
    public void testQueryAll() {
        log.info("【queryAll: {}】", sysUserService.queryAll());
    }

    @Test
    public void testExistsByUsername() {
        log.info("【existsByUsername: {}】", sysUserService.existsByUsername("admin"));
    }
}
