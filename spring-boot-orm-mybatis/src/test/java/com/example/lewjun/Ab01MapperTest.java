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
    public void testLoadContext() {
        log.info("【queryAll:{}】", ab01Mapper.queryAll());
        log.info("【queryByAab002:{}】", ab01Mapper.queryByAab002("ACCOUNTING"));
        final Ab01 ab01 = new Ab01().setAab002("aab002").setAab003("aab003");
        ab01Mapper.insert(ab01);

        ab01Mapper.update(new Ab01().setAab001(41).setAab002("002").setAab003("003"));

        ab01Mapper.delete(10);

        log.info("【queryByAac001:{}】", ab01Mapper.queryByAab001(20));

        log.info("【queryByAab002AndAab003:{}】", ab01Mapper.queryByAab002AndAab003("002", "003"));

        log.info("【queryByAab003AndAab002:{}】", ab01Mapper.queryByAab003AndAab002("003", "002"));

        log.info("【queryAll:{}】", ab01Mapper.queryAll());
    }
}