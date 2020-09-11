package com.example.lewjun.repository;

import com.example.lewjun.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LoginUserRepository {
    private final List<LoginUser> loginUsers = new ArrayList<>(3);

    @Autowired
    public LoginUserRepository(final PasswordEncoder passwordEncoder) {
        loginUsers.addAll(Arrays.asList(
                new LoginUser("admin", passwordEncoder.encode("admin"),
                        Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
                ),
                new LoginUser("normal", passwordEncoder.encode("normal"),
                        Collections.singletonList(new SimpleGrantedAuthority("NORMAL"))
                ),
                new LoginUser("user", passwordEncoder.encode("user"),
                        Arrays.asList(new SimpleGrantedAuthority("NORMAL"), new SimpleGrantedAuthority("USER"))
                )
        ));
    }

    public LoginUser findByUsername(final String username) {
        return loginUsers.stream()
                .filter(loginUser -> Objects.equals(username, loginUser.getUsername()))
                .findFirst()
                .orElseThrow(() -> new BadCredentialsException("用户不存在"));
    }
}
