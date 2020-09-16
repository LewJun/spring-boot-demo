package com.example.lewjun.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
import com.example.lewjun.domain.SysUserLogin;
import com.example.lewjun.mapper.SysUserLoginMapper;
import com.example.lewjun.service.SysUserLoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class SysUserLoginServiceImpl extends MyServiceImpl<SysUserLoginMapper, SysUserLogin> implements SysUserLoginService {

//    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    SysUserLoginServiceImpl(final PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public boolean login(final SysUserLogin sysUserLogin) {
//        return passwordMatches(sysUserLogin);
        return true;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean changePassword(final SysUserLogin sysUserLogin, final String newPassword) {
        if (sysUserLogin == null || StringUtils.isEmpty(newPassword)) {
            return false;
        }

//        if (!passwordMatches(sysUserLogin)) {
//            throw new RuntimeException("用户名和密码不匹配");
//        }

        setPassword(sysUserLogin, newPassword);

        return super.updateById(sysUserLogin);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean resetPassword(final SysUserLogin sysUserLogin) {
        setPassword(sysUserLogin, "123456");
        if (!baseMapper.existsByUserId(sysUserLogin.getUserId()).isPresent()) {
            return save(sysUserLogin);
        }
        return SqlHelper.retBool(baseMapper.resetPassword(sysUserLogin));
    }

    private void setPassword(final SysUserLogin sysUserLogin, final String password) {
//        sysUserLogin.setPassword(passwordEncoder.encode(password));
    }

    private String findPasswordByUserId(final long userId) {
        return baseMapper.findPasswordByUserId(userId).orElseThrow(() -> BussException.of(EnumApiResultStatus.SYS_USER_USERNAME_OR_PASSWORD_WRONG_ERR));
    }

//    private boolean passwordMatches(final SysUserLogin sysUserLogin) {
//        final String password = findPasswordByUserId(sysUserLogin.getUserId());
//        return passwordEncoder.matches(sysUserLogin.getPassword(), password);
//    }
}
