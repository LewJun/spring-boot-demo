package com.example.lewjun.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class LoginUser extends User {
    public LoginUser(final String username, final String password, final List<GrantedAuthority> grantedAuthorities) {
        super(username, password, grantedAuthorities);
    }
}
