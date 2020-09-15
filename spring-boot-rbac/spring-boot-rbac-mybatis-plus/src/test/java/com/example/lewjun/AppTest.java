package com.example.lewjun;

import com.example.lewjun.utils.CamelUnderlineUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@SpringBootTest
class AppTest {
    @Test
    void testLoadContext() {
        System.out.println(CamelUnderlineUtil.camelToUnderline("deptId"));
    }
}
