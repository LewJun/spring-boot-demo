package com.example.lewjun.repository;

import com.example.lewjun.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginUserRepository {
    private final Map<String, LoginUser> loginUserMap = new HashMap<>(2);

    @Autowired
    public LoginUserRepository(final PasswordEncoder passwordEncoder) {
        loginUserMap.put("admin", new LoginUser("admin", passwordEncoder.encode("admin"), Arrays.asList("ROLE_ADMIN")));
        loginUserMap.put("normal", new LoginUser("normal", passwordEncoder.encode("normal"), Arrays.asList("ROLE_NORMAL")));
    }

    public LoginUser findByUsername(final String username) {
        return loginUserMap.get(username);
    }
}
