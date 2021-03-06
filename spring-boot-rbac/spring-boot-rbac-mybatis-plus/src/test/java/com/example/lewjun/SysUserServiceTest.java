package com.example.lewjun;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.domain.SysUser;
import com.example.lewjun.service.SysUserService;
import com.example.lewjun.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
class SysUserServiceTest {
    @Autowired
    private SysUserService sysUserService;

    @Test
    void testList() {
        log.info("【queryAll: {}】", sysUserService.list());
    }

    @Test
    void testExistsByUsername() {
        log.info("【existsByUsername: {}】", sysUserService.existsByUsername("admin"));
    }

    @Test
    void testFindByUsername() {
        log.info("【findByUsername: {}】", sysUserService.findByUsername("admin"));
    }

    @Test
    void testSave() {
        final SysUser sysUser = new SysUser().setUsername("xxz").setEmail("xxz@qq.com");
        final boolean isSaved = sysUserService.save(sysUser);
        log.info("【isSaved: {}】", isSaved);
        log.info("sysUser: {}】", sysUser);
    }

    @Test
    void testUpdate() {
        sysUserService.updateById(
                new SysUser()
                        .setId(3)
                        .setNickname("admin")
        );
    }

    @Test
    void testRemoveById() {
        final boolean isRemoved = sysUserService.removeById(1);
        log.info("【isRemoved: {}】", isRemoved);
    }

    @Test
    void testPage() {
        final MyPageInfo<SysUser> sysUserPage = sysUserService.page(new MyPageInfo<>(1, 2));
        log.info("【sysUserPage: {}】", JsonUtils.object2String(sysUserPage));
    }
}
