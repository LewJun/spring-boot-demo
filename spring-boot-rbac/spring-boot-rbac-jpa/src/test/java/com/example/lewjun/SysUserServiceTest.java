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
    public void testAdd() {
        log.info("【add: {}】", sysUserService.save(
                new SysUser()
                        .setUsername("admin")
                        .setPassword("admin")
        ));
    }
}
