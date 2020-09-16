package com.example.lewjun.config;

import com.example.lewjun.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 认证提供者
 *
 * @author huiye
 */
@Component
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    public MyDaoAuthenticationProvider(final LoginUserService loginUserService, final PasswordEncoder passwordEncoder) {
        this.setPasswordEncoder(passwordEncoder);
        this.setUserDetailsService(loginUserService);
    }
}
