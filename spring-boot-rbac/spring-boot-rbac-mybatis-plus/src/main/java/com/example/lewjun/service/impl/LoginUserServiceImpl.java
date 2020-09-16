package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.domain.LoginUser;
import com.example.lewjun.domain.SysRole;
import com.example.lewjun.domain.SysUserLoginDetailsDO;
import com.example.lewjun.mapper.SysRoleMapper;
import com.example.lewjun.mapper.SysUserMapper;
import com.example.lewjun.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    private final SysUserMapper sysUserMapper;

    private final SysRoleMapper sysRoleMapper;

    @Autowired
    public LoginUserServiceImpl(final SysUserMapper sysUserMapper, final SysRoleMapper sysRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<SysUserLoginDetailsDO> userLoginDetailsDOOptional = sysUserMapper.findByUsername(username);
        if (userLoginDetailsDOOptional.isPresent()) {
            final SysUserLoginDetailsDO sysUserLoginDetailsDO = userLoginDetailsDOOptional.get();
            final String uname = sysUserLoginDetailsDO.getUsername();
            final String password = sysUserLoginDetailsDO.getPassword();

            final MyPageInfo<SysRole> rolePageInfo = sysRoleMapper.findByUserId(new MyPageInfo<>(1, 999), sysUserLoginDetailsDO.getId());

            final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if (rolePageInfo.getTotal() > 0) {
                final List<SysRole> records = rolePageInfo.getRecords();
                for (final SysRole record : records) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(record.getName()));
                }
            }

            return new LoginUser(uname, password, grantedAuthorities);
        }
//        throw BussException.of(EnumApiResultStatus.CONTENT_NOT_FOUND);
        throw new UsernameNotFoundException("用户不存在");
    }
}
