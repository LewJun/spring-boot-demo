# spring-boot-actuator

> spring-boot 集成 spring-boot-starter-actuator 用于监控 spring-boot 的启动和运行状态

[TOC]

## [pom.xml](pom.xml)

添加依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
</dependencies>
```

## [App.java](src/main/java/com/example/lewjun/App.java)

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
public class App {
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

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo

# 若要访问端点信息，需要配置用户名和密码
spring:
  security:
    user:
      name: xyz
      password: xyz123

management:
  # 端点信息接口使用的端口，为了和主系统接口使用的端口进行分离
  server:
    port: 4321
    servlet:
      context-path: /sys

  # 端点健康情况，默认值"never"，设置为"always"可以显示硬盘使用情况和线程情况
  endpoint:
    health:
      show-details: always

  # 设置端点暴露的哪些内容，默认["health","info"]，设置"*"代表暴露所有可访问的端点
  endpoints:
    web:
      exposure:
        include: '*'
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

## 访问
* 在浏览器访问：[http://localhost:1234/demo](http://localhost:1234/demo) ，由于配置了spring.security，
所以会跳转到[http://localhost:1234/demo/login](http://localhost:1234/demo/login) ，
输入xyz/xyz123即可登录。

* 在浏览器访问：[http://localhost:4321/sys/actuator](http://localhost:4321/sys/actuator) ，输入xyz/xyz123后，可以看到很多可以访问的路径。
返回数据为json结构，使用firefox可以看到格式化后的数据。

