# spring-boot-multi-env

> 演示spring boot 项目多环境配置

[TOC]


## [application.yml](src/main/resources/application.yml)

```yaml
server:
  servlet:
    context-path: /demo

spring:
  profiles:
    active: dev # 使用dev环境
```

## [application-dev.yml](src/main/resources/application-dev.yml)
```yaml
# 开发环境
env:
  name: "开发环境"

server:
  port: 1234
```

## [application-pre.yml](src/main/resources/application-pre.yml)
```yaml
# 预发布环境
env:
  name: "预发布环境"

server:
  port: 8080
```

## [application-prod.yml](src/main/resources/application-prod.yml)
```yaml
# 生产环境
env:
  name: "生产环境"

server:
  port: 8888
```

## [App.java](src/main/java/com/example/lewjun/App.java)

```java

/**
 * spring boot 启动类
 */
@SpringBootApplication
@RestController
@Slf4j
public class App {
    @Value("${env.name}")
    private String envName;

    @Value("${server.port}")
    private int serverPort;

    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") final String name) {
        log.info("envName: {}", envName);
        log.info("serverPort: {}", serverPort);
        return String.format("Hello %s!", name);
    }

    @GetMapping("/")
    public String index() {
        log.info("envName: {}", envName);
        log.info("serverPort: {}", serverPort);
        return "/";
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

* 直接运行
> java -jar xxx.jar

此时，控制台输出的日志：
```log
2020-07-22 09:50:36.155  INFO 10112 --- [  restartedMain] com.example.lewjun.App                   : The following profiles are active: dev
2020-07-22 09:50:38.868  INFO 10112 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 1234 (http)


2020-07-22 10:15:11.277  INFO 900 --- [io-1234-exec-10] com.example.lewjun.App                   : envName: 开发环境
2020-07-22 10:15:11.279  INFO 900 --- [io-1234-exec-10] com.example.lewjun.App                   : serverPort: 1234
```


* 在运行的时候指定环境

`--spring.profiles.active=pre`

> java -jar xxx.jar --spring.profiles.active=pre

## 测试用例
[AppTest.java](src/test/java/com/example/lewjun/AppTest.java)

```java

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class AppTest {
    @Value("${env.name}")
    private String envName;

    @Value("${server.port}")
    private int serverPort;

    @Test
    public void testLoadContext() {
        log.info("envName: {}", envName);
        log.info("serverPort: {}", serverPort);
    }
}
```
