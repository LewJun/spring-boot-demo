package com.example.lewjun;

import com.example.lewjun.domain.SysUser;
import com.example.lewjun.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class SysUserServiceTest {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testList() {
        log.info("【queryAll: {}】", sysUserService.list());
    }

    @Test
    public void testExistsByUsername() {
        log.info("【existsByUsername: {}】", sysUserService.existsByUsername("admin"));
    }

    @Test
    public void testSave() {
        final SysUser sysUser = new SysUser().setUsername("xxx").setPassword("yyy");
        final boolean isSaved = sysUserService.save(sysUser);
        log.info("【isSaved: {}】", isSaved);
        log.info("sysUser: {}】", sysUser);
    }

    @Test
    public void testRemoveById() {
        final boolean isRemoved = sysUserService.removeById(4);
        log.info("【isRemoved: {}】", isRemoved);
    }

    @Test
    public void testLogin() {
        final SysUser sysUser = new SysUser().setUsername("admin").setPassword("admin");
        final boolean isLogin = sysUserService.login(sysUser);
        log.info("【isLogin: {}】", isLogin);
    }

    @Test
    public void testPasswordEncode() {
        final String rawPassword = "user";
        final String passwordEncode = passwordEncoder.encode(rawPassword);
        log.info("【encode: {}】", passwordEncode);

        log.info("【match: {}】", passwordEncoder.matches(rawPassword, passwordEncode));
    }
}
