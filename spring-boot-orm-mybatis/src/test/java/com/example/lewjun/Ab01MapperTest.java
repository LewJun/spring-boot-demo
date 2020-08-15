package com.example.lewjun;

import com.example.lewjun.domain.Ab01;
import com.example.lewjun.domain.Ab01Ac01;
import com.example.lewjun.mapper.Ab01Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    public void testQueryByAab002Cache() {
        log.info("【1:{}】", ab01Mapper.queryByAab002("SALES"));
        log.info("【2:{}】", ab01Mapper.queryByAab002("SALES"));

        ab01Mapper.delete(30);// 这里有一个删除操作

        log.info("【3:{}】", ab01Mapper.queryByAab002("SALES"));
    }

    @Test
    public void queryByAab003() {
        log.info("【queryByAab003:{}】", ab01Mapper.queryByAab003("DALLAS"));
    }

    @Test
    public void queryByAb01Ac01() {
        final List<Ab01Ac01> ab01Ac01s = ab01Mapper.queryByAb01Ac01();
        log.info("【queryByAb01Ac01:{}】", ab01Ac01s);
    }

    @Test
    public void testInsertUseProvider() {
        final Ab01 ab01 = new Ab01().setAab002("002").setAab003("003");
        log.info("【insertUseProvider: {}】", ab01Mapper.insertUseProvider(ab01));
    }

    @Test
    public void testUpdateUseProvider() {
        log.info("【updateUseProvider: {}】", ab01Mapper.updateUseProvider(
                new Ab01().setAab001(10)
                        .setAab002("String aab002")
                        .setAab003("String aab003")
                )
        );

        log.info("【queryByAab001: {}】", ab01Mapper.queryByAab001(10));
    }

    @Test
    public void testUpdateSelectiveUseProvider() {
        log.info("【updateSelectiveUseProvider: {}】", ab01Mapper.updateSelectiveUseProvider(
                new Ab01().setAab001(10)
                        .setAab002("String aab002")
                )
        );

        log.info("【queryByAab001: {}】", ab01Mapper.queryByAab001(10));
    }

    @Test
    public void testDeleteUseProvider() {
        log.info("【deleteUseProvider: {}】", ab01Mapper.deleteUseProvider(10));

        log.info("【queryAll: {}】", ab01Mapper.queryAll());
    }

    @Test
    public void testQueryByAab001UseProvider() {
        log.info("【queryByAab001UseProvider: {}】", ab01Mapper.queryByAab001UseProvider(10));
    }

    @Test
    public void testQueryByAb01Ac01UseProvider() {
        log.info("【queryByAb01Ac01UseProvider: {}】", ab01Mapper.queryByAb01Ac01UseProvider());
    }

}
