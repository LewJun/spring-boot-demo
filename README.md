# spring-boot-demo

[TOC]

## 创建maven项目

```
mvn archetype:generate 
-DgroupId=com.example.lewjun
-DartifactId=spring-boot-demo
-DarchetypeArtifactId=maven-archetype-quickstart
-DarchetypeCatalog=local
-DinteractiveMode=false
```

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run


## 模块

* [spring-boot-helloworld](spring-boot-helloworld) spring-boot 的一个 helloworld
* [spring-boot-read-properties](spring-boot-read-properties) 一个spring boot读取properties/yml的例子
* [spring-boot-actuator](spring-boot-actuator) spring-boot 集成 spring-boot-starter-actuator 用于监控 spring-boot 的启动和运行状态
* [spring-boot-admin](spring-boot-admin) 主要演示了 Spring Boot 如何集成 Admin 管控台，监控管理 Spring Boot 应用，
    分别为 [admin 服务端](spring-boot-admin/spring-boot-admin-server)和 [admin 客户端](spring-boot-admin/spring-boot-admin-client) 两个模块。
    可以通过服务端管理客户端，客户端也可以看到自己的详细信息。
* [spring-boot-logback](spring-boot-logback) 一个spring boot的logback配置及使用示例
* [spring-boot-recv-param](spring-boot-recv-param) spring boot 接收参数的几种方式
* [spring-boot-file-upload](spring-boot-file-upload) 文件上传例子
* [spring-boot-file-download](spring-boot-file-download) 文件下载例子


