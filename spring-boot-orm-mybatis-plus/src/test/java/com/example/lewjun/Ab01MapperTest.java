package com.example.lewjun;

import com.example.lewjun.domain.Ab01;
import com.example.lewjun.mapper.Ab01Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Slf4j
@SpringBootTest
public class Ab01MapperTest {
    @Autowired
    private Ab01Mapper ab01Mapper;

    @Test
    public void testQueryWhere() {
        log.info("【queryWhere:{}】",
                ab01Mapper.queryWhere(
                        new Ab01()
                                .setAab001(10)
                                .setAab002("aab002")
                                .setAab003("aab003")
                )
        );
    }

    @Test
    public void testQueryChoose() {
        log.info("【queryChoose a:{}】",
                ab01Mapper.queryChoose("a")
        );
        log.info("【queryChoose b:{}】",
                ab01Mapper.queryChoose("b")
        );
        log.info("【queryChoose c:{}】",
                ab01Mapper.queryChoose("c")
        );
    }

    @Test
    public void testQueryTrim() {
        log.info("【queryTrim:{}】",
                ab01Mapper.queryTrim()
        );
    }

    @Test
    public void testUpdateSelective() {
        log.info("【updateSelective:{}】", ab01Mapper.updateSelective(new Ab01(10, null, "aab003")));
        log.info("【selectById:{}】", ab01Mapper.selectById(10));
    }

    @Test
    public void testQueryByPks() {
        log.info("【queryByPks:{}】", ab01Mapper.queryByPks(Arrays.asList(10, 30)));
    }

    @Test
    public void testInserts() {
        ab01Mapper.inserts(Arrays.asList(
                new Ab01().setAab001(1).setAab002("aab002").setAab003("aab003"),
                new Ab01().setAab001(2).setAab002("aab0021").setAab003("aab0031")
        ));
    }

    @Test
    public void testInsertSelective() {
        final Ab01 ab01 = new Ab01().setAab001(1).setAab002("aab002").setAab003("aab003");
        ab01Mapper.insertSelective(ab01);
        log.info("【ab01.aab001:{}】", ab01.getAab001());
    }
}
