package com.example.lewjun;

import com.example.lewjun.util.RequestUrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class JunitTest {
    @Test
    public void testSomething() {
        System.out.println(RequestUrlUtils.getRequestUrlWithoutQueryParams("/index/?"));
    }
}
