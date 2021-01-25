package com.example.lewjun;

import com.example.lewjun.domain.one2many2.Mather;
import com.example.lewjun.domain.one2many2.Son;
import com.example.lewjun.repositories.MatherRepository;
import com.example.lewjun.repositories.SonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class One2Many2Test {

    @Autowired
    MatherRepository matherRepository;

    @Autowired
    SonRepository sonRepository;

    @Test
    void testOne2Many() {

        final Mather mather = new Mather();
        mather.setUname("Mather")
                .setSons(Arrays.asList(
                        new Son().setUname("son 1").setMather(mather),
                        new Son().setUname("son 2").setMather(mather),
                        new Son().setUname("son 3").setMather(mather)
                ));
        matherRepository.save(mather);

        log.info("【mather:{}】", mather);
        final List<Mather> matherList = matherRepository.findAll();
        log.info("【matherList:{}】", matherList);

        final List<Son> sons = sonRepository.findAll();
        log.info("【sons:{}】", sons);
        for (final Son son : sons) {
            log.info("【son.name:{}】", son.getUname());
            log.info("【son mather.name:{}】", son.getMather().getUname());
        }
    }
    /*
不会建立中间表，只会在关系被维护端加入主表主键编码。
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: insert into mather (uname, id) values (?, ?)
2021-01-25 14:43:04.643 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [Mather]
2021-01-25 14:43:04.643 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [1]
Hibernate: insert into son (mather_id, uname, id) values (?, ?, ?)
2021-01-25 14:43:04.643 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-25 14:43:04.659 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [VARCHAR] - [son 1]
2021-01-25 14:43:04.659 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [INTEGER] - [2]
Hibernate: insert into son (mather_id, uname, id) values (?, ?, ?)
2021-01-25 14:43:04.659 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-25 14:43:04.659 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [VARCHAR] - [son 2]
2021-01-25 14:43:04.659 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [INTEGER] - [3]
Hibernate: insert into son (mather_id, uname, id) values (?, ?, ?)
2021-01-25 14:43:04.659 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-25 14:43:04.659 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [VARCHAR] - [son 3]
2021-01-25 14:43:04.659 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [INTEGER] - [4]
2021-01-25 14:43:04.659  INFO 10468 --- [           main] com.example.lewjun.One2Many2Test         : 【mather:com.example.lewjun.domain.one2many2.Mather@6da4feeb】
Hibernate: select mather0_.id as id1_7_, mather0_.uname as uname2_7_ from mather mather0_
2021-01-25 14:43:04.706  INFO 10468 --- [           main] com.example.lewjun.One2Many2Test         : 【matherList:[com.example.lewjun.domain.one2many2.Mather@6f6cc7da]】
Hibernate: select son0_.id as id1_9_, son0_.mather_id as mather_i3_9_, son0_.uname as uname2_9_ from son son0_
2021-01-25 14:43:04.721  INFO 10468 --- [           main] com.example.lewjun.One2Many2Test         : 【sons:[com.example.lewjun.domain.one2many2.Son@256a0d95, com.example.lewjun.domain.one2many2.Son@2f3928ac, com.example.lewjun.domain.one2many2.Son@4bf03fee]】
2021-01-25 14:43:04.721  INFO 10468 --- [           main] com.example.lewjun.One2Many2Test         : 【son.name:son 1】
Hibernate: select mather0_.id as id1_7_0_, mather0_.uname as uname2_7_0_ from mather mather0_ where mather0_.id=?
2021-01-25 14:43:04.721 TRACE 10468 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-25 14:43:04.737  INFO 10468 --- [           main] com.example.lewjun.One2Many2Test         : 【son mather.name:Mather】
2021-01-25 14:43:04.737  INFO 10468 --- [           main] com.example.lewjun.One2Many2Test         : 【son.name:son 2】
2021-01-25 14:43:04.737  INFO 10468 --- [           main] com.example.lewjun.One2Many2Test         : 【son mather.name:Mather】
2021-01-25 14:43:04.737  INFO 10468 --- [           main] com.example.lewjun.One2Many2Test         : 【son.name:son 3】
2021-01-25 14:43:04.737  INFO 10468 --- [           main] com.example.lewjun.One2Many2Test         : 【son mather.name:Mather】
     */
}
