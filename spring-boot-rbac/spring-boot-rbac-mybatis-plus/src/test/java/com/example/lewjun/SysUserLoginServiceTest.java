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
public class SysUserLoginServiceTest {

    @Autowired
    public SysUserService sysUserService;

    @Autowired
    public SysUserLoginService sysUserLoginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testLogin() {
        final String username = "xxx";
        final String password = "admin";
        if (!sysUserService.existsByUsername(username)) {
            throw new RuntimeException("用户名或密码错误");
        }

        final long userId = sysUserService.findUserIdByUsername(username);

        final boolean isLogin = sysUserLoginService.login(new SysUserLogin().setUserId(userId).setPassword(password));
        log.info("【isLogin: {}】", isLogin);
    }

    @Test
    public void testPasswordEncode() {
        final String rawPassword = "normal";
        final String passwordEncode = passwordEncoder.encode(rawPassword);
        log.info("【encode: {}】", passwordEncode);

        log.info("【match: {}】", passwordEncoder.matches(rawPassword, passwordEncode));
    }
}
