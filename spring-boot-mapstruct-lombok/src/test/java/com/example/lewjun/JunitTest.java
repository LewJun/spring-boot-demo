package com.example.lewjun;

import com.example.lewjun.domain.bo.Ab01BO;
import com.example.lewjun.domain.convert.Ab01Convert;
import com.example.lewjun.domain.dataobject.Ab01DO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

@Slf4j
public class JunitTest {
    @Test
    public void test1() {
        Ab01DO ab01DO = new Ab01DO();
        ab01DO.setAab001(111);
        ab01DO.setAab002("aab002");
        ab01DO.setAab003("aab003");
        ab01DO.setBirthday(new Date());

        Ab01BO ab01BO = Ab01Convert.INSTANCE.convert(ab01DO);
        log.info("【ab01BO: {}】", ab01BO);
    }
}
