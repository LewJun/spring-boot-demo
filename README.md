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
* [spring-boot-junit-test](spring-boot-junit-test) 单元测试例子
* [spring-boot-restfull](spring-boot-restfull) restfull例子
* [spring-boot-swagger2](spring-boot-swagger2) 使用springfox3生成swagger文档
* [spring-boot-jsr-303](spring-boot-jsr-303) JSR-303实现请求参数校验
* [spring-boot-war](spring-boot-war) 演示如何将spring boot 项目打包成传统的war包
* [spring-boot-multi-env](spring-boot-multi-env) 演示spring boot 项目多环境配置
* [spring-boot-aoplog](spring-boot-aoplog) 演示spring boot 实现日志全局拦截
* [spring-boot-unified-return](spring-boot-unified-return) 演示spring boot 实现restfull api返回统一格式的数据
* [spring-boot-template-jsp](spring-boot-template-jsp) 演示spring boot 集成jsp的例子
* 数据库
    * [spring-boot-template-thymeleaf](spring-boot-template-thymeleaf) 演示spring boot 集成thymeleaf的例子
    * [spring-boot-orm-jdbcTemplate](spring-boot-orm-jdbcTemplate) 一个spring boot集成jdbcTemplate的例子
    * [spring-boot-orm-jpa](spring-boot-orm-jpa) 一个spring boot整合jpa的例子
    * [spring-boot-orm-mybatis](spring-boot-orm-mybatis) 一个spring boot整合mybatis的例子
    * [spring-boot-orm-mybatis-plus](spring-boot-orm-mybatis-plus) spring boot 集成 mybatis-plus
    * [spring-boot-sharding-datasource-mybatis](spring-boot-sharding-datasource-mybatis) mybatis整合ShardingSphere，实现分库分表
    * [spring-boot-sharding-multi-datasource-mybatis](spring-boot-sharding-multi-datasource-mybatis) mybatis整合ShardingSphere实现多数据源
* [spring-boot-banner](spring-boot-banner) 一个spring boot自定义banner的例子
* [spring-boot-orm-redis-lettuce](spring-boot-redis-lettuce) 一个spring boot整合lettuce redis的例子
* [spring-boot-scheduling](spring-boot-scheduling) 定时任务
* [spring-boot-cos-tencent](spring-boot-cos-tencent) 集成腾讯对象存储 COS
* [spring-boot-mapstruct](spring-boot-mapstruct) spring-boot-mapstruct的使用
* [spring-boot-mapstruct-lombok](spring-boot-mapstruct-lombok) mapstruct搭配lombok的使用
* [spring-boot-jdk8-new-features](spring-boot-jdk8-new-features) java 8 新特性
