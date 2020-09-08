package com.example.lewjun;

import com.example.lewjun.domain.SysUserLogin;
import com.example.lewjun.service.SysUserLoginService;
import com.example.lewjun.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
class SysUserLoginServiceTest {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserLoginService sysUserLoginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testLogin() {
        final String username = "admin";
        final String password = "admin";

        final Integer userId = sysUserService.findUserIdByUsername(username);
        if (userId == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        final boolean isLogin = sysUserLoginService.login(new SysUserLogin().setUserId(userId).setPassword(password));
        log.info("【isLogin: {}】", isLogin);
    }

    @Test
    void testPasswordEncode() {
        final String rawPassword = "normal";
        final String passwordEncode = passwordEncoder.encode(rawPassword);
        log.info("【encode: {}】", passwordEncode);

        log.info("【match: {}】", passwordEncoder.matches(rawPassword, passwordEncode));
    }
}
