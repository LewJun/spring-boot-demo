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

## 找回日志中请求路径列表
为什么在Spring Boot 2.1.x版本中不再打印请求路径列表呢？

主要是由于从该版本开始，将这些日志的打印级别做了调整：从原来的INFO调整为TRACE。所以，当我们希望在应用启动的时候打印这些信息的话，只需要在配置文件增增加对RequestMappingHandlerMapping类的打印级别设置即可，比如在application.properties中增加下面这行配置：

```properties
logging.level.org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping=trace
```

在增加了上面的配置之后重启应用，便可以看到如下的日志打印：
```log
2020-08-03 10:18:08.315 TRACE 7080 --- [  restartedMain] s.w.s.m.m.a.RequestMappingHandlerMapping : 
	c.e.l.App:
	{GET /}: index()
	{GET /hello}: hello(String)
2020-08-03 10:18:08.322 TRACE 7080 --- [  restartedMain] s.w.s.m.m.a.RequestMappingHandlerMapping : 
	o.s.b.a.w.s.e.BasicErrorController:
	{ /error}: error(HttpServletRequest)
	{ /error, produces [text/html]}: errorHtml(HttpServletRequest,HttpServletResponse)
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

