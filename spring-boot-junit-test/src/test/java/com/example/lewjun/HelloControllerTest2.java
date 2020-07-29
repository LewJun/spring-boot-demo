package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * spring boot 测试类
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest2 {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String baseUrl;

    @Before
    public void setUp() throws MalformedURLException {
        this.baseUrl = new URL("http://localhost:" + port + "/demo").toString();
    }

    @Test
    public void testHello() {
        log.info("【port: {}】", port);
        String url = baseUrl + "/hello";
        final ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);
        log.info("【/hello: {}】", responseEntity);
    }
}
