package com.example.lewjun;

import com.example.lewjun.dao.Ab01Dao;
import com.example.lewjun.domain.Ab01;
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
class AppTest {

    @Autowired
    private Ab01Dao ab01Dao;

    @Test
    void testLoadContext() {
        final int save = ab01Dao.save("insert into ab01(aab001, aab002, aab003) values(?,?,?)", 123, "xxx", "yyy");
        log.info("save: {}", save);// ok

        final Ab01 ab01 = ab01Dao.queryForObj("select * from ab01 where aab001=?", Ab01.class, 123);
        log.info("ab01: {}", ab01);// ok

        final List<Ab01> ab01s = ab01Dao.queryForObjs("select aab002, aab003 from ab01", Ab01.class);
        log.info("ab01s: {}", ab01s);// ok

        final List<Integer> aab001s = ab01Dao.queryForList("select aab001 from ab01", Integer.class);
        log.info("aab001s:{}", aab001s);// ok

        final Integer count = ab01Dao.queryForObject("select count(0) r from ab01", Integer.class);
        log.info("count: {}", count); // ok

        final int delete = ab01Dao.delete("delete from ab01 where aab001=?", 1223);
        log.info("delete: {}", delete); // ok

        final int update = ab01Dao.update("update ab01 set aab002=?, aab003=? where aab001=?", "aab002", "aab003", 123);
        log.info("update: {}", update); // ok

        final int deleteById = ab01Dao.deleteById("delete from ab01 where aab001=?", 123);
        log.info("deleteById:{}", deleteById);// ok
    }
}
