package com.example.lewjun;

import com.example.lewjun.domain.Ab01;
import com.example.lewjun.service.Ab01Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class Ab01ServiceTest {

    @Autowired
    private Ab01Service ab01Service;

    @Test
    public void test() {
        log.info("【queryAll: {}】", ab01Service.queryAll());
        log.info("【queryById: {}】", ab01Service.queryById(20));
        log.info("【getAb01Count: {}】", ab01Service.getAb01Count());

        log.info("【queryByAab002: {}】", ab01Service.queryByAab002("SALES"));
        log.info("【queryByAab003: {}】", ab01Service.queryByAab003("DALLAS"));

        log.info("【delete: {}】", ab01Service.delete(40));

        ab01Service.update(new Ab01().setAab001(10).setAab002("aab002").setAab003("aab003"));

        ab01Service.save(new Ab01().setAab002("002").setAab003("003"));

        log.info("【queryAll: {}】", ab01Service.queryAll());

        log.info("【queryAllAab001: {}】", ab01Service.queryAllAab001());
    }

    @Test
    public void testQueryAll() {
        log.info("【queryAll: {}】", ab01Service.queryAll());
    }
}
