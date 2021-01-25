package com.example.lewjun;

import com.example.lewjun.domain.one2many.Child;
import com.example.lewjun.domain.one2many.Father;
import com.example.lewjun.domain.one2many.FatherChild;
import com.example.lewjun.repositories.ChildRepository;
import com.example.lewjun.repositories.FatherChildRepository;
import com.example.lewjun.repositories.FatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class One2ManyTest {

    @Autowired
    FatherRepository fatherRepository;

    @Autowired
    ChildRepository childRepository;

    @Autowired
    FatherChildRepository fatherChildRepository;

    @Test
    void testOne2Many() {
        final Father father = new Father().setUname("Father");
        fatherRepository.save(father);

        final List<Child> children = Arrays.asList(
                new Child().setUname("child 1"),
                new Child().setUname("child 2"),
                new Child().setUname("child 3")
        );
        childRepository.saveAll(children);

        final Integer fatherId = father.getId();
        fatherChildRepository.saveAll(
                children.stream().map(child ->
                        new FatherChild()
                                .setFather(fatherId)
                                .setChild(child.getId())
                ).collect(Collectors.toList())
        );

        log.info("【father findAll: {}】", fatherRepository.findAll());

        log.info("【father findById: {}】", fatherRepository.findById(1));

        log.info("【child findAll:{}】", childRepository.findAll());

        log.info("【father child findAll:{}】", fatherChildRepository.findAll());
    }
/*
一对多 采用中间表的方式。
Hibernate: call next value for hibernate_sequence
Hibernate: insert into father (uname, id) values (?, ?)
2021-01-25 15:01:13.031 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [Father]
2021-01-25 15:01:13.031 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [1]
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: insert into child (uname, id) values (?, ?)
2021-01-25 15:01:13.047 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [child 1]
2021-01-25 15:01:13.047 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [2]
Hibernate: insert into child (uname, id) values (?, ?)
2021-01-25 15:01:13.047 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [child 2]
2021-01-25 15:01:13.047 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [3]
Hibernate: insert into child (uname, id) values (?, ?)
2021-01-25 15:01:13.047 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [child 3]
2021-01-25 15:01:13.047 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [4]
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: insert into father_child (child, father, id) values (?, ?, ?)
2021-01-25 15:01:13.063 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [2]
2021-01-25 15:01:13.063 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [1]
2021-01-25 15:01:13.063 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [INTEGER] - [5]
Hibernate: insert into father_child (child, father, id) values (?, ?, ?)
2021-01-25 15:01:13.063 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [3]
2021-01-25 15:01:13.063 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [1]
2021-01-25 15:01:13.063 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [INTEGER] - [6]
Hibernate: insert into father_child (child, father, id) values (?, ?, ?)
2021-01-25 15:01:13.063 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [4]
2021-01-25 15:01:13.063 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [1]
2021-01-25 15:01:13.063 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [INTEGER] - [7]
Hibernate: select father0_.id as id1_3_, father0_.uname as uname2_3_ from father father0_
2021-01-25 15:01:13.094  INFO 12776 --- [           main] com.example.lewjun.One2ManyTest          : 【father findAll: [com.example.lewjun.domain.one2many.Father@5e1a986c]】
Hibernate: select father0_.id as id1_3_0_, father0_.uname as uname2_3_0_ from father father0_ where father0_.id=?
2021-01-25 15:01:13.110 TRACE 12776 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [1]
2021-01-25 15:01:13.125  INFO 12776 --- [           main] com.example.lewjun.One2ManyTest          : 【father findById: Optional[com.example.lewjun.domain.one2many.Father@1d6a8386]】
Hibernate: select child0_.id as id1_2_, child0_.uname as uname2_2_ from child child0_
2021-01-25 15:01:13.125  INFO 12776 --- [           main] com.example.lewjun.One2ManyTest          : 【child findAll:[com.example.lewjun.domain.one2many.Child@76a7fcbd, com.example.lewjun.domain.one2many.Child@433ea2ac, com.example.lewjun.domain.one2many.Child@7f977fba]】
Hibernate: select fatherchil0_.id as id1_4_, fatherchil0_.child as child2_4_, fatherchil0_.father as father3_4_ from father_child fatherchil0_
2021-01-25 15:01:13.141  INFO 12776 --- [           main] com.example.lewjun.One2ManyTest          : 【father child findAll:[com.example.lewjun.domain.one2many.FatherChild@110e9982, com.example.lewjun.domain.one2many.FatherChild@5eb0a686, com.example.lewjun.domain.one2many.FatherChild@73608eb0]】
  */
}
