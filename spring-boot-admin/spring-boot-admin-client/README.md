# spring-boot-admin-client

> 搭建一个 Spring Boot Admin 的服务端项目，可视化展示自己客户端项目的运行状态。

[TOC]

## [pom.xml](pom.xml)

```xml
<?xml version="1.0"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd"
         xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.example.lewjun</groupId>
        <artifactId>spring-boot-admin</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>spring-boot-admin-client</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-client</artifactId>
            <version>${spring-boot-admin-dependencies.version}</version>
        </dependency>
    </dependencies>
</project>

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


debug: true

spring:
  devtools:
    livereload:
      enabled: false
      # solve: Unable to start LiveReload server, default port is 35729
      port: 35730

  # 若要访问端点信息，需要配置用户名和密码
  security:
    user:
      name: xyz
      password: xyz123

  application:
    name: spring-boot-admin-client
  boot:
    admin:
      client:
        # spring-boot-admin-server地址和端口
        url: "http://localhost:8000/"
        instance:
          metadata:
            # 客户端端点信息的安全认证信息
            user.name: ${spring.security.user.name}
            user.password: ${spring.security.user.password}

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
* 先启动服务端
* 再启动客户端
* 最后访问 http://localhost:8000 即可在服务端看到所有注册到服务端的客户端信息。

