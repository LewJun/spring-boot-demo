package com.example.lewjun;

import com.example.lewjun.mapper.Ac01Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class Ac01MapperTest {

    @Autowired
    private Ac01Mapper ac01Mapper;

    @Test
    public void testQueryByAc01Ab01() {
        log.info("【queryByAc01Ab01:{}】", ac01Mapper.queryByAc01Ab01());
    }
}
