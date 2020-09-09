package com.example.lewjun.service;

import com.example.lewjun.model.LoginUser;
import com.example.lewjun.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserService implements UserDetailsService {
    public LoginUserRepository loginUserRepository;

    // spring 推荐在构造方法上注入属性
    @Autowired
    public LoginUserService(final LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    @Override
    public LoginUser loadUserByUsername(final String username) {
        return Optional.ofNullable(loginUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }
}
