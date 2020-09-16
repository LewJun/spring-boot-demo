package com.example.lewjun.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class LoginUser extends User {
    private final String username;
    private final String password;
    private final List<GrantedAuthority> grantedAuthorities;

    public LoginUser(final String username, final String password, final List<GrantedAuthority> grantedAuthorities) {
        super(username, password, grantedAuthorities);

        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }
}
