package com.example.lewjun;

import com.example.lewjun.domain.Ac01;
import com.example.lewjun.mapper.Ac01Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Slf4j
@SpringBootTest
public class Ac01MapperTest {
    @Autowired
    public Ac01Mapper ac01Mapper;

    @Test
    public void testInsert() {
        ac01Mapper.insert(
                new Ac01()
                        .setAac002("aac002")
                        .setAac003("aac003")
                        .setAac004(4)
                        .setAac005(new Date())
                        .setAac006(10L)
        );
    }

    @Test
    public void testQueryAll() {
        log.info("【queryAll: {}】", ac01Mapper.queryAll());
    }

}
