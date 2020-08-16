package com.example.lewjun;

import com.example.lewjun.domain.Ab01;
import com.example.lewjun.repositories.Ab01Repository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class AppTest {
    @Autowired
    private Ab01Repository ab01Repository;

    @Test
    public void testLoadContext() {
        ab01Repository.saveAll(
                Arrays.asList(
                        new Ab01().setAab002("aab002").setAab003("aab003"),
                        new Ab01().setAab002("aab0021").setAab003("aab0031"),
                        new Ab01().setAab002("aab0022").setAab003("aab0032")
                )
        );

        log.info("【findAll: {}】", ab01Repository.findAll());
        log.info("【findAb01: {}】", ab01Repository.findAb01("aab002"));

        log.info("【findByAab002: {}】", ab01Repository.findByAab002("aab002"));

        log.info("【findByAab002AndAab003: {}】", ab01Repository.findByAab002AndAab003("aab0021", "aab0031"));

        ab01Repository.deleteById(2);

        log.info("【findAll: {}】", ab01Repository.findAll(Sort.by(Sort.Order.desc("aab001"))));
    }
}
