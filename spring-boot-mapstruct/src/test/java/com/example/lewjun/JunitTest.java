package com.example.lewjun;

import com.example.lewjun.domain.bo.Ab01BO;
import com.example.lewjun.domain.convert.Ab01Convert;
import com.example.lewjun.domain.dataobject.Ab01DO;
import org.junit.Test;

public class JunitTest {
    @Test
    public void test1() {
        Ab01DO ab01DO = new Ab01DO();
        ab01DO.setAab001(111);
        ab01DO.setAab002("aab002");
        ab01DO.setAab003("aab003");

        Ab01BO ab01BO = Ab01Convert.INSTANCE.do2bo(ab01DO);
        System.out.println(ab01BO.getAab001());
        System.out.println(ab01BO.getAab002());
        System.out.println(ab01BO.getAab003());
    }
}
