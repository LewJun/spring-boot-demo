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

## [HelloControllerTest.java](src/test/java/com/example/lewjun/HelloControllerTest.java)
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

选中getHello运行即可

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo
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

