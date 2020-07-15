# spring-boot-admin-server

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

    <artifactId>spring-boot-admin-server</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-server</artifactId>
            <version>${spring-boot-admin-dependencies.version}</version>
        </dependency>
    </dependencies>
</project>

```

## [App.java](src/main/java/com/example/lewjun/App.java)

```java
/**
 * spring boot 启动类
 */
@EnableAdminServer      // <---------- **WARNING** 注意这里的EnableAdminServer注解
@SpringBootApplication
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 8000

debug: true
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

