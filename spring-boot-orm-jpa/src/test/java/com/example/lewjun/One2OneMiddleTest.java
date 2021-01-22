package com.example.lewjun;

import com.example.lewjun.domain.one2one_middle.Husband;
import com.example.lewjun.domain.one2one_middle.HusbandWife;
import com.example.lewjun.domain.one2one_middle.Wife;
import com.example.lewjun.repositories.HusbandRepository;
import com.example.lewjun.repositories.HusbandWifeRepository;
import com.example.lewjun.repositories.WifeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
public class One2OneMiddleTest {

    @Autowired
    WifeRepository wifeRepository;

    @Autowired
    HusbandRepository husbandRepository;

    @Autowired
    HusbandWifeRepository husbandWifeRepository;

    @Test
    void testSave() {
        final Wife wife = new Wife()
                .setUsername("LewJun")
                .setAge(32)
                .setWeight(65.3f)
                .setBirthday(LocalDate.now());
        wifeRepository.save(wife);

        final Husband husband = new Husband()
                .setPhone("028-88888888")
                .setStreet("street NO.1")
                .setZipcode("610000");
        husbandRepository.save(husband);

        final HusbandWife husbandWife = new HusbandWife();
        husbandWife.setHusband(husband.getId())
                .setWife(wife.getId());
        husbandWifeRepository.save(husbandWife);

        log.info("【husbandWife: {}】", husbandWifeRepository.findAll());
    }
}
