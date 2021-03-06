package com.example.lewjun;

import com.example.lewjun.domain.SysDeptRole;
import com.example.lewjun.service.SysDeptRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SysDeptRoleServiceTest {

    @Autowired
    SysDeptRoleService sysDeptRoleService;

    @Test
    void save() {
        final SysDeptRole sysDeptRole = new SysDeptRole();
        sysDeptRole.setDeptId(1);
        sysDeptRole.setRoleId(2);
        sysDeptRoleService.save(sysDeptRole);
    }

    @Test
    void remove() {
        final SysDeptRole sysDeptRole = new SysDeptRole();
        sysDeptRole.setDeptId(1);
        sysDeptRole.setRoleId(2);
    }
}
