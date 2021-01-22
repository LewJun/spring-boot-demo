package com.example.lewjun;

import com.example.lewjun.domain.one2many.Child;
import com.example.lewjun.domain.one2many.Father;
import com.example.lewjun.repositories.ChildRepository;
import com.example.lewjun.repositories.FatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Slf4j
@SpringBootTest
public class One2ManyTest {

    @Autowired
    FatherRepository fatherRepository;

    @Autowired
    ChildRepository childRepository;

    @Test
    void testOne2Many() {

        fatherRepository.save(new Father().setUname("Father")
                .setChildren(
                        Arrays.asList(
                                new Child().setUname("child 1"),
                                new Child().setUname("child 2"),
                                new Child().setUname("child 3")
                        )
                )
        );

        log.info("【findAll: {}】", fatherRepository.findAll());

        log.info("【findById: {}】", fatherRepository.findById(1));

        log.info("【findById:{}】", childRepository.findById(3));
    }
/*
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: insert into father (uname, id) values (?, ?)
2021-01-22 16:57:14.653 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [Father]
2021-01-22 16:57:14.656 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [1]
Hibernate: insert into child (uname, id) values (?, ?)
2021-01-22 16:57:14.658 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [child 1]
2021-01-22 16:57:14.659 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [2]
Hibernate: insert into child (uname, id) values (?, ?)
2021-01-22 16:57:14.661 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [child 2]
2021-01-22 16:57:14.661 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [3]
Hibernate: insert into child (uname, id) values (?, ?)
2021-01-22 16:57:14.662 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [child 3]
2021-01-22 16:57:14.662 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [4]
Hibernate: insert into father_children (father_id, children_id) values (?, ?)
2021-01-22 16:57:14.675 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-22 16:57:14.676 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [2]
Hibernate: insert into father_children (father_id, children_id) values (?, ?)
2021-01-22 16:57:14.679 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-22 16:57:14.679 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [3]
Hibernate: insert into father_children (father_id, children_id) values (?, ?)
2021-01-22 16:57:14.680 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-22 16:57:14.686 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [4]
Hibernate: select father0_.id as id1_3_, father0_.uname as uname2_3_ from father father0_
Hibernate: select children0_.father_id as father_i1_4_0_, children0_.children_id as children2_4_0_, child1_.id as id1_2_1_, child1_.uname as uname2_2_1_ from father_children children0_ inner join child child1_ on children0_.children_id=child1_.id where children0_.father_id=?
2021-01-22 16:57:15.013  INFO 1972 --- [           main] com.example.lewjun.One2ManyTest          : 【findAll: [Father(id=1, uname=Father, children=[Child(id=2, uname=child 1), Child(id=3, uname=child 2), Child(id=4, uname=child 3)])]】
Hibernate: select father0_.id as id1_3_0_, father0_.uname as uname2_3_0_ from father father0_ where father0_.id=?
2021-01-22 16:57:15.033 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
Hibernate: select children0_.father_id as father_i1_4_0_, children0_.children_id as children2_4_0_, child1_.id as id1_2_1_, child1_.uname as uname2_2_1_ from father_children children0_ inner join child child1_ on children0_.children_id=child1_.id where children0_.father_id=?
2021-01-22 16:57:15.035  INFO 1972 --- [           main] com.example.lewjun.One2ManyTest          : 【findById: Optional[Father(id=1, uname=Father, children=[Child(id=2, uname=child 1), Child(id=3, uname=child 2), Child(id=4, uname=child 3)])]】
Hibernate: select child0_.id as id1_2_0_, child0_.uname as uname2_2_0_ from child child0_ where child0_.id=?
2021-01-22 16:57:15.039 TRACE 1972 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [3]
2021-01-22 16:57:15.041  INFO 1972 --- [           main] com.example.lewjun.One2ManyTest          : 【findById:Optional[Child(id=3, uname=child 2)]】
  */
}
