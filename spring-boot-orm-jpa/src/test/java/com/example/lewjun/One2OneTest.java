package com.example.lewjun;

import com.example.lewjun.domain.one2one.Address;
import com.example.lewjun.domain.one2one.People;
import com.example.lewjun.repositories.PeopleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
class One2OneTest {

    @Autowired
    PeopleRepository peopleRepository;

    @Test
    public void testOneToOne() {
        final Address address = new Address()
                .setPhone("028-88888888")
                .setStreet("street NO.1")
                .setZipcode("610000");

        final People people = new People()
                .setUsername("LewJun")
                .setAge(32)
                .setWeight(65.3f)
                .setBirthday(LocalDate.now())
                .setAddress(address);

        final People savedPeople = peopleRepository.save(people);

        log.info("【address: {}】", address);
        log.info("【savedPeople: {}】", savedPeople);

        log.info("【findAll:{}】", peopleRepository.findAll());
/*
执行如上测试，打印日志如下所示：
Hibernate: call next value for hibernate_sequence
Hibernate: call next value for hibernate_sequence
Hibernate: insert into address (phone, street, zipcode, id) values (?, ?, ?, ?)

Hibernate: insert into people (address_id, age, birthday, username, weight, id) values (?, ?, ?, ?, ?, ?)

2021-01-22 14:54:35.252  INFO 1064 --- [           main] com.example.lewjun.PeopleRepositoryTest  : 【address: Address(id=2, phone=028-88888888, zipcode=610000, street=street NO.1)】
2021-01-22 14:54:35.252  INFO 1064 --- [           main] com.example.lewjun.PeopleRepositoryTest  : 【savedPeople: People(id=1, username=LewJun, age=32, weight=65.3, birthday=2021-01-22, address=Address(id=2, phone=028-88888888, zipcode=610000, street=street NO.1))】
Hibernate: select people0_.id as id1_2_, people0_.address_id as address_6_2_, people0_.age as age2_2_, people0_.birthday as birthday3_2_, people0_.username as username4_2_, people0_.weight as weight5_2_ from people people0_
Hibernate: select address0_.id as id1_1_0_, address0_.phone as phone2_1_0_, address0_.street as street3_1_0_, address0_.zipcode as zipcode4_1_0_ from address address0_ where address0_.id=?
2021-01-22 14:54:35.320 TRACE 1064 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [2]
2021-01-22 14:54:35.332  INFO 1064 --- [           main] com.example.lewjun.PeopleRepositoryTest  : 【findAll:[People(id=1, username=LewJun, age=32, weight=65.3, birthday=2021-01-22, address=Address(id=2, phone=028-88888888, zipcode=610000, street=street NO.1))]】
查询主表（关系维护表）的时候，会把关联表的数据查询出来。
*/
    }
}
