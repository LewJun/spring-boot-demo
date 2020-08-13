package com.example.lewjun;

import com.example.lewjun.domain.Ab01;
import com.example.lewjun.mapper.Ab01Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class Ab01MapperTest {
    @Autowired
    private Ab01Mapper ab01Mapper;

    @Test
    public void testShardingInsert() {
        ab01Mapper.truncate();
        for (int i = 0; i < 100; i++) {
            ab01Mapper.insert(new Ab01().setAab001(i).setAab002("aab002 " + i).setAab003("aab003 " + i));
        }
    }

    @Test
    public void testShardingQueryAll() {
        log.info("【queryAll:{}】", ab01Mapper.queryAll());
    }
}
