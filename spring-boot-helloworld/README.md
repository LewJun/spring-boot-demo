# spring-boot-helloworld

> 一个spring boot的hello world

[TOC]

## 模板复用

> 使用命令创建模块是一个重复的动作，可以直接拷贝该模块，然后修改pom.xml
<artifactId>spring-boot-helloworld</artifactId>为对应的模块名字即可
启动后访问 http://localhost:1234/demo 即可

## [pom.xml](pom.xml)

```xml
<?xml version="1.0"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.example.lewjun</groupId>
        <artifactId>spring-boot-demo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>spring-boot-helloworld</artifactId>
    <version>1.0-SNAPSHOT</version>
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

