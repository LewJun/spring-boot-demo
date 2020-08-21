package com.example.lewjun.service;

import com.example.lewjun.model.LoginUser;
import com.example.lewjun.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements UserDetailsService {
    @Autowired
    public LoginUserRepository loginUserRepository;

    @Override
    public LoginUser loadUserByUsername(final String username) throws UsernameNotFoundException {
        return loginUserRepository.findByUsername(username);
    }
}
