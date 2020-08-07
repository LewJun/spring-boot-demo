# spring-boot-junit-test

> 单元测试例子

[TOC]

## [pom.xml](pom.xml)

## [App.java](src/main/java/com/example/lewjun/HelloController.java)

```java
package com.example.lewjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring boot 启动类
 */
@SpringBootApplication
@RestController
public class HelloController {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/")
    public String index() {
        return "/";
    }
}
```

## 使用MockMvc对接口做单元测试
[HelloControllerTest.java](src/test/java/com/example/lewjun/HelloControllerTest.java)
```java
package com.example.lewjun;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * spring boot 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {
    private MockMvc mvc;

    @Test
    public void testLoadContext() {
    }

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/hello")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World!")));
    }
}
```

## @AutoConfigureMockMvc 注解

用于自动化配置我们稍后注入的 MockMvc Bean 对象 mvc，这样，就可以取消手动初始化mvc了。

```java
/**
 * spring boot 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testLoadContext() {
    }
//
//    @Before
//    public void setUp() {
//        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
//    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/hello")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World!")))
        ;
    }
}
```

选中getHello运行即可

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo
```

## 使用TestRestTemplate对接口做单元测试
[HelloControllerTest2.java](src/test/java/com/example/lewjun/HelloControllerTest2.java)

```java
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
```

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

