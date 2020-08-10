package com.example.lewjun;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lewjun.domain.Ab01;
import com.example.lewjun.service.Ab01Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class Ab01ServiceTest {
    @Autowired
    private Ab01Service ab01Service;

    @Test
    public void testAb01Service() {
        // 增
        final Ab01 ab01Save = new Ab01(50, "aab002", "aab003");
        if (ab01Service.save(ab01Save)) {
            log.info("【list: {}】", ab01Service.list());
        }

        log.info("【saveBatch: {}】",
                ab01Service.saveBatch(
                        Arrays.asList(
                                new Ab01(1, "a1", "b1"),
                                new Ab01(2, "a2", "b2"),
                                new Ab01(3, "a3", "b3")
                        )
                ));

        final int count = ab01Service.count();
        log.info("【count: {}】", count);

        // 根据id查询
        final Ab01 ab01 = ab01Service.getById(10);
        log.info("【ab01: {}】", ab01);

        // 修改
        ab01Service.updateById(ab01.setAab002("xxx").setAab003("yyy"));

        // 删除
        log.info("【remove 60: {}】", ab01Service.removeById(60));
        log.info("【remove 50: {}】", ab01Service.removeById(50));

        // 获取列表
        log.info("【list: {}】", ab01Service.list());

        // list by ids
        log.info("【listByIds: {}】", ab01Service.listByIds(Arrays.asList(20, 30, 40, 50, 60)));

        log.info("【aab002=b2: {}】", ab01Service.query().eq("aab002", "a2").count());

        log.info("【aab003 like ll: {}】", ab01Service.query().like("aab003", "LL").list());

        final Page<Ab01> ab01Page = ab01Service.page(
                new Page<Ab01>()
                        .setCurrent(2) // 第2页
                        .setSize(3) // 第3页
        );

        // 按照条件，总的有7条
        log.info("【ab01Page total: {}】", ab01Page.getTotal());

        final List<Ab01> ab01List = ab01Page.getRecords();

        // 第2页的3条数据
        log.info("【ab01List page: {}】", ab01List);

        log.info("【ab01 page 3/3: {}】",
                ab01Service.page(
                        new Page<Ab01>()
                                .setCurrent(3) // 第3页
                                .setSize(3) // 第3页
                ).getRecords()
        );

    }

    @Test
    public void testQueryByAab002() {
        log.info("【queryByAab002: {}】", ab01Service.queryByAab002("SALES"));
    }

    @Test
    public void testQueryByAab003() {
        log.info("【queryByAab003: {}】", ab01Service.queryByAab003("DALLAS"));
    }

    @Test
    public void testQueryAb01Ac01() {
        log.info("【queryAb01Ac01:{}】", ab01Service.queryAb01Ac01());
    }


    @Test
    public void testGetById() {
        final Ab01 ab01 = ab01Service.getById(100);
        log.info("【getById:{}】", ab01);
    }

    @Test
    public void testGetByIdOptional() {
        final String aab002 = ab01Service.getByIdOptional(100).map(Ab01::getAab002)
                .orElseThrow(() -> new IllegalArgumentException("illegal arg"));
        log.info("【aab002:{}】", aab002);
    }
}
