package com.example.lewjun;

import com.example.lewjun.domain.Ad01;
import com.example.lewjun.mapper.Ad01Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class Ad01MapperTest {
    @Autowired
    public Ad01Mapper ad01Mapper;

    @Test
    public void testInsert() {
        ad01Mapper.insert(new Ad01().setAad001(10));
        ad01Mapper.insert(new Ad01().setAad001(20));
        ad01Mapper.insert(new Ad01().setAad001(30));
        ad01Mapper.insert(new Ad01().setAad001(40));
        ad01Mapper.insert(new Ad01().setAad001(50));
        ad01Mapper.insert(new Ad01().setAad001(60));
    }

    @Test
    public void testQueryAll() {
        // 查询时候，会从任意库中查询ad01
        log.info("【queryAll: {}】", ad01Mapper.queryAll());
    }
}
