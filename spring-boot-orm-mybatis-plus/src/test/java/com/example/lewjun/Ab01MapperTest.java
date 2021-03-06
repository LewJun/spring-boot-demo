package com.example.lewjun;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lewjun.domain.Ab01;
import com.example.lewjun.mapper.Ab01Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

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
    public void testInsert() {
        ab01Mapper.insert(new Ab01().setAab001(1).setAab002("aab002").setAab003("aab003"));
        ab01Mapper.insert(new Ab01().setAab001(2).setAab002("aab0021").setAab003("aab0031"));
        log.info("【selectById 1: {}】", ab01Mapper.selectById(1));

        ab01Mapper.updateById(new Ab01().setAab001(2).setAab002("aab0021").setAab003("aab003xxx"));
        final Ab01 ab01 = ab01Mapper.selectById(2);
        log.info("【selectById 2: {}】", ab01);
        log.info("【selectById 2: {}】", ab01.getCreateTime());
        log.info("【selectById 2: {}】", ab01.getUpdateTime());
    }

    @Test
    public void testInsertSelective() {
        final Ab01 ab01 = new Ab01().setAab001(1).setAab002("aab002").setAab003("aab003");
        ab01Mapper.insertSelective(ab01);
        log.info("【ab01.aab001:{}】", ab01.getAab001());
    }

    @Test
    void testQueryAll() {
        IPage<Ab01> ab01Page = new Page<>(2, 2);
        ab01Page = ab01Mapper.selectPage(ab01Page, null);
        log.info("【ab01Page: {}】", ab01Page.getSize());
        log.info("【ab01Page: {}】", ab01Page.getTotal());


        final List<Ab01> records = ab01Page.getRecords();
        for (final Ab01 ab01 : records) {
            log.info("【ab01: {}】", ab01);
        }
    }
}
