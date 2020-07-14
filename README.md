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
