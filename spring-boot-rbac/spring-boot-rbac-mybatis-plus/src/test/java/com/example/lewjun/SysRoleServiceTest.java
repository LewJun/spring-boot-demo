package com.example.lewjun;

import com.example.lewjun.domain.SysRole;
import com.example.lewjun.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class SysRoleServiceTest {
    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void testList() {
        log.info("【queryAll: {}】", sysRoleService.list());
    }

    @Test
    public void testFindByUserId() {
        log.info("【findByUserId: {}】", sysRoleService.findByUserId(1L));
    }

    @Test
    public void removeById() {
        sysRoleService.removeById(1);
    }

    @Test
    public void testSave() {
        sysRoleService.save(
                new SysRole().setName("admin")
        );
    }


    @Test
    public void testUpdate() {
        sysRoleService.updateById(
                new SysRole().setId(2L).setName("admin")
        );
    }
}
