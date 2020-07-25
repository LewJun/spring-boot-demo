package com.example.lewjun;

import com.example.lewjun.service.Ac01Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class Ac01ServiceTest {
    @Autowired
    private Ac01Service ac01Service;

    @Test
    public void testAc01Service() {
        log.info("【ac01List: {}】", ac01Service.list());
    }

    @Test
    public void testQueryAc01Ab01() {
        log.info("【Ac01Ab01List: {}】", ac01Service.queryAc01Ab01());
    }
}
