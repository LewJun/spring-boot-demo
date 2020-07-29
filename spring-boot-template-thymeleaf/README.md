# spring-boot-template-thymeleaf

> 一个spring boot的thymeleaf例子

[TOC]

## 添加依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

## 建立templates目录
在src/main/resources下建立templates目录，用于存放模板文件，例如index.html

## [App.java](src/main/java/com/example/lewjun/App.java)
```java
/**
 * spring boot 启动类
 */
@SpringBootApplication
@Controller
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("msg", "hello world");
        model.addAttribute("now", new Date());
        return "index";
    }
}
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

