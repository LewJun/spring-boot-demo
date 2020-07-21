# spring-boot-war

> 演示如何将spring boot 项目打包成传统的war包

[TOC]

## Step 1 设置packaging 为war
## Step 2 引入spring-boot-starter-tomcat
## Step 3 设置scope为provided
## 完整[pom.xml](pom.xml)

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

    <artifactId>spring-boot-war</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>           <!-- Step 1 设置packaging 为war -->

        <!-- Step 2 引入spring-boot-starter-tomcat -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope> <!-- Step 3 设置scope为provided -->
        </dependency>
</project>
```

## 继承 SpringBootServletInitializer
## 重写SpringBootServletInitializer#configure方法
## 完整[App.java](src/main/java/com/example/lewjun/App.java)

```java
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class App extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
}

```

## 打war包
> mvn war:war

将会生成spring-boot-war-1.0-SNAPSHOT.war

## 拷贝到外部tomcat

## 访问
application.yml并没有什么卵用，访问地址变成了：
http://localhost:8080/spring-boot-war-1.0-SNAPSHOT/hello
