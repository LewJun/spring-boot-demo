package com.example.lewjun.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

public class LoginUser extends User {
    public LoginUser(final String username, final String password, final List<String> authorities) {
        super(username, password,
                authorities.stream().map(s -> {
                    return new GrantedAuthority() {
                        @Override
                        public String getAuthority() {
                            return s;
                        }
                    };
                }).collect(Collectors.toList())
        );
    }
}
