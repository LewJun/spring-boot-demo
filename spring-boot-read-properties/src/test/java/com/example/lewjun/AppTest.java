package com.example.lewjun;

import com.example.lewjun.model.DatasourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class AppTest {

    @Autowired
    private StringEncryptor stringEncryptor;
    @Autowired
    private DatasourceProperties datasourceProperties;

    @Test
    public void testLoadContext() {
        log.info("【username: {}】", stringEncryptor.encrypt("root"));
        log.info("【password: {}】", stringEncryptor.encrypt("root123456"));
        log.info("【driver-class-name: {}】", stringEncryptor.encrypt("com.mysql.cj.jdbc.Driver"));
        log.info("【url: {}】", stringEncryptor.encrypt("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia"));
    }

    @Test
    public void testDeDataSource() {
        log.info("【datasourceProperties: {}】", datasourceProperties);
    }
}
