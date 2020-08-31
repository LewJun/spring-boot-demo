package com.example.lewjun;

import com.example.lewjun.domain.SysUser;
import com.example.lewjun.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class SysUserServiceTest {
    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testList() {
        log.info("【queryAll: {}】", sysUserService.list());
    }

    @Test
    public void testExistsByUsername() {
        log.info("【existsByUsername: {}】", sysUserService.existsByUsername("admin"));
    }

    @Test
    public void testFindByUsername() {
        log.info("【findByUsername: {}】", sysUserService.findByUsername("admin"));
    }

    @Test
    public void testSave() {
        final SysUser sysUser = new SysUser().setUsername("xxz").setEmail("xxz@qq.com");
        final boolean isSaved = sysUserService.save(sysUser);
        log.info("【isSaved: {}】", isSaved);
        log.info("sysUser: {}】", sysUser);
    }

    @Test
    public void testUpdate() {
        sysUserService.updateById(
                new SysUser()
                        .setId(13L)
                        .setNickname("nickname")
        );
    }

    @Test
    public void testRemoveById() {
        final boolean isRemoved = sysUserService.removeById(1L);
        log.info("【isRemoved: {}】", isRemoved);
    }
}
