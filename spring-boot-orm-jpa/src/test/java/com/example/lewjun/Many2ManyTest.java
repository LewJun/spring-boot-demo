package com.example.lewjun;

import com.example.lewjun.domain.many2many.SysRole;
import com.example.lewjun.domain.many2many.SysUser;
import com.example.lewjun.repositories.SysRoleRepository;
import com.example.lewjun.repositories.SysUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class Many2ManyTest {
    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    SysRoleRepository sysRoleRepository;

    @Test
    void testMany2Many() {
        sysUserRepository.save(new SysUser()
                .setUname("zs")
                .setSysRoles(Arrays.asList(
                        new SysRole().setUname("role1"),
                        new SysRole().setUname("role2"),
                        new SysRole().setUname("role3")
                ))
        );
        final List<SysUser> sysUserList = sysUserRepository.findAll();
        log.info("【sysUsers:{}】", sysUserList);

        final List<SysRole> sysRoles = sysRoleRepository.findAll();
        log.info("【sysRoles:{}】", sysRoles);

/*
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: insert into sys_user (uname, id) values (?, ?)
2021-01-25 15:34:44.339 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [zs]
2021-01-25 15:34:44.339 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [1]
Hibernate: insert into sys_role (uname, id) values (?, ?)
2021-01-25 15:34:44.339 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [role1]
2021-01-25 15:34:44.339 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [2]
Hibernate: insert into sys_role (uname, id) values (?, ?)
2021-01-25 15:34:44.339 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [role2]
2021-01-25 15:34:44.339 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [3]
Hibernate: insert into sys_role (uname, id) values (?, ?)
2021-01-25 15:34:44.339 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [role3]
2021-01-25 15:34:44.339 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [4]
Hibernate: insert into sys_user_sys_roles (sys_users_id, sys_roles_id) values (?, ?)
2021-01-25 15:34:44.355 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-25 15:34:44.355 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [2]
Hibernate: insert into sys_user_sys_roles (sys_users_id, sys_roles_id) values (?, ?)
2021-01-25 15:34:44.355 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-25 15:34:44.355 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [3]
Hibernate: insert into sys_user_sys_roles (sys_users_id, sys_roles_id) values (?, ?)
2021-01-25 15:34:44.355 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-25 15:34:44.355 TRACE 6828 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [4]
Hibernate: select sysuser0_.id as id1_11_, sysuser0_.uname as uname2_11_ from sys_user sysuser0_
2021-01-25 15:34:44.386  INFO 6828 --- [           main] com.example.lewjun.Many2ManyTest         : 【sysUsers:[com.example.lewjun.domain.many2many.SysUser@79957f11]】
Hibernate: select sysrole0_.id as id1_10_, sysrole0_.uname as uname2_10_ from sys_role sysrole0_
2021-01-25 15:34:44.402  INFO 6828 --- [           main] com.example.lewjun.Many2ManyTest         : 【sysRoles:[com.example.lewjun.domain.many2many.SysRole@4fa86cb8, com.example.lewjun.domain.many2many.SysRole@54c60202, com.example.lewjun.domain.many2many.SysRole@7889b4b9]】
*/
    }

}
