# spring-boot-admin

> 主要演示了 Spring Boot 如何集成 Admin 管控台，监控管理 Spring Boot 应用，分别为 [admin 服务端](spring-boot-admin-server)
>和 [admin 客户端](spring-boot-admin-client) 两个模块。

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
        <artifactId>spring-boot-demo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>spring-boot-admin</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>spring-boot-admin-client</module>
        <module>spring-boot-admin-server</module>
    </modules>

    <properties>
        <spring-boot-admin-dependencies.version>2.2.3</spring-boot-admin-dependencies.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>de.codecentric</groupId>
                <artifactId>spring-boot-admin-dependencies</artifactId>
                <version>${spring-boot-admin-dependencies.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>

```

## 运行步骤

* 启动 [admin 服务端](spring-boot-admin-server)
* 启动 [admin 客户端](spring-boot-admin-client)
    * 访问http://localhost:8000 即可
