package com.example.lewjun.repository;

import com.example.lewjun.model.LoginUser;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginUserRepository {
    private final Map<String, LoginUser> loginUserMap = new HashMap<>(2);

    public LoginUserRepository() {
        loginUserMap.put("admin", new LoginUser("admin", "admin", Arrays.asList("ROLE_ADMIN")));
        loginUserMap.put("normal", new LoginUser("normal", "normal", Arrays.asList("ROLE_NORMAL")));
    }

    public LoginUser findByUsername(final String username) {
        return loginUserMap.get(username);
    }
}
