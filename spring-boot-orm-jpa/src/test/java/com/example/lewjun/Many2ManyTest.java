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
        sysRoleRepository.saveAll(
                Arrays.asList(
                        new SysRole().setUname("role1"),
                        new SysRole().setUname("role2"),
                        new SysRole().setUname("role3")
                )
        );

        final List<SysRole> sysRoles = sysRoleRepository.findAll();
        log.info("【sysRoles are all saved. {}】", sysRoles);

        sysUserRepository.save(new SysUser()
                .setUname("zs")
                .setSysRoles(sysRoles)
        );
        log.info("【sysUsers:{}】", sysUserRepository.findAll());

        log.info("【sysRoles:{}】", sysRoleRepository.findAll());

/*
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: insert into sys_role (uname, id) values (?, ?)
2021-01-22 17:55:51.630 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [role1]
2021-01-22 17:55:51.632 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [1]
Hibernate: insert into sys_role (uname, id) values (?, ?)
2021-01-22 17:55:51.634 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [role2]
2021-01-22 17:55:51.634 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [2]
Hibernate: insert into sys_role (uname, id) values (?, ?)
2021-01-22 17:55:51.636 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [role3]
2021-01-22 17:55:51.636 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [3]
Hibernate: select sysrole0_.id as id1_8_, sysrole0_.uname as uname2_8_ from sys_role sysrole0_
Hibernate: select sysusers0_.sys_role_id as sys_role1_9_0_, sysusers0_.sys_users_id as sys_user2_9_0_, sysuser1_.id as id1_10_1_, sysuser1_.uname as uname2_10_1_ from sys_role_sys_users sysusers0_ inner join sys_user sysuser1_ on sysusers0_.sys_users_id=sysuser1_.id where sysusers0_.sys_role_id=?
Hibernate: select sysusers0_.sys_role_id as sys_role1_9_0_, sysusers0_.sys_users_id as sys_user2_9_0_, sysuser1_.id as id1_10_1_, sysuser1_.uname as uname2_10_1_ from sys_role_sys_users sysusers0_ inner join sys_user sysuser1_ on sysusers0_.sys_users_id=sysuser1_.id where sysusers0_.sys_role_id=?
Hibernate: select sysusers0_.sys_role_id as sys_role1_9_0_, sysusers0_.sys_users_id as sys_user2_9_0_, sysuser1_.id as id1_10_1_, sysuser1_.uname as uname2_10_1_ from sys_role_sys_users sysusers0_ inner join sys_user sysuser1_ on sysusers0_.sys_users_id=sysuser1_.id where sysusers0_.sys_role_id=?
2021-01-22 17:55:51.679  INFO 8412 --- [           main] com.example.lewjun.Many2ManyTest         : 【sysRoles are all saved. [SysRole(id=1, uname=role1, sysUsers=[]), SysRole(id=2, uname=role2, sysUsers=[]), SysRole(id=3, uname=role3, sysUsers=[])]】
Hibernate: call next value for hibernate_sequence
Hibernate: insert into sys_user (uname, id) values (?, ?)
2021-01-22 17:55:51.698 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [zs]
2021-01-22 17:55:51.698 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [4]
Hibernate: insert into sys_user_sys_roles (sys_user_id, sys_roles_id) values (?, ?)
2021-01-22 17:55:51.704 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [4]
2021-01-22 17:55:51.705 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [1]
Hibernate: insert into sys_user_sys_roles (sys_user_id, sys_roles_id) values (?, ?)
2021-01-22 17:55:51.708 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [4]
2021-01-22 17:55:51.708 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [2]
Hibernate: insert into sys_user_sys_roles (sys_user_id, sys_roles_id) values (?, ?)
2021-01-22 17:55:51.710 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [4]
2021-01-22 17:55:51.711 TRACE 8412 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [3]
Hibernate: select sysuser0_.id as id1_10_, sysuser0_.uname as uname2_10_ from sys_user sysuser0_
Hibernate: select sysroles0_.sys_user_id as sys_user1_11_0_, sysroles0_.sys_roles_id as sys_role2_11_0_, sysrole1_.id as id1_8_1_, sysrole1_.uname as uname2_8_1_ from sys_user_sys_roles sysroles0_ inner join sys_role sysrole1_ on sysroles0_.sys_roles_id=sysrole1_.id where sysroles0_.sys_user_id=?
Hibernate: select sysusers0_.sys_role_id as sys_role1_9_0_, sysusers0_.sys_users_id as sys_user2_9_0_, sysuser1_.id as id1_10_1_, sysuser1_.uname as uname2_10_1_ from sys_role_sys_users sysusers0_ inner join sys_user sysuser1_ on sysusers0_.sys_users_id=sysuser1_.id where sysusers0_.sys_role_id=?
Hibernate: select sysusers0_.sys_role_id as sys_role1_9_0_, sysusers0_.sys_users_id as sys_user2_9_0_, sysuser1_.id as id1_10_1_, sysuser1_.uname as uname2_10_1_ from sys_role_sys_users sysusers0_ inner join sys_user sysuser1_ on sysusers0_.sys_users_id=sysuser1_.id where sysusers0_.sys_role_id=?
Hibernate: select sysusers0_.sys_role_id as sys_role1_9_0_, sysusers0_.sys_users_id as sys_user2_9_0_, sysuser1_.id as id1_10_1_, sysuser1_.uname as uname2_10_1_ from sys_role_sys_users sysusers0_ inner join sys_user sysuser1_ on sysusers0_.sys_users_id=sysuser1_.id where sysusers0_.sys_role_id=?
2021-01-22 17:55:51.721  INFO 8412 --- [           main] com.example.lewjun.Many2ManyTest         : 【sysUsers:[SysUser(id=4, uname=zs, sysRoles=[SysRole(id=1, uname=role1, sysUsers=[]), SysRole(id=2, uname=role2, sysUsers=[]), SysRole(id=3, uname=role3, sysUsers=[])])]】
Hibernate: select sysrole0_.id as id1_8_, sysrole0_.uname as uname2_8_ from sys_role sysrole0_
Hibernate: select sysusers0_.sys_role_id as sys_role1_9_0_, sysusers0_.sys_users_id as sys_user2_9_0_, sysuser1_.id as id1_10_1_, sysuser1_.uname as uname2_10_1_ from sys_role_sys_users sysusers0_ inner join sys_user sysuser1_ on sysusers0_.sys_users_id=sysuser1_.id where sysusers0_.sys_role_id=?
Hibernate: select sysusers0_.sys_role_id as sys_role1_9_0_, sysusers0_.sys_users_id as sys_user2_9_0_, sysuser1_.id as id1_10_1_, sysuser1_.uname as uname2_10_1_ from sys_role_sys_users sysusers0_ inner join sys_user sysuser1_ on sysusers0_.sys_users_id=sysuser1_.id where sysusers0_.sys_role_id=?
Hibernate: select sysusers0_.sys_role_id as sys_role1_9_0_, sysusers0_.sys_users_id as sys_user2_9_0_, sysuser1_.id as id1_10_1_, sysuser1_.uname as uname2_10_1_ from sys_role_sys_users sysusers0_ inner join sys_user sysuser1_ on sysusers0_.sys_users_id=sysuser1_.id where sysusers0_.sys_role_id=?
2021-01-22 17:55:51.738  INFO 8412 --- [           main] com.example.lewjun.Many2ManyTest         : 【sysRoles:[SysRole(id=1, uname=role1, sysUsers=[]), SysRole(id=2, uname=role2, sysUsers=[]), SysRole(id=3, uname=role3, sysUsers=[])]】
*/
    }

}
